package net.kike.tobacco_industry.client;

import net.kike.tobacco_industry.TobaccoIndustry;
import net.kike.tobacco_industry.item.ModItems;
import net.kike.tobacco_industry.item.custom.CigaretteItem;
import net.kike.tobacco_industry.util.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = TobaccoIndustry.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemColors itemColors = Minecraft.getInstance().getItemColors();
            registerItemColors(itemColors);
        });
    }

    private static void registerItemColors(ItemColors itemColors) {
        itemColors.register((stack, tintIndex) -> {
            if (stack.is(ModTags.Items.CIGARETTE)) {
                Item item = stack.getItem();
                if (item instanceof CigaretteItem cigaretteItem) {
                    return (tintIndex == 1) ? cigaretteItem.getStripColor() : cigaretteItem.getBaseColor();
                }
            }
            return 0xFFFFFF; // Default white
        }, ForgeRegistries.ITEMS.getValues().stream()
                .filter(item -> item.getDefaultInstance().is(ModTags.Items.CIGARETTE))
                .toArray(Item[]::new));
    }
}

/*
ForgeRegistries.ITEMS.getValues().stream()
                    .filter(item -> item.getDefaultInstance().is(ModTags.Items.CIGARETTE))
                    .toArray(Item[]::new));
 */