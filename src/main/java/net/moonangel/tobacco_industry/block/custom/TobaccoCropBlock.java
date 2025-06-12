package net.moonangel.tobacco_industry.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import net.moonangel.tobacco_industry.item.TIItems;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TobaccoCropBlock extends CropBlock {
    public static final int FIRST_STAGE_MAX_AGE = 11;
    public static final int SECOND_STAGE_MAX_AGE = 18;
    public static final int SECOND_STAGE_SPAWN_AGE = 5;

    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 8.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 9.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 9.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 9.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 9.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 9.0D, 14.0D)
    };


    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 18);

    public TobaccoCropBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE_BY_AGE[this.getAge(pState)];
    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!pLevel.isAreaLoaded(pPos, 1)) return;
        if (pLevel.getRawBrightness(pPos, 0) >= 9) {

            if (canGrow(pState, pLevel, pPos)) {
                float growthSpeed = getGrowthSpeed(this, pLevel, pPos);

                if (ForgeHooks.onCropsGrowPre(pLevel, pPos, pState, pRandom.nextInt((int)(25.0F / growthSpeed) + 1) == 0)) {
                    growTobaccoPlant(pLevel, pPos, pState, 1);
                    ForgeHooks.onCropsGrowPost(pLevel, pPos, pState);
                }
            }
        }
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        return super.mayPlaceOn(state, world, pos);
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockState belowState = pLevel.getBlockState(pPos.below());
        return super.canSurvive(pState, pLevel, pPos) || (belowState.is(this) && !isUpperHalf(belowState) && isUpperHalf(pState));
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return this.getAge(pState) != this.getMaxAge() && this.getAge(pState) != FIRST_STAGE_MAX_AGE;
    }

    @Override
    public void growCrops(Level pLevel, BlockPos pPos, BlockState pState) {
        int growthIncrease = this.getBonemealAgeIncrease(pLevel) - 2; // Makes bone-mealing less effective

        if (!isUpperHalf(pState) && canGrow(pState, pLevel, pPos)) { // If the bottom is bone-mealed
            growTobaccoPlant(pLevel, pPos, pState, growthIncrease);
        } else if (isUpperHalf(pState) && canGrow(pState, pLevel, pPos.below())) {  // If top is bone-mealed
            growTobaccoPlant(pLevel, pPos.below(), pLevel.getBlockState(pPos.below()), growthIncrease);
        }
    }

    private void growTobaccoPlant(Level pLevel, BlockPos pPos, BlockState pState, int growthIncrease) {
        int currentAge = this.getAge(pState);
        int nextAge = Math.min(currentAge + growthIncrease, this.getMaxAge());

        // Only for the bottom half
        if (!this.isUpperHalf(pState)) {
            // Update the bottom
            pLevel.setBlock(pPos, this.getStateForAge(Math.min(nextAge, FIRST_STAGE_MAX_AGE)), 2);

            if (nextAge >= SECOND_STAGE_SPAWN_AGE) {
                BlockPos abovePos = pPos.above();
                BlockState aboveState = pLevel.getBlockState(abovePos);

                if (aboveState.isAir()) { // Create the top
                    int topAge = FIRST_STAGE_MAX_AGE + (nextAge - SECOND_STAGE_SPAWN_AGE + 1);
                    topAge = Math.min(topAge, SECOND_STAGE_MAX_AGE);
                    pLevel.setBlock(abovePos, this.getStateForAge(topAge), 2);
                } else if (aboveState.is(this)) { // Update the top
                    int topAge = Math.min(SECOND_STAGE_MAX_AGE, this.getAge(aboveState) + growthIncrease);
                    pLevel.setBlock(abovePos, this.getStateForAge(topAge), 2);
                }
            }
        }
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if (state.getValue(AGE) >= SECOND_STAGE_SPAWN_AGE) {
            BlockPos otherHalfPos = state.getValue(AGE) > FIRST_STAGE_MAX_AGE ? pos.below() : pos.above();
            BlockState otherHalfState = level.getBlockState(otherHalfPos);

            if (otherHalfState.getBlock() == this) {
                // Get the other block's drops if the player is in survival
                if (!player.isCreative()) {
                    if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
                        BlockEntity be = level.getBlockEntity(otherHalfPos);

                        LootParams.Builder lootParamsBuilder = new LootParams.Builder(serverLevel)
                                .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(otherHalfPos))
                                .withParameter(LootContextParams.BLOCK_STATE, otherHalfState)
                                .withParameter(LootContextParams.TOOL, player.getMainHandItem())
                                .withParameter(LootContextParams.THIS_ENTITY, player);

                        if (be != null) {
                            lootParamsBuilder.withOptionalParameter(LootContextParams.BLOCK_ENTITY, be);
                        }

                        List<ItemStack> drops = otherHalfState.getBlock().getDrops(otherHalfState, lootParamsBuilder);
                        for (ItemStack drop : drops) {
                            Containers.dropItemStack(level, otherHalfPos.getX(), otherHalfPos.getY(), otherHalfPos.getZ(), drop);
                        }
                    }
                }
                // Delete the block
                level.levelEvent(player, 2001, otherHalfPos, Block.getId(otherHalfState));
                level.setBlock(otherHalfPos, Blocks.AIR.defaultBlockState(), 35);
            }
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    public boolean isUpperHalf(BlockState state) {
        return state.getValue(AGE) > FIRST_STAGE_MAX_AGE;
    }

    public boolean isMature(BlockState state) { return state.getValue(AGE) == 11 || state.getValue(AGE) == 18; }

    // Checks if the age is the max for both the lower and upper parts and checks if the block above is air or the upper half
    public boolean canGrow(BlockState pState, Level pLevel, BlockPos pPos) {
        return (this.getAge(pState) < this.getMaxAge() && this.getAge(pState) != FIRST_STAGE_MAX_AGE)
                && (pLevel.getBlockState(pPos.above()).isAir() || pLevel.getBlockState(pPos.above()).is(this));
    }

    @Override
    public int getMaxAge() {
        return SECOND_STAGE_MAX_AGE;
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return TIItems.TOBACCO_SEEDS.get();
    }

    @Override
    public @NotNull IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }
}
