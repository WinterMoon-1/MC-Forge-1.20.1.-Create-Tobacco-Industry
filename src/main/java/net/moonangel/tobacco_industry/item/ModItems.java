package net.moonangel.tobacco_industry.item;

import com.tterrag.registrate.util.entry.ItemEntry;
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
import net.moonangel.tobacco_industry.item.custom.TobaccoMixItem;
import net.moonangel.tobacco_industry.util.ModModelUtils;
import net.moonangel.tobacco_industry.util.ModTags;
import org.checkerframework.checker.units.qual.C;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = TobaccoIndustry.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TobaccoIndustry.MOD_ID);

    public static final ItemEntry<ItemNameBlockItem> TOBACCO_SEEDS =
            TobaccoIndustry.REGISTRATE.item("tobacco_seeds", props -> new ItemNameBlockItem(ModBlocks.TOBACCO_CROP.get(), props)).register();
    public static final ItemEntry<Item> TOBACCO_LEAF =
            TobaccoIndustry.REGISTRATE.item("tobacco_leaf", Item::new).register();
    public static final ItemEntry<Item> DRIED_TOBACCO_LEAF =
            TobaccoIndustry.REGISTRATE.item("dried_tobacco_leaf", Item::new).register();
    public static final ItemEntry<Item> TOBACCO_POWDER =
            TobaccoIndustry.REGISTRATE.item("tobacco_powder", Item::new).register();

    public static final ItemEntry<CigaretteItem> BASE_CIGARETTE =
            cigarette("base_cigarette", () -> null, 0xFFFFFF, 0xDE832F);
    public static final ItemEntry<CigaretteItem> CIGARETTE_HASTE =
            cigarette("cigarette_haste", () -> new MobEffectInstance(MobEffects.DIG_SPEED, 600, 0), 0xFFFFFF, 0xFFE11A);
    public static final ItemEntry<CigaretteItem> CIGARETTE_ABSORPTION =
            cigarette("cigarette_absorption", () -> new MobEffectInstance(MobEffects.ABSORPTION, 600, 0), 0xFFFFFF, 0x00ABD8);
    public static final ItemEntry<CigaretteItem> CIGARETTE_DOLPHINS_GRACE =
            cigarette("cigarette_dolphins_grace", () -> new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 600, 0), 0xFFFFFF, 0xFFFFFF);
    public static final ItemEntry<CigaretteItem> CIGARETTE_NIGHT_VISION =
            cigarette("cigarette_night_vision", () -> new MobEffectInstance(MobEffects.NIGHT_VISION, 600, 0), 0xFFFFFF, 0xC2FF66);
    public static final ItemEntry<CigaretteItem> CIGARETTE_INVISIBILITY =
            cigarette("cigarette_invisibility", () -> new MobEffectInstance(MobEffects.INVISIBILITY, 600, 0), 0xFFFFFF, 0xF6F6F6);
    public static final ItemEntry<CigaretteItem> CIGARETTE_FIRE_RESISTANCE =
            cigarette("cigarette_fire_resistance", () -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600, 0), 0xFFFFFF, 0xFF9900);
    public static final ItemEntry<CigaretteItem> CIGARETTE_LEAPING =
            cigarette("cigarette_leaping", () -> new MobEffectInstance(MobEffects.JUMP, 600, 0), 0xFFFFFF, 0xFDFF84);
    public static final ItemEntry<CigaretteItem> CIGARETTE_SWIFTNESS =
            cigarette("cigarette_swiftness", () -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 0), 0xFFFFFF, 0x33EBFF);
    public static final ItemEntry<CigaretteItem> CIGARETTE_WATER_BREATHING =
            cigarette("cigarette_water_breathing", () -> new MobEffectInstance(MobEffects.WATER_BREATHING, 600, 0), 0xFFFFFF, 0x98DAC0);
    public static final ItemEntry<CigaretteItem> CIGARETTE_HEALING =
            cigarette("cigarette_healing", () -> new MobEffectInstance(MobEffects.HEAL, 600, 0), 0xFFFFFF, 0xF82423);
    public static final ItemEntry<CigaretteItem> CIGARETTE_REGENERATION =
            cigarette("cigarette_regeneration", () -> new MobEffectInstance(MobEffects.REGENERATION, 600, 0), 0xFFFFFF, 0xCD5CAB);
    public static final ItemEntry<CigaretteItem> CIGARETTE_STRENGTH =
            cigarette("cigarette_strength", () -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0), 0xFFFFFF, 0xFFC700);
    public static final ItemEntry<CigaretteItem> CIGARETTE_SLOW_FALLING =
            cigarette("cigarette_slow_falling", () -> new MobEffectInstance(MobEffects.SLOW_FALLING, 600, 0), 0xFFFFFF, 0xF3CFB9);
    public static final ItemEntry<CigaretteItem> CIGARETTE_ATTUNEMENT =
            cigarette("cigarette_attunement", () -> new MobEffectInstance(ModEffects.ATTUNEMENT.get(), 600, 0), 0xFFFFFF, 0xB088E7);
    public static final ItemEntry<CigaretteItem> CIGARETTE_FADING =
            cigarette("cigarette_fading", () -> new MobEffectInstance(ModEffects.FADING.get(), 600, 0), 0xFFFFFF, 0xD0F1F1);
    public static final ItemEntry<CigaretteItem> CIGARETTE_GLOWING =
            cigarette("cigarette_glowing", () -> new MobEffectInstance(ModEffects.GLOWING.get(), 600, 0), 0xFFFFFF, 0x7BF4B8);
    public static final ItemEntry<CigaretteItem> CIGARETTE_MAGNETISM =
            cigarette("cigarette_magnetism", () -> new MobEffectInstance(ModEffects.MAGNETISM.get(), 600, 0), 0xFFFFFF, 0xA6BFBB);
    public static final ItemEntry<CigaretteItem> CIGARETTE_REACHING =
            cigarette("cigarette_reaching", () -> new MobEffectInstance(ModEffects.REACHING.get(), 600, 0), 0xFFFFFF, 0x8A6628);
    public static final ItemEntry<CigaretteItem> CIGARETTE_RETURNING =
            cigarette("cigarette_returning", () -> new MobEffectInstance(ModEffects.RETURNING.get(), 600, 0), 0xFFFFFF, 0xE7D684);
    public static final ItemEntry<CigaretteItem> CIGARETTE_WISDOM =
            cigarette("cigarette_wisdom", () -> new MobEffectInstance(ModEffects.WISDOM.get(), 600, 0), 0xFFFFFF, 0xE7D684);


    public static final ItemEntry<CigarItem> BASE_CIGAR =
            cigar("base_cigar", () -> null, 0xFFFFFF, 0xDE832F);
    public static final ItemEntry<CigarItem> CIGAR_HASTE =
            cigar("cigar_haste", () -> new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 0), 0xFFFFFF, 0xFFE11A);
    public static final ItemEntry<CigarItem> CIGAR_ABSORPTION =
            cigar("cigar_absorption", () -> new MobEffectInstance(MobEffects.ABSORPTION, 1200, 0), 0xFFFFFF, 0x00ABD8);
    public static final ItemEntry<CigarItem> CIGAR_DOLPHINS_GRACE =
            cigar("cigar_dolphins_grace", () -> new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 1200, 0), 0xFFFFFF, 0xFFFFFF);
    public static final ItemEntry<CigarItem> CIGAR_NIGHT_VISION =
            cigar("cigar_night_vision", () -> new MobEffectInstance(MobEffects.NIGHT_VISION, 1200, 0), 0xFFFFFF, 0xC2FF66);
    public static final ItemEntry<CigarItem> CIGAR_INVISIBILITY =
            cigar("cigar_invisibility", () -> new MobEffectInstance(MobEffects.INVISIBILITY, 1200, 0), 0xFFFFFF, 0xF6F6F6);
    public static final ItemEntry<CigarItem> CIGAR_FIRE_RESISTANCE =
            cigar("cigar_fire_resistance", () -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0), 0xFFFFFF, 0xFF9900);
    public static final ItemEntry<CigarItem> CIGAR_LEAPING =
            cigar("cigar_leaping", () -> new MobEffectInstance(MobEffects.JUMP, 1200, 0), 0xFFFFFF, 0xFDFF84);
    public static final ItemEntry<CigarItem> CIGAR_SWIFTNESS =
            cigar("cigar_swiftness", () -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 0), 0xFFFFFF, 0x33EBFF);
    public static final ItemEntry<CigarItem> CIGAR_WATER_BREATHING =
            cigar("cigar_water_breathing", () -> new MobEffectInstance(MobEffects.WATER_BREATHING, 1200, 0), 0xFFFFFF, 0x98DAC0);
    public static final ItemEntry<CigarItem> CIGAR_HEALING =
            cigar("cigar_healing", () -> new MobEffectInstance(MobEffects.HEAL, 1200, 0), 0xFFFFFF, 0xF82423);
    public static final ItemEntry<CigarItem> CIGAR_REGENERATION =
            cigar("cigar_regeneration", () -> new MobEffectInstance(MobEffects.REGENERATION, 1200, 0), 0xFFFFFF, 0xCD5CAB);
    public static final ItemEntry<CigarItem> CIGAR_STRENGTH =
            cigar("cigar_strength", () -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 0), 0xFFFFFF, 0xFFC700);
    public static final ItemEntry<CigarItem> CIGAR_SLOW_FALLING =
            cigar("cigar_slow_falling", () -> new MobEffectInstance(MobEffects.SLOW_FALLING, 1200, 0), 0xFFFFFF, 0xF3CFB9);
    public static final ItemEntry<CigarItem> CIGAR_ATTUNEMENT =
            cigar("cigar_attunement", () -> new MobEffectInstance(ModEffects.ATTUNEMENT.get(), 1200, 0), 0xFFFFFF, 0xB088E7);
    public static final ItemEntry<CigarItem> CIGAR_FADING =
            cigar("cigar_fading", () -> new MobEffectInstance(ModEffects.FADING.get(), 1200, 0), 0xFFFFFF, 0xD0F1F1);
    public static final ItemEntry<CigarItem> CIGAR_GLOWING =
            cigar("cigar_glowing", () -> new MobEffectInstance(ModEffects.GLOWING.get(), 1200, 0), 0xFFFFFF, 0x7BF4B8);
    public static final ItemEntry<CigarItem> CIGAR_MAGNETISM =
            cigar("cigar_magnetism", () -> new MobEffectInstance(ModEffects.MAGNETISM.get(), 1200, 0), 0xFFFFFF, 0xA6BFBB);
    public static final ItemEntry<CigarItem> CIGAR_REACHING =
            cigar("cigar_reaching", () -> new MobEffectInstance(ModEffects.REACHING.get(), 1200, 0), 0xFFFFFF, 0x8A6628);
    public static final ItemEntry<CigarItem> CIGAR_RETURNING =
            cigar("cigar_returning", () -> new MobEffectInstance(ModEffects.RETURNING.get(), 1200, 0), 0xFFFFFF, 0xE7D684);
    public static final ItemEntry<CigarItem> CIGAR_WISDOM =
            cigar("cigar_wisdom", () -> new MobEffectInstance(ModEffects.WISDOM.get(), 1200, 0), 0xFFFFFF, 0xE7D684);

    public static final ItemEntry<TobaccoMixItem> BASE_MIX =
            mix("base_mix", () -> null, 0xFFFFFF, 0xDE832F);
    public static final ItemEntry<TobaccoMixItem> MIX_HASTE =
            mix("mix_haste", () -> new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 0), 0xFFFFFF, 0xFFE11A);
    public static final ItemEntry<TobaccoMixItem> MIX_ABSORPTION =
            mix("mix_absorption", () -> new MobEffectInstance(MobEffects.ABSORPTION, 1200, 0), 0xFFFFFF, 0x00ABD8);
    public static final ItemEntry<TobaccoMixItem> MIX_DOLPHINS_GRACE =
            mix("mix_dolphins_grace", () -> new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 1200, 0), 0xFFFFFF, 0xFFFFFF);
    public static final ItemEntry<TobaccoMixItem> MIX_NIGHT_VISION =
            mix("mix_night_vision", () -> new MobEffectInstance(MobEffects.NIGHT_VISION, 1200, 0), 0xFFFFFF, 0xC2FF66);
    public static final ItemEntry<TobaccoMixItem> MIX_INVISIBILITY =
            mix("mix_invisibility", () -> new MobEffectInstance(MobEffects.INVISIBILITY, 1200, 0), 0xFFFFFF, 0xF6F6F6);
    public static final ItemEntry<TobaccoMixItem> MIX_FIRE_RESISTANCE =
            mix("mix_fire_resistance", () -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0), 0xFFFFFF, 0xFF9900);
    public static final ItemEntry<TobaccoMixItem> MIX_LEAPING =
            mix("mix_leaping", () -> new MobEffectInstance(MobEffects.JUMP, 1200, 0), 0xFFFFFF, 0xFDFF84);
    public static final ItemEntry<TobaccoMixItem> MIX_SWIFTNESS =
            mix("mix_swiftness", () -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 0), 0xFFFFFF, 0x33EBFF);
    public static final ItemEntry<TobaccoMixItem> MIX_WATER_BREATHING =
            mix("mix_water_breathing", () -> new MobEffectInstance(MobEffects.WATER_BREATHING, 1200, 0), 0xFFFFFF, 0x98DAC0);
    public static final ItemEntry<TobaccoMixItem> MIX_HEALING =
            mix("mix_healing", () -> new MobEffectInstance(MobEffects.HEAL, 1200, 0), 0xFFFFFF, 0xF82423);
    public static final ItemEntry<TobaccoMixItem> MIX_REGENERATION =
            mix("mix_regeneration", () -> new MobEffectInstance(MobEffects.REGENERATION, 1200, 0), 0xFFFFFF, 0xCD5CAB);
    public static final ItemEntry<TobaccoMixItem> MIX_STRENGTH =
            mix("mix_strength", () -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 0), 0xFFFFFF, 0xFFC700);
    public static final ItemEntry<TobaccoMixItem> MIX_SLOW_FALLING =
            mix("mix_slow_falling", () -> new MobEffectInstance(MobEffects.SLOW_FALLING, 1200, 0), 0xFFFFFF, 0xF3CFB9);
    public static final ItemEntry<TobaccoMixItem> MIX_ATTUNEMENT =
            mix("mix_attunement", () -> new MobEffectInstance(ModEffects.ATTUNEMENT.get(), 1200, 0), 0xFFFFFF, 0xB088E7);
    public static final ItemEntry<TobaccoMixItem> MIX_FADING =
            mix("mix_fading", () -> new MobEffectInstance(ModEffects.FADING.get(), 1200, 0), 0xFFFFFF, 0xD0F1F1);
    public static final ItemEntry<TobaccoMixItem> MIX_GLOWING =
            mix("mix_glowing", () -> new MobEffectInstance(ModEffects.GLOWING.get(), 1200, 0), 0xFFFFFF, 0x7BF4B8);
    public static final ItemEntry<TobaccoMixItem> MIX_MAGNETISM =
            mix("mix_magnetism", () -> new MobEffectInstance(ModEffects.MAGNETISM.get(), 1200, 0), 0xFFFFFF, 0xA6BFBB);
    public static final ItemEntry<TobaccoMixItem> MIX_REACHING =
            mix("mix_reaching", () -> new MobEffectInstance(ModEffects.REACHING.get(), 1200, 0), 0xFFFFFF, 0x8A6628);
    public static final ItemEntry<TobaccoMixItem> MIX_RETURNING =
            mix("mix_returning", () -> new MobEffectInstance(ModEffects.RETURNING.get(), 1200, 0), 0xFFFFFF, 0xE7D684);
    public static final ItemEntry<TobaccoMixItem> MIX_WISDOM =
            mix("mix_wisdom", () -> new MobEffectInstance(ModEffects.WISDOM.get(), 1200, 0), 0xFFFFFF, 0xE7D684);

    private static ItemEntry<CigaretteItem> cigarette(String itemName, Supplier<MobEffectInstance> effectSupplier, int baseColour, int stripColour) {
        String langName = formatLangName(itemName);
        Properties properties = new Properties()
                .stacksTo(16)
                .food(new FoodProperties.Builder()
                        .nutrition(0)
                        .saturationMod(0)
                        .alwaysEat()
                        .build());

        return TobaccoIndustry.REGISTRATE
                .item(itemName, props -> new CigaretteItem(properties, effectSupplier, baseColour, stripColour))
                .tag(ModTags.Items.CIGARETTE)
                .lang(langName)
                .model((ctx, prov) -> ModModelUtils.cigaretteTypeItem(ctx, prov, "cigarette"))
                .register();
    }

    private static ItemEntry<CigarItem> cigar(String itemName, Supplier<MobEffectInstance> effectSupplier, int baseColour, int stripColour) {
        String langName = formatLangName(itemName);
        Properties properties = new Properties()
                .stacksTo(1)
                .durability(20)
                .food(new FoodProperties.Builder()
                        .nutrition(0)
                        .saturationMod(0)
                        .alwaysEat()
                        .build());

        return TobaccoIndustry.REGISTRATE
                .item(itemName, props -> new CigarItem(properties, effectSupplier, baseColour, stripColour))
                .tag(ModTags.Items.CIGAR)
                .lang(langName)
                .model((ctx, prov) -> ModModelUtils.cigaretteTypeItem(ctx, prov, "cigar"))
                .register();
    }

    private static ItemEntry<TobaccoMixItem> mix(String itemName, Supplier<MobEffectInstance> effectSupplier, int baseColour, int stripColour) {
        String langName = formatLangName(itemName);
        Properties properties = new Properties()
                .stacksTo(16);

        return TobaccoIndustry.REGISTRATE
                .item(itemName, props -> new TobaccoMixItem(properties, effectSupplier, baseColour, stripColour))
                .tag(ModTags.Items.TOBACCO_MIX)
                .lang(langName)
                .model((ctx, prov) -> ModModelUtils.cigaretteTypeItem(ctx, prov, "mix"))
                .register();
    }

    // Needed because the effects are not yet registered at this stage
    private static String formatLangName(String itemName) {
        if (itemName.startsWith("base_")) return capitalizeWords(itemName.substring("base_".length()));

        int firstUnderscore = itemName.indexOf("_");
        String type = itemName.substring(0, firstUnderscore);
        String effectName = itemName.substring(firstUnderscore + 1).replace('_', ' ');

        return capitalizeWords(effectName) + " " + capitalizeWords(type);
    }

    private static String capitalizeWords(String input) {
        String[] parts = input.split(" ");
        return Arrays.stream(parts)
                .map(part -> part.isEmpty() ? part :
                        Character.toUpperCase(part.charAt(0)) + part.substring(1))
                .collect(Collectors.joining(" "));
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
