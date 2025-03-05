package net.kike.tobacco_industry;

import net.kike.tobacco_industry.animation.AnimationReloader;
import net.kike.tobacco_industry.block.ModBlocks;
import net.kike.tobacco_industry.block.entity.ModBlockEntities;
import net.kike.tobacco_industry.effect.ModEffects;
import net.kike.tobacco_industry.entity.ModEntities;
import net.kike.tobacco_industry.item.ModCreativeModTabs;
import net.kike.tobacco_industry.item.ModItems;
import net.kike.tobacco_industry.loot.ModLootModifiers;
import net.kike.tobacco_industry.recipe.ModRecipes;
import net.kike.tobacco_industry.screen.ModMenuTypes;
import net.kike.tobacco_industry.sound.ModSounds;
import net.kike.tobacco_industry.villager.ModVillagers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TobaccoIndustry.MOD_ID)
public class TobaccoIndustry {

    public static final String MOD_ID = "tobacco_industry";

    public TobaccoIndustry(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        ModCreativeModTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModLootModifiers.register(modEventBus);
        ModVillagers.register(modEventBus);
        ModSounds.register(modEventBus);
        ModEntities.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModEffects.register(modEventBus);
        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(modid = TobaccoIndustry.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}
