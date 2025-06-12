package net.moonangel.tobacco_industry.datagen;

import net.moonangel.tobacco_industry.TobaccoIndustry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PoiTypeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TIPoiTypeTagsProvider extends PoiTypeTagsProvider {
    public TIPoiTypeTagsProvider(PackOutput p_256012_, CompletableFuture<HolderLookup.Provider> p_256617_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_256012_, p_256617_, TobaccoIndustry.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

    }
}
