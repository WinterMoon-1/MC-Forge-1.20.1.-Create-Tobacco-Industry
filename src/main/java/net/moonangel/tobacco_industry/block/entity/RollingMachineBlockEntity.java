package net.moonangel.tobacco_industry.block.entity;

import com.google.common.collect.ImmutableList;
import com.simibubi.create.AllSoundEvents;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.belt.behaviour.DirectBeltInputBehaviour;
import com.simibubi.create.content.processing.recipe.ProcessingInventory;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyRecipe;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.filtering.FilteringBehaviour;
import com.simibubi.create.foundation.item.ItemHelper;
import com.simibubi.create.foundation.recipe.RecipeConditions;
import com.simibubi.create.foundation.recipe.RecipeFinder;
import com.simibubi.create.foundation.utility.VecHelper;
import com.simibubi.create.infrastructure.config.AllConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.moonangel.tobacco_industry.recipe.RollingRecipe;
import net.moonangel.tobacco_industry.util.TITags;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class RollingMachineBlockEntity extends KineticBlockEntity {

    public RollingMachineBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
        inventory = new ProcessingInventory(this::start).withSlotLimit(!AllConfigs.server().recipes.bulkPressing.get());
        inventory.remainingTime = -1;
        recipeIndex = 0;
        invProvider = LazyOptional.of(() -> inventory);
        playEvent = ItemStack.EMPTY;
    }
    private static final Object coilingRecipesKey = new Object();

    public ProcessingInventory inventory;
    private int recipeIndex;
    private final LazyOptional<IItemHandler> invProvider;
    private FilteringBehaviour filtering;
    private ItemStack playEvent;
    private static final int MIX_SLOT = 0;
    private static final int PAPER_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        super.addBehaviours(behaviours);    
        behaviours.add(new DirectBeltInputBehaviour(this));
    }

    @Override
    public void write(CompoundTag compound, boolean clientPacket) {
        compound.put("Inventory", inventory.serializeNBT());
        compound.putInt("RecipeIndex", recipeIndex);
        super.write(compound, clientPacket);

        if (!clientPacket || playEvent.isEmpty())
            return;
        compound.put("PlayEvent", playEvent.serializeNBT());
        playEvent = ItemStack.EMPTY;
    }

    @Override
    protected void read(CompoundTag compound, boolean clientPacket) {
        super.read(compound, clientPacket);
        inventory.deserializeNBT(compound.getCompound("Inventory"));
        recipeIndex = compound.getInt("RecipeIndex");

        if (compound.contains("PlayEvent"))
            playEvent = ItemStack.of(compound.getCompound("PlayEvent"));
    }

    @Override
    protected AABB createRenderBoundingBox() {
        return new AABB(worldPosition).inflate(.125f);
    }

    //TODO Test and tweak pitch
    @Override
    @OnlyIn(Dist.CLIENT)
    public void tickAudio() {
        super.tickAudio();
        if (getSpeed() == 0)
            return;

        if (!playEvent.isEmpty()) {
            spawnEventParticles(playEvent);
            playEvent = ItemStack.EMPTY;

            AllSoundEvents.CRANKING.playAt(level, worldPosition, 3, 2, true);
        }
    }

    private void findEntities() {
        if (!inventory.isEmpty())
            return;

        Vec3 center = VecHelper.getCenterOf(worldPosition);

        // Mix input
        AABB topSearchArea = new AABB(center.add(0, 0.75, 0), center.add(0, 0.25, 0)).inflate(.4f);
        for (ItemEntity itemEntity : level.getEntitiesOfClass(ItemEntity.class, topSearchArea)) {
            if (itemEntity.getItem().is(TITags.Items.TOBACCO_MIX)) insertItem(itemEntity, MIX_SLOT);
        }

        // Paper input
        Direction leftSide = getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING).getCounterClockWise();
        Vec3 offset = Vec3.atLowerCornerOf(leftSide.getNormal()).scale(0.5);
        AABB sideSearchArea = new AABB(center, center.add(offset)).inflate(0.3);
        for (ItemEntity itemEntity : level.getEntitiesOfClass(ItemEntity.class, sideSearchArea)) {
            if (itemEntity.getItem().is(TITags.Items.ROLLING_PAPER)) insertItem(itemEntity, PAPER_SLOT);
        }
    }

    //TODO
    @Override
    public void tick() {
        super.tick();

        if (getSpeed() == 0)
            return;
        if (inventory.remainingTime == -1) {
            if (!inventory.isEmpty() && !inventory.appliedRecipe)
                start(inventory.getStackInSlot(0));
            return;
        }

        if (inventory.isEmpty()) findEntities();

        float processingSpeed = Mth.clamp(Math.abs(getSpeed()) / 24, 1, 128);
        inventory.remainingTime -= processingSpeed;

        if (inventory.remainingTime < 5 && !inventory.appliedRecipe) {
            if (level.isClientSide && !isVirtual())
                return;
            playEvent = inventory.getStackInSlot(0);
            applyRecipe();
            inventory.appliedRecipe = true;
            inventory.recipeDuration = 20;
            inventory.remainingTime = 20;
            sendData();
            return;
        }

        //TODO hardcode ejecting to the right
        Vec3 itemMovement = getItemMovementVec();
        Direction itemMovementFacing = Direction.getNearest(itemMovement.x, itemMovement.y, itemMovement.z);
        if (inventory.remainingTime > 0)
            return;
        inventory.remainingTime = 0;

        for (int slot = 0; slot < inventory.getSlots(); slot++) {
            ItemStack stack = inventory.getStackInSlot(slot);
            if (stack.isEmpty())
                continue;
            ItemStack tryExportingToBeltFunnel = getBehaviour(DirectBeltInputBehaviour.TYPE)
                    .tryExportingToBeltFunnel(stack, itemMovementFacing.getOpposite(), false);
            if (tryExportingToBeltFunnel != null) {
                if (tryExportingToBeltFunnel.getCount() != stack.getCount()) {
                    inventory.setStackInSlot(slot, tryExportingToBeltFunnel);
                    notifyUpdate();
                    return;
                }
                if (!tryExportingToBeltFunnel.isEmpty())
                    return;
            }
        }

        BlockPos nextPos = worldPosition.offset(BlockPos.containing(itemMovement));
        DirectBeltInputBehaviour behaviour = BlockEntityBehaviour.get(level, nextPos, DirectBeltInputBehaviour.TYPE);
        if (behaviour != null) {
            boolean changed = false;
            if (!behaviour.canInsertFromSide(itemMovementFacing))
                return;
            if (level.isClientSide && !isVirtual())
                return;
            for (int slot = 0; slot < inventory.getSlots(); slot++) {
                ItemStack stack = inventory.getStackInSlot(slot);
                if (stack.isEmpty())
                    continue;
                ItemStack remainder = behaviour.handleInsertion(stack, itemMovementFacing, false);
                if (remainder.equals(stack, false))
                    continue;
                inventory.setStackInSlot(slot, remainder);
                changed = true;
            }
            if (changed) {
                setChanged();
                sendData();
            }
            return;
        }

        // Eject Items
        Vec3 outPos = VecHelper.getCenterOf(worldPosition)
                .add(itemMovement.scale(.5f)
                        .add(0, .5, 0));
        Vec3 outMotion = itemMovement.scale(.0625)
                .add(0, .125, 0);
        for (int slot = 0; slot < inventory.getSlots(); slot++) {
            ItemStack stack = inventory.getStackInSlot(slot);
            if (stack.isEmpty())
                continue;
            ItemEntity entityIn = new ItemEntity(level, outPos.x, outPos.y, outPos.z, stack);
            entityIn.setDeltaMovement(outMotion);
            level.addFreshEntity(entityIn);
        }
        inventory.clear();
        level.updateNeighbourForOutputSignal(worldPosition, getBlockState().getBlock());
        inventory.remainingTime = -1;
        setChanged();
        sendData();
    }

    @Override
    public void invalidate() {
        super.invalidate();
        invProvider.invalidate();
    }

    @Override
    public void destroy() {
        super.destroy();
        ItemHelper.dropContents(level, worldPosition, inventory);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER)
            return invProvider.cast();
        return super.getCapability(cap, side);
    }

    //TODO Might use it
    protected void spawnEventParticles(ItemStack stack) {
        if (stack == null || stack.isEmpty())
            return;

        ParticleOptions particleData = null;
        if (stack.getItem() instanceof BlockItem)
            particleData = new BlockParticleOption(ParticleTypes.BLOCK, ((BlockItem) stack.getItem()).getBlock()
                    .defaultBlockState());
        else
            particleData = new ItemParticleOption(ParticleTypes.ITEM, stack);

        RandomSource r = level.random;
        Vec3 v = VecHelper.getCenterOf(this.worldPosition)
                .add(0, 5 / 16f, 0);
        for (int i = 0; i < 10; i++) {
            Vec3 m = VecHelper.offsetRandomly(new Vec3(0, 0.25f, 0), r, .125f);
            level.addParticle(particleData, v.x, v.y, v.z, m.x, m.y, m.y);
        }
    }

    //TODO Might use it
    public Vec3 getItemMovementVec() {
        boolean alongX = getBlockState().getValue(HORIZONTAL_FACING) == Direction.NORTH ||
                getBlockState().getValue(HORIZONTAL_FACING) == Direction.SOUTH;
        boolean offset = getBlockState().getValue(HORIZONTAL_FACING) == Direction.SOUTH ||
                getBlockState().getValue(HORIZONTAL_FACING) == Direction.WEST;
        return new Vec3((offset ? -1 : 1) * (alongX ? 0 : 1), 0, (offset ? -1 : 1) * (alongX ? -1 : 0));
    }

    private void applyRecipe() {
        List<? extends Recipe<?>> recipes = getRecipes();
        if (recipes.isEmpty())
            return;
        if (recipeIndex >= recipes.size())
            recipeIndex = 0;

        Recipe<?> recipe = recipes.get(recipeIndex);

        int rolls = inventory.getStackInSlot(0)
                .getCount();
        inventory.clear();

        List<ItemStack> list = new ArrayList<>();
        for (int roll = 0; roll < rolls; roll++) {
            List<ItemStack> results = new LinkedList<ItemStack>();
            if (recipe instanceof RollingRecipe)
                results = ((RollingRecipe) recipe).rollResults();

            for (int i = 0; i < results.size(); i++) {
                ItemStack stack = results.get(i);
                ItemHelper.addToList(stack, list);
            }
        }

        for (int slot = 0; slot < list.size() && slot + 1 < inventory.getSlots(); slot++)
            inventory.setStackInSlot(slot + 1, list.get(slot));
        //TODO advancement
    }

    private List<? extends Recipe<?>> getRecipes() {
        Optional<RollingRecipe> assemblyRecipe = SequencedAssemblyRecipe.getRecipe(level, inventory.getStackInSlot(0),
                RollingRecipe.INSTANCE.getType(), RollingRecipe.class);
        if (assemblyRecipe.isPresent() && filtering.test(assemblyRecipe.get()
                .getResultItem(level.registryAccess())))
            return ImmutableList.of(assemblyRecipe.get());

        Predicate<Recipe<?>> types = RecipeConditions.isOfType(RollingRecipe.INSTANCE.getType());

        List<Recipe<?>> startedSearch = RecipeFinder.get(coilingRecipesKey, level, types);
        return startedSearch.stream()
                .filter(RecipeConditions.outputMatchesFilter(filtering))
                .filter(RecipeConditions.firstIngredientMatches(inventory.getStackInSlot(0)))
                .collect(Collectors.toList());
    }

    public void insertItem(ItemEntity entity, int slotIndex) {
        if (!inventory.getStackInSlot(slotIndex).isEmpty())
            return;
        if (!entity.isAlive())
            return;
        if (level.isClientSide)
            return;

        //Not necessary?
        //inventory.setStackInSlot(slotIndex, ItemStack.EMPTY);
        ItemStack remainder = inventory.insertItem(slotIndex, entity.getItem()
                .copy(), false);
        if (remainder.isEmpty())
            entity.discard();
        else
            entity.setItem(remainder);
    }

    public void start(ItemStack inserted) {
        if (inventory.isEmpty())
            return;
        if (level.isClientSide && !isVirtual())
            return;

        List<? extends Recipe<?>> recipes = getRecipes();
        boolean valid = !recipes.isEmpty();
        int time = 50;

        if (recipes.isEmpty()) {
            inventory.remainingTime = inventory.recipeDuration = 10;
            inventory.appliedRecipe = false;
            sendData();
            return;
        }

        if (valid) {
            recipeIndex++;
            if (recipeIndex >= recipes.size())
                recipeIndex = 0;
        }

        Recipe<?> recipe = recipes.get(recipeIndex);
        if (recipe instanceof RollingRecipe coilingRecipe) {
            time = coilingRecipe.getProcessingDuration();
        }

        inventory.remainingTime = time * Math.max(1, (inserted.getCount() / 5));
        inventory.recipeDuration = inventory.remainingTime;
        inventory.appliedRecipe = false;
        sendData();
    }

}
