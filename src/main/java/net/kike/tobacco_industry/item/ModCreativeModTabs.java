package net.kike.tobacco_industry.item;

import net.kike.tobacco_industry.TobaccoIndustry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TobaccoIndustry.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TOBACCO_INDUSTRY_TAB = CREATIVE_MODE_TABS.register("tobacco_industry_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BASE_CIGARETTE.get()))
                    .title(Component.translatable("creative_tab.tobacco_industry"))
                    .displayItems((pParameters, pOutput) -> {

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

                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
