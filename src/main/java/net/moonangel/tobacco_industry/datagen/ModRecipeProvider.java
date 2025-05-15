package net.moonangel.tobacco_industry.datagen;

import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.kinetics.crusher.CrushingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.foundation.data.recipe.CreateRecipeProvider;
import com.simibubi.create.foundation.data.recipe.CrushingRecipeGen;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.moonangel.tobacco_industry.TobaccoIndustry;
import net.moonangel.tobacco_industry.item.ModItems;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        // Dried Tobacco Leaf from smoking
        SimpleCookingRecipeBuilder.smoking(
            Ingredient.of(ModItems.TOBACCO_LEAF.get()), RecipeCategory.MISC, ModItems.DRIED_TOBACCO_LEAF.get(), 0.25f, 100)
                .unlockedBy("has_tobacco_leaf", has(ModItems.TOBACCO_LEAF.get()))
                .save(consumer, new ResourceLocation(TobaccoIndustry.MOD_ID, "dried_tobacco_leaf_from_smoking"));

        // Tobacco Powder from crushing
        new ProcessingRecipeBuilder<>(CrushingRecipe::new,
                new ResourceLocation(TobaccoIndustry.MOD_ID, "tobacco_powder"))
                .require(ModItems.DRIED_TOBACCO_LEAF.get())
                .output(ModItems.TOBACCO_POWDER.get())
                .duration(100)
                .build(consumer);
    }

}


