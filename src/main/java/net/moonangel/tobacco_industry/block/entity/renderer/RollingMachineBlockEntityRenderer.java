package net.moonangel.tobacco_industry.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.moonangel.tobacco_industry.block.entity.RollingMachineBlockEntity;

public class RollingMachineBlockEntityRenderer extends KineticBlockEntityRenderer<RollingMachineBlockEntity> {
    public RollingMachineBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void renderSafe(RollingMachineBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer, int light, int overlay) {

    }
}
