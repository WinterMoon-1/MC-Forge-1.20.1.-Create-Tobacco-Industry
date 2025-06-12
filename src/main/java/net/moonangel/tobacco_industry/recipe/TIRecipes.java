package net.moonangel.tobacco_industry.recipe;

import com.simibubi.create.content.processing.recipe.ProcessingRecipeSerializer;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;
import net.moonangel.tobacco_industry.TobaccoIndustry;

public class TIRecipes implements IRecipeTypeInfo {

    public static final ResourceLocation ID = new ResourceLocation(TobaccoIndustry.MOD_ID, "rolling");

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TobaccoIndustry.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, TobaccoIndustry.MOD_ID);

    public static final RegistryObject<RecipeSerializer<?>> SERIALIZER =
            SERIALIZERS.register("rolling", () -> new ProcessingRecipeSerializer<>(RollingRecipe::new));
    public static final RegistryObject<RecipeType<?>> TYPE =
            TYPES.register("rolling", () -> RecipeType.simple(ID));

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return TYPE.get();
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }
}
