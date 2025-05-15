package net.moonangel.tobacco_industry.content;

import com.jozufozu.flywheel.api.MaterialManager;
import com.jozufozu.flywheel.core.virtual.VirtualRenderWorld;
import com.simibubi.create.content.contraptions.behaviour.MovementBehaviour;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import com.simibubi.create.content.contraptions.render.ActorInstance;
import com.simibubi.create.content.contraptions.render.ContraptionMatrices;
import com.simibubi.create.foundation.utility.BlockHelper;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.moonangel.tobacco_industry.block.custom.TobaccoCropBlock;
import org.jetbrains.annotations.Nullable;

public class TobaccoCropHarvesterMovementBehaviour implements MovementBehaviour {

    private final MovementBehaviour original;

    public TobaccoCropHarvesterMovementBehaviour(MovementBehaviour original) {
        this.original = original;
    }

    @Override
    public void visitNewPosition(MovementContext context, BlockPos pos) {
        Level level = context.world;
        BlockState state = level.getBlockState(pos);

        if (state.getBlock() instanceof TobaccoCropBlock block) {
            if (block.isMature(state)) {
                BlockPos otherHalfPos = block.isUpperHalf(state) ? pos.below() : pos.above();
                BlockPos bottomPos = block.isUpperHalf(state) ? otherHalfPos : pos;

                BlockHelper.destroyBlockAs(level, bottomPos.above(), null, ItemStack.EMPTY, 1.0f, itemStack -> {
                    dropItem(context, itemStack);
                });
                BlockHelper.destroyBlockAs(level, bottomPos, null, ItemStack.EMPTY, 1.0f, itemStack -> {
                    dropItem(context, itemStack);
                });

                level.setBlock(bottomPos, block.getStateForAge(0), 3);
            }
        }

        original.visitNewPosition(context, pos);
    }

    // Delegate the methods to the original
    @Override
    public boolean isActive(MovementContext context) {
        return original.isActive(context);
    }

    @Override
    public boolean hasSpecialInstancedRendering() {
        return original.hasSpecialInstancedRendering();
    }

    @Override
    public boolean renderAsNormalBlockEntity() {
        return original.renderAsNormalBlockEntity();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public @Nullable ActorInstance createInstance(MaterialManager materialManager, VirtualRenderWorld simulationWorld, MovementContext context) {
        return original.createInstance(materialManager, simulationWorld, context);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void renderInContraption(MovementContext context, VirtualRenderWorld renderWorld, ContraptionMatrices matrices, MultiBufferSource buffer) {
        original.renderInContraption(context, renderWorld, matrices, buffer);
    }

    @Override
    public Vec3 getActiveAreaOffset(MovementContext context) {
        return original.getActiveAreaOffset(context);
    }

}
