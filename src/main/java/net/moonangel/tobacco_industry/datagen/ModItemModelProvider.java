package net.moonangel.tobacco_industry.datagen;

import net.moonangel.tobacco_industry.TobaccoIndustry;
import net.moonangel.tobacco_industry.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.loaders.SeparateTransformsModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TobaccoIndustry.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        simpleItem(ModItems.TOBACCO_SEEDS);
        simpleItem(ModItems.TOBACCO_LEAF);
        simpleItem(ModItems.DRIED_TOBACCO_LEAF);
        simpleItem(ModItems.TOBACCO_POWDER);

        cigaretteTypeItem(ModItems.BASE_CIGARETTE.get(), "cigarette");
        cigaretteTypeItem(ModItems.CIGARETTE_ATTUNEMENT.get(), "cigarette");
        cigaretteTypeItem(ModItems.CIGARETTE_FADING.get(), "cigarette");
        cigaretteTypeItem(ModItems.CIGARETTE_GLOWING.get(), "cigarette");
        cigaretteTypeItem(ModItems.CIGARETTE_MAGNETISM.get(), "cigarette");
        cigaretteTypeItem(ModItems.CIGARETTE_REACHING.get(), "cigarette");
        cigaretteTypeItem(ModItems.CIGARETTE_RETURNING.get(), "cigarette");
        cigaretteTypeItem(ModItems.CIGARETTE_WISDOM.get(), "cigarette");
        cigaretteTypeItem(ModItems.CIGARETTE_HASTE.get(), "cigarette");
        cigaretteTypeItem(ModItems.CIGARETTE_ABSORPTION.get(), "cigarette");
        cigaretteTypeItem(ModItems.CIGARETTE_DOLPHINS_GRACE.get(), "cigarette");
        cigaretteTypeItem(ModItems.CIGARETTE_NIGHT_VISION.get(), "cigarette");
        cigaretteTypeItem(ModItems.CIGARETTE_INVISIBILITY.get(), "cigarette");
        cigaretteTypeItem(ModItems.CIGARETTE_FIRE_RESISTANCE.get(), "cigarette");
        cigaretteTypeItem(ModItems.CIGARETTE_LEAPING.get(), "cigarette");
        cigaretteTypeItem(ModItems.CIGARETTE_SWIFTNESS.get(), "cigarette");
        cigaretteTypeItem(ModItems.CIGARETTE_WATER_BREATHING.get(), "cigarette");
        cigaretteTypeItem(ModItems.CIGARETTE_HEALING.get(), "cigarette");
        cigaretteTypeItem(ModItems.CIGARETTE_REGENERATION.get(), "cigarette");
        cigaretteTypeItem(ModItems.CIGARETTE_STRENGTH.get(), "cigarette");
        cigaretteTypeItem(ModItems.CIGARETTE_SLOW_FALLING.get(), "cigarette");

        cigaretteTypeItem(ModItems.BASE_CIGAR.get(), "cigar");
        cigaretteTypeItem(ModItems.CIGAR_ATTUNEMENT.get(), "cigar");
        cigaretteTypeItem(ModItems.CIGAR_FADING.get(), "cigar");
        cigaretteTypeItem(ModItems.CIGAR_GLOWING.get(), "cigar");
        cigaretteTypeItem(ModItems.CIGAR_MAGNETISM.get(), "cigar");
        cigaretteTypeItem(ModItems.CIGAR_REACHING.get(), "cigar");
        cigaretteTypeItem(ModItems.CIGAR_RETURNING.get(), "cigar");
        cigaretteTypeItem(ModItems.CIGAR_WISDOM.get(), "cigar");
        cigaretteTypeItem(ModItems.CIGAR_HASTE.get(), "cigar");
        cigaretteTypeItem(ModItems.CIGAR_ABSORPTION.get(), "cigar");
        cigaretteTypeItem(ModItems.CIGAR_DOLPHINS_GRACE.get(), "cigar");
        cigaretteTypeItem(ModItems.CIGAR_NIGHT_VISION.get(), "cigar");
        cigaretteTypeItem(ModItems.CIGAR_INVISIBILITY.get(), "cigar");
        cigaretteTypeItem(ModItems.CIGAR_FIRE_RESISTANCE.get(), "cigar");
        cigaretteTypeItem(ModItems.CIGAR_LEAPING.get(), "cigar");
        cigaretteTypeItem(ModItems.CIGAR_SWIFTNESS.get(), "cigar");
        cigaretteTypeItem(ModItems.CIGAR_WATER_BREATHING.get(), "cigar");
        cigaretteTypeItem(ModItems.CIGAR_HEALING.get(), "cigar");
        cigaretteTypeItem(ModItems.CIGAR_REGENERATION.get(), "cigar");
        cigaretteTypeItem(ModItems.CIGAR_STRENGTH.get(), "cigar");
        cigaretteTypeItem(ModItems.CIGAR_SLOW_FALLING.get(), "cigar");

        mixItem(ModItems.BASE_MIX.get());
        mixItem(ModItems.MIX_ATTUNEMENT.get());
        mixItem(ModItems.MIX_FADING.get());
        mixItem(ModItems.MIX_GLOWING.get());
        mixItem(ModItems.MIX_MAGNETISM.get());
        mixItem(ModItems.MIX_REACHING.get());
        mixItem(ModItems.MIX_RETURNING.get());
        mixItem(ModItems.MIX_WISDOM.get());
        mixItem(ModItems.MIX_HASTE.get());
        mixItem(ModItems.MIX_ABSORPTION.get());
        mixItem(ModItems.MIX_DOLPHINS_GRACE.get());
        mixItem(ModItems.MIX_NIGHT_VISION.get());
        mixItem(ModItems.MIX_INVISIBILITY.get());
        mixItem(ModItems.MIX_FIRE_RESISTANCE.get());
        mixItem(ModItems.MIX_LEAPING.get());
        mixItem(ModItems.MIX_SWIFTNESS.get());
        mixItem(ModItems.MIX_WATER_BREATHING.get());
        mixItem(ModItems.MIX_HEALING.get());
        mixItem(ModItems.MIX_REGENERATION.get());
        mixItem(ModItems.MIX_STRENGTH.get());
        mixItem(ModItems.MIX_SLOW_FALLING.get());

    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(TobaccoIndustry.MOD_ID, "item/" + item.getId().getPath()));
    }

    private void cigaretteTypeItem(Item item, String type) {
        String itemName = ForgeRegistries.ITEMS.getKey(item).getPath();

        // Generate the 2D cigarette layered model (used in GUI, ground and fixed perspectives)
        withExistingParent(itemName + "_2d", mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + type + "_base"))
                .texture("layer1", modLoc("item/" + type + "_strip"));


        //Creating a reference for the existing 3d json to avoid duplication problems
        ItemModelBuilder model3D = getBuilder(type +"_3d_reference")
                .parent(new ModelFile.UncheckedModelFile(modLoc("item/" + type + "_3d")));


        // Generates the main cigarette type model with separate transforms for different perspectives
        getBuilder(itemName)
                .parent(new ModelFile.UncheckedModelFile(mcLoc("item/generated")))
                .customLoader(SeparateTransformsModelBuilder::begin)
                .base(model3D)
                .perspective(ItemDisplayContext.GUI, getBuilder(itemName + "_2d"))
                .perspective(ItemDisplayContext.GROUND, getBuilder(itemName + "_2d"))
                .perspective(ItemDisplayContext.FIXED, getBuilder(itemName + "_2d"));
    }

    private void mixItem(Item item) {
        String itemName = ForgeRegistries.ITEMS.getKey(item).getPath();

        withExistingParent(itemName, mcLoc("item/generated"))
                .texture("layer0", new ResourceLocation(TobaccoIndustry.MOD_ID, "item/mix_base"))
                .texture("layer1", new ResourceLocation(TobaccoIndustry.MOD_ID, "item/mix_strip"));
    }
}