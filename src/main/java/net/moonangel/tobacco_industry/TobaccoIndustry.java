package net.moonangel.tobacco_industry;

import com.simibubi.create.foundation.data.CreateRegistrate;
import net.moonangel.tobacco_industry.block.ModBlocks;
import net.moonangel.tobacco_industry.block.entity.ModBlockEntities;
import net.moonangel.tobacco_industry.content.ModMovementBehaviours;
import net.moonangel.tobacco_industry.effect.ModEffects;
import net.moonangel.tobacco_industry.entity.ModEntities;
import net.moonangel.tobacco_industry.item.ModCreativeModTabs;
import net.moonangel.tobacco_industry.item.ModItems;
import net.moonangel.tobacco_industry.loot.ModLootModifiers;
import net.moonangel.tobacco_industry.recipe.ModRecipes;
import net.moonangel.tobacco_industry.screen.ModMenuTypes;
import net.moonangel.tobacco_industry.sound.ModSounds;
import net.moonangel.tobacco_industry.villager.ModVillagers;
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

import static net.moonangel.tobacco_industry.block.ModBlocks.ROLLING_MACHINE;

@Mod(TobaccoIndustry.MOD_ID)
public class TobaccoIndustry {

    public static final String MOD_ID = "tobacco_industry";
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);

    public TobaccoIndustry(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        REGISTRATE.registerEventListeners(modEventBus);

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
        event.enqueueWork(ModMovementBehaviours::register);
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
