package net.moonangel.tobacco_industry.util;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.loaders.SeparateTransformsModelBuilder;
import net.moonangel.tobacco_industry.TobaccoIndustry;
import net.moonangel.tobacco_industry.block.custom.TobaccoCropBlock;

public class TIModelUtils {
    public static <T extends Item> void cigaretteTypeItem(DataGenContext<Item, T> ctx, RegistrateItemModelProvider prov, String type) {
        String itemName = ctx.getId().getPath();

        // 2D layered model (GUI, ground, fixed)
        prov.withExistingParent(itemName + "_2d", prov.mcLoc("item/generated"))
                .texture("layer0", prov.modLoc("item/" + type + "_base"))
                .texture("layer1", prov.modLoc("item/" + type + "_strip"));


        // Reference for the existing 3d json
        ItemModelBuilder model3D = prov.getBuilder(type +"_3d_reference")
                .parent(new ModelFile.UncheckedModelFile(prov.modLoc("item/" + type + "_3d")));


        // Main model with perspective overrides
        prov.getBuilder(itemName)
                .customLoader(SeparateTransformsModelBuilder::begin)
                .base(model3D)
                .perspective(ItemDisplayContext.GUI, prov.getBuilder(itemName + "_2d"))
                .perspective(ItemDisplayContext.GROUND, prov.getBuilder(itemName + "_2d"))
                .perspective(ItemDisplayContext.FIXED, prov.getBuilder(itemName + "_2d"));
    }

    public static void makeTobaccoCrop(Block block, BlockStateProvider prov, String baseName) {
        prov.getVariantBuilder(block).forAllStates(state -> {
            int age = state.getValue(((TobaccoCropBlock) block).getAgeProperty());

            return new ConfiguredModel[]{
                    new ConfiguredModel(prov.models()
                            .singleTexture(baseName + age,
                                    new ResourceLocation(TobaccoIndustry.MOD_ID, "block/crop_cross"),
                                    "cross",
                                    new ResourceLocation(TobaccoIndustry.MOD_ID, "block/" + baseName + age))
                            .texture("particle", new ResourceLocation(TobaccoIndustry.MOD_ID, "block/" + baseName + age))
                            .renderType("cutout"))
            };
        });
    }

}
