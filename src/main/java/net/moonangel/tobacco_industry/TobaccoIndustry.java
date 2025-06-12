package net.moonangel.tobacco_industry;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipHelper;
import com.simibubi.create.foundation.item.TooltipModifier;
import net.moonangel.tobacco_industry.block.TIBlocks;
import net.moonangel.tobacco_industry.block.entity.TIBlockEntities;
import net.moonangel.tobacco_industry.content.TIMovementBehaviours;
import net.moonangel.tobacco_industry.effect.TIEffects;
import net.moonangel.tobacco_industry.item.TICreativeModeTabs;
import net.moonangel.tobacco_industry.item.TIItems;
import net.moonangel.tobacco_industry.recipe.TIRecipes;
import net.moonangel.tobacco_industry.screen.TIMenuTypes;
import net.moonangel.tobacco_industry.sound.TISounds;
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

@Mod(TobaccoIndustry.MOD_ID)
public class TobaccoIndustry {

    public static final String MOD_ID = "tobacco_industry";
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);

    static {
        REGISTRATE.setTooltipModifierFactory(item -> new ItemDescription.Modifier(item, TooltipHelper.Palette.STANDARD_CREATE)
                .andThen(TooltipModifier.mapNull(KineticStats.create(item))));
    }

    public TobaccoIndustry(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        REGISTRATE.registerEventListeners(modEventBus);

        TICreativeModeTabs.register(modEventBus);
        TIEffects.register(modEventBus);

        TIItems.register(modEventBus);
        TIBlocks.register(modEventBus);

        TISounds.register(modEventBus);
        TIBlockEntities.register(modEventBus);
        TIMenuTypes.register(modEventBus);
        TIRecipes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(TIMovementBehaviours::register);
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
