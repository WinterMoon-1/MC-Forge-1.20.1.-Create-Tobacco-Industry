package net.kike.tobacco_industry.event;

import net.kike.tobacco_industry.TobaccoIndustry;
import net.kike.tobacco_industry.entity.ModEntities;
import net.kike.tobacco_industry.entity.client.ModModelLayers;
import net.kike.tobacco_industry.entity.client.RhinoModel;
import net.kike.tobacco_industry.entity.custom.RhinoEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TobaccoIndustry.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.RHINO.get(), RhinoEntity.createAttributes().build());
    }

}
