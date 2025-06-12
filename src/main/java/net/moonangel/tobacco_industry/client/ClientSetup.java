package net.moonangel.tobacco_industry.client;

import net.moonangel.tobacco_industry.TobaccoIndustry;
import net.moonangel.tobacco_industry.animation.AnimationReloader;
import net.moonangel.tobacco_industry.item.TIItems;
import net.moonangel.tobacco_industry.item.custom.CigarItem;
import net.moonangel.tobacco_industry.item.custom.CigaretteItem;
import net.moonangel.tobacco_industry.item.custom.TobaccoMixItem;
import net.moonangel.tobacco_industry.util.TITags;
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
            if(stack.is(TITags.Items.CIGARETTE) || stack.is(TITags.Items.CIGAR) || stack.is(TITags.Items.TOBACCO_MIX)) {
                Item item = stack.getItem();
                if (item instanceof CigaretteItem cigaretteItem) {
                    return (tintIndex == 1) ? cigaretteItem.getStripColour() : cigaretteItem.getBaseColour();
                } else if (item instanceof CigarItem cigarItem) {
                    return (tintIndex == 1) ? cigarItem.getStripColour() : cigarItem.getBaseColour();
                } else if (item instanceof TobaccoMixItem tobaccoMixItem) {
                    return (tintIndex == 1) ? tobaccoMixItem.getStripColor() : tobaccoMixItem.getBaseColour();
                }
            }

            return 0xFFFFFF;
        };

        event.register(cigaretteColorHandler,
                // Cigarettes
                TIItems.BASE_CIGARETTE.get(),
                TIItems.CIGARETTE_ATTUNEMENT.get(),
                TIItems.CIGARETTE_FADING.get(),
                TIItems.CIGARETTE_GLOWING.get(),
                TIItems.CIGARETTE_MAGNETISM.get(),
                TIItems.CIGARETTE_REACHING.get(),
                TIItems.CIGARETTE_RETURNING.get(),
                TIItems.CIGARETTE_WISDOM.get(),
                TIItems.CIGARETTE_HASTE.get(),
                TIItems.CIGARETTE_ABSORPTION.get(),
                TIItems.CIGARETTE_DOLPHINS_GRACE.get(),
                TIItems.CIGARETTE_NIGHT_VISION.get(),
                TIItems.CIGARETTE_INVISIBILITY.get(),
                TIItems.CIGARETTE_FIRE_RESISTANCE.get(),
                TIItems.CIGARETTE_LEAPING.get(),
                TIItems.CIGARETTE_SWIFTNESS.get(),
                TIItems.CIGARETTE_WATER_BREATHING.get(),
                TIItems.CIGARETTE_HEALING.get(),
                TIItems.CIGARETTE_REGENERATION.get(),
                TIItems.CIGARETTE_STRENGTH.get(),
                TIItems.CIGARETTE_SLOW_FALLING.get(),

                // Cigars
                TIItems.BASE_CIGAR.get(),
                TIItems.CIGAR_ATTUNEMENT.get(),
                TIItems.CIGAR_FADING.get(),
                TIItems.CIGAR_GLOWING.get(),
                TIItems.CIGAR_MAGNETISM.get(),
                TIItems.CIGAR_REACHING.get(),
                TIItems.CIGAR_RETURNING.get(),
                TIItems.CIGAR_WISDOM.get(),
                TIItems.CIGAR_HASTE.get(),
                TIItems.CIGAR_ABSORPTION.get(),
                TIItems.CIGAR_DOLPHINS_GRACE.get(),
                TIItems.CIGAR_NIGHT_VISION.get(),
                TIItems.CIGAR_INVISIBILITY.get(),
                TIItems.CIGAR_FIRE_RESISTANCE.get(),
                TIItems.CIGAR_LEAPING.get(),
                TIItems.CIGAR_SWIFTNESS.get(),
                TIItems.CIGAR_WATER_BREATHING.get(),
                TIItems.CIGAR_HEALING.get(),
                TIItems.CIGAR_REGENERATION.get(),
                TIItems.CIGAR_STRENGTH.get(),
                TIItems.CIGAR_SLOW_FALLING.get(),

                // Mixes
                TIItems.BASE_MIX.get(),
                TIItems.MIX_ATTUNEMENT.get(),
                TIItems.MIX_FADING.get(),
                TIItems.MIX_GLOWING.get(),
                TIItems.MIX_MAGNETISM.get(),
                TIItems.MIX_REACHING.get(),
                TIItems.MIX_RETURNING.get(),
                TIItems.MIX_WISDOM.get(),
                TIItems.MIX_HASTE.get(),
                TIItems.MIX_ABSORPTION.get(),
                TIItems.MIX_DOLPHINS_GRACE.get(),
                TIItems.MIX_NIGHT_VISION.get(),
                TIItems.MIX_INVISIBILITY.get(),
                TIItems.MIX_FIRE_RESISTANCE.get(),
                TIItems.MIX_LEAPING.get(),
                TIItems.MIX_SWIFTNESS.get(),
                TIItems.MIX_WATER_BREATHING.get(),
                TIItems.MIX_HEALING.get(),
                TIItems.MIX_REGENERATION.get(),
                TIItems.MIX_STRENGTH.get(),
                TIItems.MIX_SLOW_FALLING.get()
        );
    }

    @SubscribeEvent
    public static void onRegisterReloadListener(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(new AnimationReloader());
    }
}


