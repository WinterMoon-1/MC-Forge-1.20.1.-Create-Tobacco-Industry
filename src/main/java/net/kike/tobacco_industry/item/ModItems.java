package net.kike.tobacco_industry.item;

import net.kike.tobacco_industry.TobaccoIndustry;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TobaccoIndustry.MOD_ID);

    public static final RegistryObject<Item> BASE_CIGARETTE = ITEMS.register("base_cigarette",
            () -> new PotionItem(new Item.Properties().stacksTo(16)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
