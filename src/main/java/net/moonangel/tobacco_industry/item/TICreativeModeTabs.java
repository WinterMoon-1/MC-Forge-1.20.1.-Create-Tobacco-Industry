package net.moonangel.tobacco_industry.item;

import net.moonangel.tobacco_industry.TobaccoIndustry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.moonangel.tobacco_industry.block.TIBlocks;

public class TICreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TobaccoIndustry.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TOBACCO_INDUSTRY_TAB = CREATIVE_MODE_TABS.register("tobacco_industry_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(TIItems.BASE_CIGARETTE.get()))
                    .title(Component.translatable("creative_tab.tobacco_industry"))
                    .displayItems((pParameters, pOutput) -> {

                        // TOBACCO PRODUCTION CHAIN
                        pOutput.accept(TIBlocks.ROLLING_MACHINE.get());
                        pOutput.accept(TIItems.TOBACCO_SEEDS.get());
                        pOutput.accept(TIItems.TOBACCO_LEAF.get());
                        pOutput.accept(TIItems.DRIED_TOBACCO_LEAF.get());
                        pOutput.accept(TIItems.TOBACCO_POWDER.get());

                        // CIGARETTES
                        pOutput.accept(TIItems.BASE_CIGARETTE.get());
                        pOutput.accept(TIItems.CIGARETTE_ATTUNEMENT.get());
                        pOutput.accept(TIItems.CIGARETTE_FADING.get());
                        pOutput.accept(TIItems.CIGARETTE_GLOWING.get());
                        pOutput.accept(TIItems.CIGARETTE_MAGNETISM.get());
                        pOutput.accept(TIItems.CIGARETTE_REACHING.get());
                        pOutput.accept(TIItems.CIGARETTE_RETURNING.get());
                        pOutput.accept(TIItems.CIGARETTE_WISDOM.get());
                        pOutput.accept(TIItems.CIGARETTE_HASTE.get());
                        pOutput.accept(TIItems.CIGARETTE_ABSORPTION.get());
                        pOutput.accept(TIItems.CIGARETTE_DOLPHINS_GRACE.get());
                        pOutput.accept(TIItems.CIGARETTE_NIGHT_VISION.get());
                        pOutput.accept(TIItems.CIGARETTE_INVISIBILITY.get());
                        pOutput.accept(TIItems.CIGARETTE_FIRE_RESISTANCE.get());
                        pOutput.accept(TIItems.CIGARETTE_LEAPING.get());
                        pOutput.accept(TIItems.CIGARETTE_SWIFTNESS.get());
                        pOutput.accept(TIItems.CIGARETTE_WATER_BREATHING.get());
                        pOutput.accept(TIItems.CIGARETTE_HEALING.get());
                        pOutput.accept(TIItems.CIGARETTE_REGENERATION.get());
                        pOutput.accept(TIItems.CIGARETTE_STRENGTH.get());
                        pOutput.accept(TIItems.CIGARETTE_SLOW_FALLING.get());

                        // CIGARS
                        pOutput.accept(TIItems.BASE_CIGAR.get());
                        pOutput.accept(TIItems.CIGAR_ATTUNEMENT.get());
                        pOutput.accept(TIItems.CIGAR_FADING.get());
                        pOutput.accept(TIItems.CIGAR_GLOWING.get());
                        pOutput.accept(TIItems.CIGAR_MAGNETISM.get());
                        pOutput.accept(TIItems.CIGAR_REACHING.get());
                        pOutput.accept(TIItems.CIGAR_RETURNING.get());
                        pOutput.accept(TIItems.CIGAR_WISDOM.get());
                        pOutput.accept(TIItems.CIGAR_HASTE.get());
                        pOutput.accept(TIItems.CIGAR_ABSORPTION.get());
                        pOutput.accept(TIItems.CIGAR_DOLPHINS_GRACE.get());
                        pOutput.accept(TIItems.CIGAR_NIGHT_VISION.get());
                        pOutput.accept(TIItems.CIGAR_INVISIBILITY.get());
                        pOutput.accept(TIItems.CIGAR_FIRE_RESISTANCE.get());
                        pOutput.accept(TIItems.CIGAR_LEAPING.get());
                        pOutput.accept(TIItems.CIGAR_SWIFTNESS.get());
                        pOutput.accept(TIItems.CIGAR_WATER_BREATHING.get());
                        pOutput.accept(TIItems.CIGAR_HEALING.get());
                        pOutput.accept(TIItems.CIGAR_REGENERATION.get());
                        pOutput.accept(TIItems.CIGAR_STRENGTH.get());
                        pOutput.accept(TIItems.CIGAR_SLOW_FALLING.get());

                        // MIXES
                        pOutput.accept(TIItems.BASE_MIX.get());
                        pOutput.accept(TIItems.MIX_ATTUNEMENT.get());
                        pOutput.accept(TIItems.MIX_FADING.get());
                        pOutput.accept(TIItems.MIX_GLOWING.get());
                        pOutput.accept(TIItems.MIX_MAGNETISM.get());
                        pOutput.accept(TIItems.MIX_REACHING.get());
                        pOutput.accept(TIItems.MIX_RETURNING.get());
                        pOutput.accept(TIItems.MIX_WISDOM.get());
                        pOutput.accept(TIItems.MIX_HASTE.get());
                        pOutput.accept(TIItems.MIX_ABSORPTION.get());
                        pOutput.accept(TIItems.MIX_DOLPHINS_GRACE.get());
                        pOutput.accept(TIItems.MIX_NIGHT_VISION.get());
                        pOutput.accept(TIItems.MIX_INVISIBILITY.get());
                        pOutput.accept(TIItems.MIX_FIRE_RESISTANCE.get());
                        pOutput.accept(TIItems.MIX_LEAPING.get());
                        pOutput.accept(TIItems.MIX_SWIFTNESS.get());
                        pOutput.accept(TIItems.MIX_WATER_BREATHING.get());
                        pOutput.accept(TIItems.MIX_HEALING.get());
                        pOutput.accept(TIItems.MIX_REGENERATION.get());
                        pOutput.accept(TIItems.MIX_STRENGTH.get());
                        pOutput.accept(TIItems.MIX_SLOW_FALLING.get());

                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
