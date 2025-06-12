package net.moonangel.tobacco_industry.datagen;

import com.simibubi.create.content.kinetics.crusher.CrushingRecipe;
import com.simibubi.create.content.kinetics.millstone.MillingRecipe;
import com.simibubi.create.content.kinetics.mixer.MixingRecipe;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;
import net.moonangel.tobacco_industry.TobaccoIndustry;
import net.moonangel.tobacco_industry.item.TIItems;

import java.util.function.Consumer;

public class TIRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public TIRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        // Dried Tobacco Leaf from smoking
        SimpleCookingRecipeBuilder.smoking(
            Ingredient.of(TIItems.TOBACCO_LEAF.get()), RecipeCategory.MISC, TIItems.DRIED_TOBACCO_LEAF.get(), 0.25f, 100)
                .unlockedBy("has_tobacco_leaf", has(TIItems.TOBACCO_LEAF.get()))
                .save(consumer, new ResourceLocation(TobaccoIndustry.MOD_ID, "dried_tobacco_leaf_from_smoking"));

        // Tobacco Powder from crushing
        new ProcessingRecipeBuilder<>(CrushingRecipe::new,
                new ResourceLocation(TobaccoIndustry.MOD_ID, "tobacco_powder"))
                .require(TIItems.DRIED_TOBACCO_LEAF.get())
                .output(TIItems.TOBACCO_POWDER.get())
                .output(0.25f, TIItems.TOBACCO_POWDER.get())
                .duration(100)
                .build(consumer);

        // Tobacco Powder from milling
        new ProcessingRecipeBuilder<>(MillingRecipe::new,
                new ResourceLocation(TobaccoIndustry.MOD_ID, "tobacco_powder"))
                .require(TIItems.DRIED_TOBACCO_LEAF.get())
                .output(TIItems.TOBACCO_POWDER.get())
                .duration(140)
                .build(consumer);

        // Simple mixes
        buildSimpleMixRecipe(TIItems.BASE_MIX.get(), TIItems.DRIED_TOBACCO_LEAF.get(), consumer);
        buildSimpleMixRecipe(TIItems.MIX_INVISIBILITY.get(), Items.GOLDEN_CARROT, consumer);
        buildSimpleMixRecipe(TIItems.MIX_FIRE_RESISTANCE.get(), Items.MAGMA_CREAM, consumer);
        buildSimpleMixRecipe(TIItems.MIX_LEAPING.get(), Items.RABBIT_FOOT, consumer);
        buildSimpleMixRecipe(TIItems.MIX_SWIFTNESS.get(), Items.SUGAR, consumer);
        buildSimpleMixRecipe(TIItems.MIX_WATER_BREATHING.get(), Items.PUFFERFISH, consumer);
        buildSimpleMixRecipe(TIItems.MIX_HEALING.get(), Items.GLISTERING_MELON_SLICE, consumer);
        buildSimpleMixRecipe(TIItems.MIX_REGENERATION.get(), Items.GHAST_TEAR, consumer);
        buildSimpleMixRecipe(TIItems.MIX_STRENGTH.get(), Items.BLAZE_POWDER, consumer);
        buildSimpleMixRecipe(TIItems.MIX_SLOW_FALLING.get(), Items.FERMENTED_SPIDER_EYE, consumer);

        buildSimpleMixRecipe(TIItems.MIX_MAGNETISM.get(), Items.REDSTONE, consumer);

        // Compound mixes
        buildCompoundMixRecipe(TIItems.MIX_NIGHT_VISION.get(), TIItems.MIX_NIGHT_VISION.get(), Items.GOLDEN_CARROT, consumer);
        buildCompoundMixRecipe(TIItems.MIX_ATTUNEMENT.get(), TIItems.MIX_NIGHT_VISION.get(), Items.AMETHYST_SHARD, consumer);
        buildCompoundMixRecipe(TIItems.MIX_FADING.get(), TIItems.MIX_INVISIBILITY.get(), Items.PHANTOM_MEMBRANE, consumer);
        buildCompoundMixRecipe(TIItems.MIX_GLOWING.get(), TIItems.MIX_NIGHT_VISION.get(), Items.GLOW_INK_SAC, consumer);
        buildCompoundMixRecipe(TIItems.MIX_REACHING.get(), TIItems.BASE_MIX.get(), Items.STICK, consumer);
        buildCompoundMixRecipe(TIItems.MIX_RETURNING.get(), TIItems.BASE_MIX.get(), Items.TOTEM_OF_UNDYING, consumer);
        buildCompoundMixRecipe(TIItems.MIX_WISDOM.get(), TIItems.MIX_STRENGTH.get(), Items.EXPERIENCE_BOTTLE, consumer);
        buildCompoundMixRecipe(TIItems.MIX_HASTE.get(), TIItems.MIX_SWIFTNESS.get(), TIItems.MIX_STRENGTH.get(), consumer);
        buildCompoundMixRecipe(TIItems.MIX_ABSORPTION.get(), TIItems.MIX_STRENGTH.get(), Items.RAW_IRON, consumer);
        buildCompoundMixRecipe(TIItems.MIX_DOLPHINS_GRACE.get(), TIItems.MIX_WATER_BREATHING.get(), TIItems.MIX_SWIFTNESS.get(), consumer);
    }

    private void buildSimpleMixRecipe(Item mixItem, Item ingredient, Consumer<FinishedRecipe> consumer) {
        String mixName = ForgeRegistries.ITEMS.getKey(mixItem).getPath();
        new ProcessingRecipeBuilder<>(MixingRecipe::new,
                new ResourceLocation(TobaccoIndustry.MOD_ID, mixName))
                .require(TIItems.TOBACCO_POWDER.get())
                .require(TIItems.TOBACCO_POWDER.get())
                .require(TIItems.TOBACCO_POWDER.get())
                .require(TIItems.TOBACCO_POWDER.get())
                .require(ingredient)
                .requiresHeat(HeatCondition.HEATED)
                .output(mixItem)
                .duration(100)
                .build(consumer);
    }

    private void buildCompoundMixRecipe(Item mixItem, Item ingredient1, Item ingredient2, Consumer<FinishedRecipe> consumer) {
        String mixName = ForgeRegistries.ITEMS.getKey(mixItem).getPath();
        new ProcessingRecipeBuilder<>(MixingRecipe::new,
                new ResourceLocation(TobaccoIndustry.MOD_ID, mixName))
                .require(ingredient1)
                .require(ingredient2)
                .requiresHeat(HeatCondition.HEATED)
                .output(mixItem)
                .duration(100)
                .build(consumer);
    }

}


