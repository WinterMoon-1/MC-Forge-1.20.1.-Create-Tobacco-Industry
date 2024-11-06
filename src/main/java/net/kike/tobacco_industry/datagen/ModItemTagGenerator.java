package net.kike.tobacco_industry.datagen;

import net.kike.tobacco_industry.TobaccoIndustry;
import net.kike.tobacco_industry.block.ModBlocks;
import net.kike.tobacco_industry.item.ModItems;
import net.kike.tobacco_industry.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_,
                               CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, TobaccoIndustry.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTags.Items.CIGARETTE)
                .add(
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
                        ModItems.CIGARETTE_STRENGTH.get()
                );
    }
}
