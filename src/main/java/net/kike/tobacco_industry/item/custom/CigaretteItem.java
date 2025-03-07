package net.kike.tobacco_industry.item.custom;

import com.mojang.logging.LogUtils;
import dev.kosmx.playerAnim.api.firstPerson.FirstPersonMode;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import net.kike.tobacco_industry.animation.AnimationHandler;
import net.kike.tobacco_industry.animation.AnimationRegistry;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

import java.util.function.Supplier;

public class CigaretteItem extends Item {
    private final int baseColor;
    private final int stripColor;
    private final Supplier<MobEffectInstance> effectSupplier;
    private static final int USE_DURATION = 140;
    private static final int SMOKING_LAYER = 11;
    private static final int LIGHTING_LAYER = 10;

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

        if (pPlayer.isUsingItem()) {
            return InteractionResultHolder.pass(stack);
        }

        pPlayer.startUsingItem(pUsedHand);

        if (pLevel.isClientSide && pPlayer instanceof AbstractClientPlayer clientPlayer) {
            KeyframeAnimation smokingAnimation = AnimationRegistry.animations.get("smoking");
            KeyframeAnimation lightingAnimation = AnimationRegistry.animations.get("lighting_cigarette");

            if (smokingAnimation != null) {
                AnimationHandler.playAnimation(clientPlayer, smokingAnimation, SMOKING_LAYER);
                AnimationHandler.playAnimation(clientPlayer, lightingAnimation, LIGHTING_LAYER);
            }
        }


        // Smoking sound
        pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                SoundEvents.CAMPFIRE_CRACKLE, pPlayer.getSoundSource(), 1.0F, 1.0F);

        return InteractionResultHolder.sidedSuccess(stack, pLevel.isClientSide);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        if (entity instanceof Player player) {
            int useTime = this.getUseDuration(stack) - timeLeft;
            LogUtils.getLogger().debug("Right click released");
            LogUtils.getLogger().debug("Use time: " + useTime + ". Where use duration: " + this.getUseDuration(stack) + " and time left: " + timeLeft);

            if (useTime >= 40) {
                applyEffect(player);
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
                player.awardStat(Stats.ITEM_USED.get(this));

                if (level.isClientSide) {
                    spawnSmokeParticles(player);
                }
            }

            if (level.isClientSide) {
                stopAnimation(player, SMOKING_LAYER);
                stopAnimation(player, LIGHTING_LAYER);
            }

            player.stopUsingItem();
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {

        if (entity instanceof Player player) {
            applyEffect(player); // Apply the effect

            if (!player.getAbilities().instabuild) {
                stack.shrink(1); // Consume the cigarette
            }

            player.stopUsingItem();
            spawnSmokeParticles(player);
            entity.stopUsingItem();
            player.awardStat(Stats.ITEM_USED.get(this));
        }

        return stack.isEmpty() ? ItemStack.EMPTY : stack;
    }

    private void stopAnimation(Player player, int animLayer) {
        if (player instanceof AbstractClientPlayer clientPlayer) {
            AnimationHandler.stopAnimation(clientPlayer, animLayer);
        }
    }

    public static void spawnSmokeParticles(Player player) {
        if (player.level().isClientSide) {
            Vec3 lookDirection = player.getLookAngle();
            Vec3 playerPos = player.position().add(0, player.getEyeHeight() - 0.2, 0);

            Vec3 particlePos = playerPos.add(lookDirection.scale(0.5));

            // Smoke particle "ejection" logic
            double speed = 0.05;
            double vxBase = lookDirection.x * speed;
            double vyBase = lookDirection.y * speed;
            double vzBase = lookDirection.z * speed;

            for (int i = 0; i < 10; i++) {
                double vx = vxBase + (Math.random() - 0.5) * 0.01;
                double vy = vyBase + (Math.random() - 0.5) * 0.05;
                double vz = vzBase + (Math.random() - 0.5) * 0.01;

                player.level().addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE,
                        particlePos.x + (Math.random() - 0.5) * 0.2,
                        particlePos.y + (Math.random() - 0.2) * 0.2,
                        particlePos.z + (Math.random() - 0.5) * 0.2,
                        vx, vy + 0.1, vz);
            }
        }
    }


}
