package net.moonangel.tobacco_industry.datagen;

import net.moonangel.tobacco_industry.TobaccoIndustry;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, TobaccoIndustry.MOD_ID);
    }

    @Override
    protected void start() {

    }
}
