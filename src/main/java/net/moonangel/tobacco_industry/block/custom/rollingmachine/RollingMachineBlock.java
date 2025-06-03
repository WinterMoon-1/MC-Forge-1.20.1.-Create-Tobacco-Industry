package net.moonangel.tobacco_industry.block.custom.rollingmachine;

import com.simibubi.create.content.kinetics.base.HorizontalKineticBlock;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.moonangel.tobacco_industry.block.entity.RollingMachineBlockEntity;

public class RollingMachineBlock extends HorizontalKineticBlock implements IBE<RollingMachineBlockEntity> {
    public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 16, 16);

    public RollingMachineBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState blockState) {
        return blockState.getValue(HORIZONTAL_FACING).getAxis();
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return face == state.getValue(HORIZONTAL_FACING).getOpposite();
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction preferred = getPreferredHorizontalFacing(context);
        if (preferred != null)
            return defaultBlockState().setValue(HORIZONTAL_FACING, preferred.getOpposite());
        return super.getStateForPlacement(context);
    }


    @Override
    public Class<RollingMachineBlockEntity> getBlockEntityClass() {
        return RollingMachineBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends RollingMachineBlockEntity> getBlockEntityType() {
        return null;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }
}
