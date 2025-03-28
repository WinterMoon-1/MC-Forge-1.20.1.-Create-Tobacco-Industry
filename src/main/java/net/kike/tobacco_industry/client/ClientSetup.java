package net.kike.tobacco_industry.client;

import net.kike.tobacco_industry.TobaccoIndustry;
import net.kike.tobacco_industry.animation.AnimationReloader;
import net.kike.tobacco_industry.item.ModItems;
import net.kike.tobacco_industry.item.custom.CigarItem;
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
            if(stack.is(ModTags.Items.CIGARETTE) || stack.is(ModTags.Items.CIGAR)) {
                Item item = stack.getItem();
                if (item instanceof CigaretteItem cigaretteItem) {
                    return (tintIndex == 1) ? cigaretteItem.getStripColor() : cigaretteItem.getBaseColor();
                } else if (item instanceof CigarItem cigarItem) {
                    return (tintIndex == 1) ? cigarItem.getStripColor() : cigarItem.getBaseColor();
                }
            }

            return 0xFFFFFF;
        };

        event.register(cigaretteColorHandler,
                // Cigarettes
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
                ModItems.CIGARETTE_SLOW_FALLING.get(),

                // Cigars
                ModItems.BASE_CIGAR.get(),
                ModItems.CIGAR_ATTUNEMENT.get(),
                ModItems.CIGAR_FADING.get(),
                ModItems.CIGAR_GLOWING.get(),
                ModItems.CIGAR_MAGNETISM.get(),
                ModItems.CIGAR_REACHING.get(),
                ModItems.CIGAR_RETURNING.get(),
                ModItems.CIGAR_WISDOM.get(),
                ModItems.CIGAR_HASTE.get(),
                ModItems.CIGAR_ABSORPTION.get(),
                ModItems.CIGAR_DOLPHINS_GRACE.get(),
                ModItems.CIGAR_NIGHT_VISION.get(),
                ModItems.CIGAR_INVISIBILITY.get(),
                ModItems.CIGAR_FIRE_RESISTANCE.get(),
                ModItems.CIGAR_LEAPING.get(),
                ModItems.CIGAR_SWIFTNESS.get(),
                ModItems.CIGAR_WATER_BREATHING.get(),
                ModItems.CIGAR_HEALING.get(),
                ModItems.CIGAR_REGENERATION.get(),
                ModItems.CIGAR_STRENGTH.get(),
                ModItems.CIGAR_SLOW_FALLING.get()
        );
    }

    @SubscribeEvent
    public static void onRegisterReloadListener(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(new AnimationReloader());
    }
}


