package net.moonangel.tobacco_industry.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class TobaccoMixItem extends Item {
    private final int baseColour;
    private final int stripColor;
    private final Supplier<MobEffectInstance> effectSupplier;


    public TobaccoMixItem(Properties properties, Supplier<MobEffectInstance> effectSupplier, int baseColour, int stripColour) {
        super(properties);
        this.effectSupplier = effectSupplier;
        this.baseColour = baseColour;
        this.stripColor = stripColour;
    }

    public int getBaseColour() {
        return baseColour;
    }

    public int getStripColor() {
        return stripColor;
    }

    public Supplier<MobEffectInstance> getEffectSupplier() { return effectSupplier; }
}
