package net.kike.tobacco_industry.item.custom;

import com.mojang.logging.LogUtils;
import dev.kosmx.playerAnim.api.layered.AnimationStack;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import net.kike.tobacco_industry.TobaccoIndustry;
import net.kike.tobacco_industry.animation.AnimationHandler;
import net.kike.tobacco_industry.animation.AnimationRegistry;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
    private static final int USE_DURATION = 140;

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
        return UseAnim.NONE;
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        LogUtils.getLogger().debug("Using cigarette");

        if (pLevel.isClientSide && pPlayer instanceof AbstractClientPlayer clientPlayer) {
            KeyframeAnimation animation = AnimationRegistry.animations.get("smoking");
            LogUtils.getLogger().debug("In client. Smoking animation retrieved from Animation Registry");

            if (animation != null) {
                LogUtils.getLogger().debug("Animation is not null, playing animation");
                    ModifierLayer<IAnimation> animationLayer = (ModifierLayer<IAnimation>) AnimationHandler.getAnimationLayer(clientPlayer);

                animationLayer.setAnimation(new KeyframeAnimationPlayer(animation));

                AnimationHandler.playAnimation(clientPlayer, animation);
            } else {
                LogUtils.getLogger().debug("Animation " + animation + " is null bro");
            }
        }


        // Smoking sound
        pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                SoundEvents.CAMPFIRE_CRACKLE, pPlayer.getSoundSource(), 1.0F, 1.0F);

        return InteractionResultHolder.consume(stack);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (entity instanceof Player player) {
            applyEffect(player); // Apply the effect

            if (!player.getAbilities().instabuild) {
                stack.shrink(1); // Consume the cigarette
            }

            player.awardStat(Stats.ITEM_USED.get(this));
        }

        LogUtils.getLogger().debug("Finished using cigarette");
        return stack.isEmpty() ? ItemStack.EMPTY : stack;
    }

}
