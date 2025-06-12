package net.moonangel.tobacco_industry.item;

import net.moonangel.tobacco_industry.TobaccoIndustry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.moonangel.tobacco_industry.block.ModBlocks;

public class TICreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TobaccoIndustry.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TOBACCO_INDUSTRY_TAB = CREATIVE_MODE_TABS.register("tobacco_industry_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BASE_CIGARETTE.get()))
                    .title(Component.translatable("creative_tab.tobacco_industry"))
                    .displayItems((pParameters, pOutput) -> {

                        // TOBACCO PRODUCTION CHAIN
                        pOutput.accept(ModBlocks.ROLLING_MACHINE.get());
                        pOutput.accept(ModItems.TOBACCO_SEEDS.get());
                        pOutput.accept(ModItems.TOBACCO_LEAF.get());
                        pOutput.accept(ModItems.DRIED_TOBACCO_LEAF.get());
                        pOutput.accept(ModItems.TOBACCO_POWDER.get());

                        // CIGARETTES
                        pOutput.accept(ModItems.BASE_CIGARETTE.get());
                        pOutput.accept(ModItems.CIGARETTE_ATTUNEMENT.get());
                        pOutput.accept(ModItems.CIGARETTE_FADING.get());
                        pOutput.accept(ModItems.CIGARETTE_GLOWING.get());
                        pOutput.accept(ModItems.CIGARETTE_MAGNETISM.get());
                        pOutput.accept(ModItems.CIGARETTE_REACHING.get());
                        pOutput.accept(ModItems.CIGARETTE_RETURNING.get());
                        pOutput.accept(ModItems.CIGARETTE_WISDOM.get());
                        pOutput.accept(ModItems.CIGARETTE_HASTE.get());
                        pOutput.accept(ModItems.CIGARETTE_ABSORPTION.get());
                        pOutput.accept(ModItems.CIGARETTE_DOLPHINS_GRACE.get());
                        pOutput.accept(ModItems.CIGARETTE_NIGHT_VISION.get());
                        pOutput.accept(ModItems.CIGARETTE_INVISIBILITY.get());
                        pOutput.accept(ModItems.CIGARETTE_FIRE_RESISTANCE.get());
                        pOutput.accept(ModItems.CIGARETTE_LEAPING.get());
                        pOutput.accept(ModItems.CIGARETTE_SWIFTNESS.get());
                        pOutput.accept(ModItems.CIGARETTE_WATER_BREATHING.get());
                        pOutput.accept(ModItems.CIGARETTE_HEALING.get());
                        pOutput.accept(ModItems.CIGARETTE_REGENERATION.get());
                        pOutput.accept(ModItems.CIGARETTE_STRENGTH.get());
                        pOutput.accept(ModItems.CIGARETTE_SLOW_FALLING.get());

                        // CIGARS
                        pOutput.accept(ModItems.BASE_CIGAR.get());
                        pOutput.accept(ModItems.CIGAR_ATTUNEMENT.get());
                        pOutput.accept(ModItems.CIGAR_FADING.get());
                        pOutput.accept(ModItems.CIGAR_GLOWING.get());
                        pOutput.accept(ModItems.CIGAR_MAGNETISM.get());
                        pOutput.accept(ModItems.CIGAR_REACHING.get());
                        pOutput.accept(ModItems.CIGAR_RETURNING.get());
                        pOutput.accept(ModItems.CIGAR_WISDOM.get());
                        pOutput.accept(ModItems.CIGAR_HASTE.get());
                        pOutput.accept(ModItems.CIGAR_ABSORPTION.get());
                        pOutput.accept(ModItems.CIGAR_DOLPHINS_GRACE.get());
                        pOutput.accept(ModItems.CIGAR_NIGHT_VISION.get());
                        pOutput.accept(ModItems.CIGAR_INVISIBILITY.get());
                        pOutput.accept(ModItems.CIGAR_FIRE_RESISTANCE.get());
                        pOutput.accept(ModItems.CIGAR_LEAPING.get());
                        pOutput.accept(ModItems.CIGAR_SWIFTNESS.get());
                        pOutput.accept(ModItems.CIGAR_WATER_BREATHING.get());
                        pOutput.accept(ModItems.CIGAR_HEALING.get());
                        pOutput.accept(ModItems.CIGAR_REGENERATION.get());
                        pOutput.accept(ModItems.CIGAR_STRENGTH.get());
                        pOutput.accept(ModItems.CIGAR_SLOW_FALLING.get());

                        // MIXES
                        pOutput.accept(ModItems.BASE_MIX.get());
                        pOutput.accept(ModItems.MIX_ATTUNEMENT.get());
                        pOutput.accept(ModItems.MIX_FADING.get());
                        pOutput.accept(ModItems.MIX_GLOWING.get());
                        pOutput.accept(ModItems.MIX_MAGNETISM.get());
                        pOutput.accept(ModItems.MIX_REACHING.get());
                        pOutput.accept(ModItems.MIX_RETURNING.get());
                        pOutput.accept(ModItems.MIX_WISDOM.get());
                        pOutput.accept(ModItems.MIX_HASTE.get());
                        pOutput.accept(ModItems.MIX_ABSORPTION.get());
                        pOutput.accept(ModItems.MIX_DOLPHINS_GRACE.get());
                        pOutput.accept(ModItems.MIX_NIGHT_VISION.get());
                        pOutput.accept(ModItems.MIX_INVISIBILITY.get());
                        pOutput.accept(ModItems.MIX_FIRE_RESISTANCE.get());
                        pOutput.accept(ModItems.MIX_LEAPING.get());
                        pOutput.accept(ModItems.MIX_SWIFTNESS.get());
                        pOutput.accept(ModItems.MIX_WATER_BREATHING.get());
                        pOutput.accept(ModItems.MIX_HEALING.get());
                        pOutput.accept(ModItems.MIX_REGENERATION.get());
                        pOutput.accept(ModItems.MIX_STRENGTH.get());
                        pOutput.accept(ModItems.MIX_SLOW_FALLING.get());

                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
