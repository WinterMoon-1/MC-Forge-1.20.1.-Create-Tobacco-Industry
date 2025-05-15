package net.moonangel.tobacco_industry.datagen;

import net.moonangel.tobacco_industry.TobaccoIndustry;
import net.moonangel.tobacco_industry.block.ModBlocks;
import net.moonangel.tobacco_industry.block.custom.TobaccoCropBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TobaccoIndustry.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        makeTobaccoCrop((CropBlock) ModBlocks.TOBACCO_CROP.get(), "tobacco_stage_", "tobacco_stage_");

    }

    public void makeTobaccoCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> tobaccoStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] tobaccoStates(BlockState state, CropBlock block, String modelName, String textureName) {
        int age = state.getValue(((TobaccoCropBlock) block).getAgeProperty());

        return new ConfiguredModel[]{
                new ConfiguredModel(models()
                        .singleTexture(modelName + age,
                                new ResourceLocation(TobaccoIndustry.MOD_ID, "block/crop_cross"),
                                "cross",
                                new ResourceLocation(TobaccoIndustry.MOD_ID, "block/" + textureName + age))
                        .texture("particle", new ResourceLocation(TobaccoIndustry.MOD_ID, "block/" + textureName + age))
                        .renderType("cutout"))
        };
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
