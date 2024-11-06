package net.kike.tobacco_industry.item;

import net.kike.tobacco_industry.TobaccoIndustry;
import net.kike.tobacco_industry.effect.ModEffects;
import net.kike.tobacco_industry.item.custom.CigaretteItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = TobaccoIndustry.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TobaccoIndustry.MOD_ID);

    public static final RegistryObject<Item> BASE_CIGARETTE = registerCigarette("base_cigarette", () -> null,0xFFFFFF, 0xFFFFFF);
    public static final RegistryObject<Item> CIGARETTE_HASTE = registerCigarette("cigarette_haste", () -> new MobEffectInstance(MobEffects.DIG_SPEED, 600, 0), 0xFFFFFF, 0xff0000);
    public static final RegistryObject<Item> CIGARETTE_ABSORPTION = registerCigarette("cigarette_absorption", () -> new MobEffectInstance(MobEffects.ABSORPTION, 600, 0), 0x0000ff, 0xff0000);
    public static final RegistryObject<Item> CIGARETTE_DOLPHINS_GRACE = registerCigarette("cigarette_dolphins_grace", () -> new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 600, 0), 0xFFFFFF, 0xFFFFFF);
    public static final RegistryObject<Item> CIGARETTE_NIGHT_VISION = registerCigarette("cigarette_night_vision", () -> new MobEffectInstance(MobEffects.NIGHT_VISION, 600, 0), 0xFFFFFF, 0xFFFFFF);
    public static final RegistryObject<Item> CIGARETTE_INVISIBILITY = registerCigarette("cigarette_invisibility", () -> new MobEffectInstance(MobEffects.INVISIBILITY, 600, 0), 0xFFFFFF, 0xFFFFFF);
    public static final RegistryObject<Item> CIGARETTE_FIRE_RESISTANCE = registerCigarette("cigarette_fire_resistance", () -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600, 0), 0xFFFFFF, 0xFFFFFF);
    public static final RegistryObject<Item> CIGARETTE_LEAPING = registerCigarette("cigarette_leaping", () -> new MobEffectInstance(MobEffects.JUMP, 600, 0), 0xFFFFFF, 0xFFFFFF);
    public static final RegistryObject<Item> CIGARETTE_SWIFTNESS = registerCigarette("cigarette_swiftness", () -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 0), 0xFFFFFF, 0xFFFFFF);
    public static final RegistryObject<Item> CIGARETTE_WATER_BREATHING = registerCigarette("cigarette_water_breathing", () -> new MobEffectInstance(MobEffects.WATER_BREATHING, 600, 0), 0xFFFFFF, 0xFFFFFF);
    public static final RegistryObject<Item> CIGARETTE_HEALING = registerCigarette("cigarette_healing", () -> new MobEffectInstance(MobEffects.HEAL, 600, 0), 0xFFFFFF, 0xFFFFFF);
    public static final RegistryObject<Item> CIGARETTE_REGENERATION = registerCigarette("cigarette_regeneration", () -> new MobEffectInstance(MobEffects.REGENERATION, 600, 0), 0xFFFFFF, 0xFFFFFF);
    public static final RegistryObject<Item> CIGARETTE_STRENGTH = registerCigarette("cigarette_strength", () -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0), 0xFFFFFF, 0xFFFFFF);
    public static final RegistryObject<Item> CIGARETTE_ATTUNEMENT = registerCigarette("cigarette_attunement", () -> new MobEffectInstance(ModEffects.ATTUNEMENT.get(), 600, 0), 0xFFFFFF, 0xFFFFFF);
    public static final RegistryObject<Item> CIGARETTE_FADING = registerCigarette("cigarette_fading", () -> new MobEffectInstance(ModEffects.FADING.get(), 600, 0), 0xFFFFFF, 0xFFFFFF);
    public static final RegistryObject<Item> CIGARETTE_GLOWING = registerCigarette("cigarette_glowing", () -> new MobEffectInstance(ModEffects.GLOWING.get(), 600, 0), 0xFFFFFF, 0xFFFFFF);
    public static final RegistryObject<Item> CIGARETTE_MAGNETISM = registerCigarette("cigarette_magnetism", () -> new MobEffectInstance(ModEffects.MAGNETISM.get(), 600, 0), 0xFFFFFF, 0xFFFFFF);
    public static final RegistryObject<Item> CIGARETTE_REACHING = registerCigarette("cigarette_reaching", () -> new MobEffectInstance(ModEffects.REACHING.get(), 600, 0), 0xFFFFFF, 0xFFFFFF);
    public static final RegistryObject<Item> CIGARETTE_RETURNING = registerCigarette("cigarette_returning", () -> new MobEffectInstance(ModEffects.RETURNING.get(), 600, 0), 0xFFFFFF, 0xFFFFFF);
    public static final RegistryObject<Item> CIGARETTE_WISDOM = registerCigarette("cigarette_wisdom", () -> new MobEffectInstance(ModEffects.WISDOM.get(), 600, 0), 0xFFFFFF, 0xFFFFFF);

    private static RegistryObject<Item> registerCigarette(String name, Supplier<MobEffectInstance> effectSupplier, int baseColor, int stripColor) {
        Properties properties = new Properties()
                .stacksTo(16)
                .food(new FoodProperties.Builder()
                        .nutrition(0)
                        .saturationMod(0)
                        .alwaysEat()
                        .build());

        return ITEMS.register(name, () -> new CigaretteItem(properties, effectSupplier, baseColor, stripColor));
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
