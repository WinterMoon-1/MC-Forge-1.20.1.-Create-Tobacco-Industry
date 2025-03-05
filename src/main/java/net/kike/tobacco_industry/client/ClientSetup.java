package net.kike.tobacco_industry.client;

import net.kike.tobacco_industry.TobaccoIndustry;
import net.kike.tobacco_industry.animation.AnimationReloader;
import net.kike.tobacco_industry.item.ModItems;
import net.kike.tobacco_industry.item.custom.CigaretteItem;
import net.kike.tobacco_industry.util.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TobaccoIndustry.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

    // Changes the color of the cigarettes / cigars
    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        ItemColor cigaretteColorHandler = (stack, tintIndex) -> {
            if(stack.is(ModTags.Items.CIGARETTE)) {
                Item item = stack.getItem();
                if (item instanceof CigaretteItem cigaretteItem) {
                    return (tintIndex == 1) ? cigaretteItem.getStripColor() : cigaretteItem.getBaseColor();
                }
            }

            return 0xFFFFFF;
        };

        event.register(cigaretteColorHandler,
                ModItems.BASE_CIGARETTE.get(),
                ModItems.CIGARETTE_ATTUNEMENT.get(),
                ModItems.CIGARETTE_FADING.get(),
                ModItems.CIGARETTE_GLOWING.get(),
                ModItems.CIGARETTE_MAGNETISM.get(),
                ModItems.CIGARETTE_REACHING.get(),
                ModItems.CIGARETTE_RETURNING.get(),
                ModItems.CIGARETTE_WISDOM.get(),
                ModItems.CIGARETTE_HASTE.get(),
                ModItems.CIGARETTE_ABSORPTION.get(),
                ModItems.CIGARETTE_DOLPHINS_GRACE.get(),
                ModItems.CIGARETTE_NIGHT_VISION.get(),
                ModItems.CIGARETTE_INVISIBILITY.get(),
                ModItems.CIGARETTE_FIRE_RESISTANCE.get(),
                ModItems.CIGARETTE_LEAPING.get(),
                ModItems.CIGARETTE_SWIFTNESS.get(),
                ModItems.CIGARETTE_WATER_BREATHING.get(),
                ModItems.CIGARETTE_HEALING.get(),
                ModItems.CIGARETTE_REGENERATION.get(),
                ModItems.CIGARETTE_STRENGTH.get(),
                ModItems.CIGARETTE_SLOW_FALLING.get()
        );
    }

    @SubscribeEvent
    public static void onRegisterReloadListener(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(new AnimationReloader());
    }
}


