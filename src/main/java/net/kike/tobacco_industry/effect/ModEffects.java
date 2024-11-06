package net.kike.tobacco_industry.effect;

import net.kike.tobacco_industry.TobaccoIndustry;
import net.kike.tobacco_industry.effect.custom.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TobaccoIndustry.MOD_ID);

    public static final RegistryObject<MobEffect> ATTUNEMENT = MOB_EFFECTS.register("attunement",
            () -> new AttunementEffect(MobEffectCategory.BENEFICIAL, 0xFF0000));

    public static final RegistryObject<MobEffect> FADING = MOB_EFFECTS.register("fading",
            () -> new FadingEffect(MobEffectCategory.BENEFICIAL, 0xFF0000));

    public static final RegistryObject<MobEffect> GLOWING = MOB_EFFECTS.register("glowing",
            () -> new GlowingEffect(MobEffectCategory.BENEFICIAL, 0xFF0000));

    public static final RegistryObject<MobEffect> MAGNETISM = MOB_EFFECTS.register("magnetism",
            () -> new MagnetismEffect(MobEffectCategory.BENEFICIAL, 0xFF0000));

    public static final RegistryObject<MobEffect> REACHING = MOB_EFFECTS.register("reaching",
            () -> new ReachingEffect(MobEffectCategory.BENEFICIAL, 0xFF0000));

    public static final RegistryObject<MobEffect> RETURNING = MOB_EFFECTS.register("returning",
            () -> new ReturningEffect(MobEffectCategory.BENEFICIAL, 0xFF0000));

    public static final RegistryObject<MobEffect> WISDOM = MOB_EFFECTS.register("wisdom",
            () -> new WisdomEffect(MobEffectCategory.BENEFICIAL, 0xFF0000));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
