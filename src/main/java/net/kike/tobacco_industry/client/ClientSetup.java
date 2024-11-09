package net.kike.tobacco_industry.client;

import net.kike.tobacco_industry.TobaccoIndustry;
import net.kike.tobacco_industry.item.custom.CigaretteItem;
import net.kike.tobacco_industry.util.ModTags;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = TobaccoIndustry.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        System.out.println("THIS IS BEING CALLED AHHHHHHH");
        ItemColor cigaretteColorHandler = (stack, tintIndex) -> {
            if(stack.is(ModTags.Items.CIGARETTE)) {
                Item item = stack.getItem();
                if (item instanceof CigaretteItem cigaretteItem) {
                    return (tintIndex == 1) ? cigaretteItem.getStripColor() : cigaretteItem.getBaseColor();
                }
            }

            return 0xFFFFFF;
        };

        ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.CIGARETTE).forEach(item -> {
            event.register(cigaretteColorHandler, item);
        });
    }
}


