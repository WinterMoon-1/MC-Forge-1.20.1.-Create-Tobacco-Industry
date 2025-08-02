package net.moonangel.tobacco_industry.recipe;

import com.simibubi.create.compat.jei.category.sequencedAssembly.SequencedAssemblySubCategory;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.content.processing.sequenced.IAssemblyRecipe;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import net.moonangel.tobacco_industry.block.TIBlocks;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class RollingRecipe extends ProcessingRecipe<RecipeWrapper> implements IAssemblyRecipe {
    public static final IRecipeTypeInfo INSTANCE = new TIRecipes();
    public RollingRecipe(ProcessingRecipeBuilder.ProcessingRecipeParams params) {
        super(RollingRecipe.INSTANCE, params);
    }

    @Override
    protected int getMaxInputCount() {
        return 2;
    }

    @Override
    protected int getMaxOutputCount() {
        return 4;
    }

    //TODO add description in lang
    //		return VintageLang.translateDirect("recipe.assembly.coiling");
    @Override
    public Component getDescriptionForAssembly() {
        return null;
    }

    @Override
    public void addRequiredMachines(Set<ItemLike> set) {
        set.add(TIBlocks.ROLLING_MACHINE.get());
    }

    @Override
    public void addAssemblyIngredients(List<Ingredient> list) {

    }

    @Override
    protected boolean canSpecifyDuration() {
        return true;
    }

    //TODO ???
    @Override
    public Supplier<Supplier<SequencedAssemblySubCategory>> getJEISubCategory() {
        return null;
    }

    @Override
    public boolean matches(RecipeWrapper pContainer, Level pLevel) {
        if(pLevel.isClientSide()) return false;
        if (getIngredients().isEmpty()) return false;

        return ingredients.get(0).test(pContainer.getItem(0)) && ingredients.get(1).test(pContainer.getItem(1));
    }
}
