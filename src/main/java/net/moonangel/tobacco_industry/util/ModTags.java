package net.moonangel.tobacco_industry.util;

import net.moonangel.tobacco_industry.TobaccoIndustry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(TobaccoIndustry.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> CIGARETTE = tag("cigarette");
        public static final TagKey<Item> CIGAR = tag("cigar");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(TobaccoIndustry.MOD_ID, name));
        }
    }
}
