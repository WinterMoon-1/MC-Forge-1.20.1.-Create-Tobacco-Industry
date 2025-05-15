package net.moonangel.tobacco_industry.item;

import net.moonangel.tobacco_industry.TobaccoIndustry;
import net.moonangel.tobacco_industry.block.ModBlocks;
import net.moonangel.tobacco_industry.effect.ModEffects;
import net.moonangel.tobacco_industry.item.custom.CigarItem;
import net.moonangel.tobacco_industry.item.custom.CigaretteItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = TobaccoIndustry.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TobaccoIndustry.MOD_ID);

    public static final RegistryObject<Item> TOBACCO_SEEDS = ITEMS.register("tobacco_seeds",
            () -> new ItemNameBlockItem(ModBlocks.TOBACCO_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> TOBACCO_LEAF = ITEMS.register("tobacco_leaf",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DRIED_TOBACCO_LEAF = ITEMS.register("dried_tobacco_leaf",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TOBACCO_POWDER = ITEMS.register("tobacco_powder",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BASE_CIGARETTE = registerCigarette("base_cigarette", () -> null,0xFFFFFF, 0xDE832F);
    public static final RegistryObject<Item> CIGARETTE_HASTE = registerCigarette("cigarette_haste", () -> new MobEffectInstance(MobEffects.DIG_SPEED, 600, 0), 0xFFFFFF, 0xFFE11A);
    public static final RegistryObject<Item> CIGARETTE_ABSORPTION = registerCigarette("cigarette_absorption", () -> new MobEffectInstance(MobEffects.ABSORPTION, 600, 0), 0xFFFFFF, 0x00ABD8);
    public static final RegistryObject<Item> CIGARETTE_DOLPHINS_GRACE = registerCigarette("cigarette_dolphins_grace", () -> new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 600, 0), 0xFFFFFF, 0xFFFFFF);
    public static final RegistryObject<Item> CIGARETTE_NIGHT_VISION = registerCigarette("cigarette_night_vision", () -> new MobEffectInstance(MobEffects.NIGHT_VISION, 600, 0), 0xFFFFFF, 0xC2FF66);
    public static final RegistryObject<Item> CIGARETTE_INVISIBILITY = registerCigarette("cigarette_invisibility", () -> new MobEffectInstance(MobEffects.INVISIBILITY, 600, 0), 0xFFFFFF, 0xF6F6F6);
    public static final RegistryObject<Item> CIGARETTE_FIRE_RESISTANCE = registerCigarette("cigarette_fire_resistance", () -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600, 0), 0xFFFFFF, 0xFF9900);
    public static final RegistryObject<Item> CIGARETTE_LEAPING = registerCigarette("cigarette_leaping", () -> new MobEffectInstance(MobEffects.JUMP, 600, 0), 0xFFFFFF, 0xFDFF84);
    public static final RegistryObject<Item> CIGARETTE_SWIFTNESS = registerCigarette("cigarette_swiftness", () -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 0), 0xFFFFFF, 0x33EBFF);
    public static final RegistryObject<Item> CIGARETTE_WATER_BREATHING = registerCigarette("cigarette_water_breathing", () -> new MobEffectInstance(MobEffects.WATER_BREATHING, 600, 0), 0xFFFFFF, 0x98DAC0);
    public static final RegistryObject<Item> CIGARETTE_HEALING = registerCigarette("cigarette_healing", () -> new MobEffectInstance(MobEffects.HEAL, 600, 0), 0xFFFFFF, 0xF82423);
    public static final RegistryObject<Item> CIGARETTE_REGENERATION = registerCigarette("cigarette_regeneration", () -> new MobEffectInstance(MobEffects.REGENERATION, 600, 0), 0xFFFFFF, 0xCD5CAB);
    public static final RegistryObject<Item> CIGARETTE_STRENGTH = registerCigarette("cigarette_strength", () -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0), 0xFFFFFF, 0xFFC700);
    public static final RegistryObject<Item> CIGARETTE_SLOW_FALLING = registerCigarette("cigarette_slow_falling", () -> new MobEffectInstance(MobEffects.SLOW_FALLING, 600, 0), 0xFFFFFF, 0xF3CFB9);
    public static final RegistryObject<Item> CIGARETTE_ATTUNEMENT = registerCigarette("cigarette_attunement", () -> new MobEffectInstance(ModEffects.ATTUNEMENT.get(), 600, 0), 0xFFFFFF, 0xB088E7);
    public static final RegistryObject<Item> CIGARETTE_FADING = registerCigarette("cigarette_fading", () -> new MobEffectInstance(ModEffects.FADING.get(), 600, 0), 0xFFFFFF, 0xD0F1F1);
    public static final RegistryObject<Item> CIGARETTE_GLOWING = registerCigarette("cigarette_glowing", () -> new MobEffectInstance(ModEffects.GLOWING.get(), 600, 0), 0xFFFFFF, 0x7BF4B8);
    public static final RegistryObject<Item> CIGARETTE_MAGNETISM = registerCigarette("cigarette_magnetism", () -> new MobEffectInstance(ModEffects.MAGNETISM.get(), 600, 0), 0xFFFFFF, 0xA6BFBB);
    public static final RegistryObject<Item> CIGARETTE_REACHING = registerCigarette("cigarette_reaching", () -> new MobEffectInstance(ModEffects.REACHING.get(), 600, 0), 0xFFFFFF, 0x8A6628);
    public static final RegistryObject<Item> CIGARETTE_RETURNING = registerCigarette("cigarette_returning", () -> new MobEffectInstance(ModEffects.RETURNING.get(), 600, 0), 0xFFFFFF, 0xE7D684);
    public static final RegistryObject<Item> CIGARETTE_WISDOM = registerCigarette("cigarette_wisdom", () -> new MobEffectInstance(ModEffects.WISDOM.get(), 600, 0), 0xFFFFFF, 0xE7D684);

    public static final RegistryObject<Item>  BASE_CIGAR = registerCigar("base_cigar", () -> null, 0xFFFFFF, 0xDE832F);
    public static final RegistryObject<Item> CIGAR_HASTE = registerCigar("cigar_haste", () -> new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 0), 0xFFFFFF, 0xFFE11A);
    public static final RegistryObject<Item> CIGAR_ABSORPTION = registerCigar("cigar_absorption", () -> new MobEffectInstance(MobEffects.ABSORPTION, 1200, 0), 0xFFFFFF, 0x00ABD8);
    public static final RegistryObject<Item> CIGAR_DOLPHINS_GRACE = registerCigar("cigar_dolphins_grace", () -> new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 1200, 0), 0xFFFFFF, 0xFFFFFF);
    public static final RegistryObject<Item> CIGAR_NIGHT_VISION = registerCigar("cigar_night_vision", () -> new MobEffectInstance(MobEffects.NIGHT_VISION, 1200, 0), 0xFFFFFF, 0xC2FF66);
    public static final RegistryObject<Item> CIGAR_INVISIBILITY = registerCigar("cigar_invisibility", () -> new MobEffectInstance(MobEffects.INVISIBILITY, 1200, 0), 0xFFFFFF, 0xF6F6F6);
    public static final RegistryObject<Item> CIGAR_FIRE_RESISTANCE = registerCigar("cigar_fire_resistance", () -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0), 0xFFFFFF, 0xFF9900);
    public static final RegistryObject<Item> CIGAR_LEAPING = registerCigar("cigar_leaping", () -> new MobEffectInstance(MobEffects.JUMP, 1200, 0), 0xFFFFFF, 0xFDFF84);
    public static final RegistryObject<Item> CIGAR_SWIFTNESS = registerCigar("cigar_swiftness", () -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 0), 0xFFFFFF, 0x33EBFF);
    public static final RegistryObject<Item> CIGAR_WATER_BREATHING = registerCigar("cigar_water_breathing", () -> new MobEffectInstance(MobEffects.WATER_BREATHING, 1200, 0), 0xFFFFFF, 0x98DAC0);
    public static final RegistryObject<Item> CIGAR_HEALING = registerCigar("cigar_healing", () -> new MobEffectInstance(MobEffects.HEAL, 1200, 0), 0xFFFFFF, 0xF82423);
    public static final RegistryObject<Item> CIGAR_REGENERATION = registerCigar("cigar_regeneration", () -> new MobEffectInstance(MobEffects.REGENERATION, 1200, 0), 0xFFFFFF, 0xCD5CAB);
    public static final RegistryObject<Item> CIGAR_STRENGTH = registerCigar("cigar_strength", () -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 0), 0xFFFFFF, 0xFFC700);
    public static final RegistryObject<Item> CIGAR_SLOW_FALLING = registerCigar("cigar_slow_falling", () -> new MobEffectInstance(MobEffects.SLOW_FALLING, 1200, 0), 0xFFFFFF, 0xF3CFB9);
    public static final RegistryObject<Item> CIGAR_ATTUNEMENT = registerCigar("cigar_attunement", () -> new MobEffectInstance(ModEffects.ATTUNEMENT.get(), 1200, 0), 0xFFFFFF, 0xB088E7);
    public static final RegistryObject<Item> CIGAR_FADING = registerCigar("cigar_fading", () -> new MobEffectInstance(ModEffects.FADING.get(), 1200, 0), 0xFFFFFF, 0xD0F1F1);
    public static final RegistryObject<Item> CIGAR_GLOWING = registerCigar("cigar_glowing", () -> new MobEffectInstance(ModEffects.GLOWING.get(), 1200, 0), 0xFFFFFF, 0x7BF4B8);
    public static final RegistryObject<Item> CIGAR_MAGNETISM = registerCigar("cigar_magnetism", () -> new MobEffectInstance(ModEffects.MAGNETISM.get(), 1200, 0), 0xFFFFFF, 0xA6BFBB);
    public static final RegistryObject<Item> CIGAR_REACHING = registerCigar("cigar_reaching", () -> new MobEffectInstance(ModEffects.REACHING.get(), 1200, 0), 0xFFFFFF, 0x8A6628);
    public static final RegistryObject<Item> CIGAR_RETURNING = registerCigar("cigar_returning", () -> new MobEffectInstance(ModEffects.RETURNING.get(), 1200, 0), 0xFFFFFF, 0xE7D684);
    public static final RegistryObject<Item> CIGAR_WISDOM = registerCigar("cigar_wisdom", () -> new MobEffectInstance(ModEffects.WISDOM.get(), 1200, 0), 0xFFFFFF, 0xE7D684);

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

    private static RegistryObject<Item> registerCigar(String name, Supplier<MobEffectInstance> effectSupplier, int baseColor, int stripColor) {
        Properties properties = new Properties()
                .stacksTo(1)
                .durability(20)
                .food(new FoodProperties.Builder()
                        .nutrition(0)
                        .saturationMod(0)
                        .alwaysEat()
                        .build());

        return ITEMS.register(name, () -> new CigarItem(properties, effectSupplier, baseColor, stripColor));
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
