package net.moonangel.tobacco_industry.block.entity;

import com.jozufozu.flywheel.backend.Backend;
import com.jozufozu.flywheel.core.PartialModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.redstone.thresholdSwitch.ThresholdSwitchObservable;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import com.simibubi.create.foundation.utility.AngleHelper;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.moonangel.tobacco_industry.TobaccoIndustry;

import static com.simibubi.create.content.kinetics.base.HorizontalKineticBlock.HORIZONTAL_FACING;

public class RollingMachineBlockEntityRenderer extends KineticBlockEntityRenderer<RollingMachineBlockEntity> {
    public RollingMachineBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void renderSafe(RollingMachineBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer, int light, int overlay) {
        renderShaft(be, ms, buffer, light);}

    protected void renderBelt(RollingMachineBlockEntity be, PoseStack ms, MultiBufferSource buffer, int light) {
        BlockState blockState =  be.getBlockState();
        PartialModel beltPartial;
        float speed = be.getSpeed();
        /*
        if (speed > 0) beltPartial = TIPartialModels.ROLLING_BELT_ACTIVE;
        if (speed > 0) beltPartial = TIPartialModels.ROLLING_BELT_REVERSED;
        else beltPartial = TIPartialModels.ROLLING_BELT_INACTIVE;

        SuperByteBuffer beltBuf = CachedBufferer.partial(beltPartial, be.getBlockState());
        Direction face = be.getBlockState().getValue(HORIZONTAL_FACING);
        float yRot = switch (face) {
            case NORTH -> 0;
            case SOUTH -> 180;
            case EAST  -> 270;
            case WEST  -> 90;
            default    -> 0;
        };4

        beltBuf.light(light).renderInto(ms, buffer.getBuffer(RenderType.cutoutMipped()));
        */
    }

    protected void renderShaft(RollingMachineBlockEntity be, PoseStack ms, MultiBufferSource buffer, int light) {
        PartialModel shaft = TIPartialModels.SHAFT_EIGHTH;
        SuperByteBuffer shaftBuf = CachedBufferer.partial(shaft, be.getBlockState());

        standardKineticRotationTransform(shaftBuf, be, light);

        Direction face = be.getBlockState().getValue(HORIZONTAL_FACING);
        float yRot = switch (face) {
            case NORTH -> 0;
            case SOUTH -> 180;
            case EAST  -> 270;
            case WEST  -> 90;
            default    -> 0;
        };

        shaftBuf.rotateCentered(Direction.UP, AngleHelper.rad(yRot));

        shaftBuf.light(light).renderInto(ms, buffer.getBuffer(RenderType.cutoutMipped()));
    }
}
