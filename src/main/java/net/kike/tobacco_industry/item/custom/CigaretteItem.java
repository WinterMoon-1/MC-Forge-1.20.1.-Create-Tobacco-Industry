package net.kike.tobacco_industry.item.custom;

import net.kike.tobacco_industry.animation.ModAnimations;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.function.Supplier;

public class CigaretteItem extends Item {
    private final int baseColor;
    private final int stripColor;
    private final Supplier<MobEffectInstance> effectSupplier;
    int USE_DURATION = 32;

    public CigaretteItem(Properties properties, Supplier<MobEffectInstance> effectSupplier, int baseColor, int stripColor) {
        super(properties);
        this.effectSupplier = effectSupplier;
        this.baseColor = baseColor;
        this.stripColor = stripColor;
    }

    public void applyEffect(Player player) {
        MobEffectInstance effect = effectSupplier.get();
        if (effect != null) {
            player.addEffect(new MobEffectInstance(effect));
        }
    }

    public int getBaseColor() {
        return baseColor;
    }

    public int getStripColor() {
        return stripColor;
    }


    @Override
    public int getUseDuration(ItemStack pStack) {
        return USE_DURATION;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.CUSTOM;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        // Trigger the smoking animation
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (!pLevel.isClientSide) {
            ModAnimations.SMOKING.setPlaying(true);
            ModAnimations.SMOKING.setCurrentFrame(0);
        }
        pPlayer.startUsingItem(pUsedHand);
        return InteractionResultHolder.success(stack);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (entity instanceof Player player) {
            if (!level.isClientSide) {
                applyEffect(player);

                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
            }

            entity.gameEvent(GameEvent.DRINK);
            player.awardStat(Stats.ITEM_USED.get(this));
        }

        return stack;
    }

}
