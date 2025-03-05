package net.kike.tobacco_industry.animation;

import com.mojang.logging.LogUtils;
import dev.kosmx.playerAnim.api.layered.AnimationStack;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import net.kike.tobacco_industry.TobaccoIndustry;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraftforge.fml.common.Mod;
import org.jline.utils.Log;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = TobaccoIndustry.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AnimationHandler {
    private static final Map<AbstractClientPlayer, AnimationStack> animations = new HashMap<>();

    public static void playAnimation(AbstractClientPlayer player, KeyframeAnimation animation) {
        try {
            AnimationStack stack = PlayerAnimationAccess.getPlayerAnimLayer(player);
            stack.removeLayer(0);
            stack.addAnimLayer(10, new KeyframeAnimationPlayer(animation));
            LogUtils.getLogger().debug("Animation layer: " + animation + " set for " + player);
        } catch (IllegalArgumentException e) {
            LogUtils.getLogger().error("Failed to get animation layer for player: " + player.getName().getString(), e);
        }
    }

    private static final Map<AbstractClientPlayer, ModifierLayer<?>> animationLayers = new HashMap<>();

    public static ModifierLayer<?> getAnimationLayer(AbstractClientPlayer player) {
        return animationLayers.computeIfAbsent(player, p -> new ModifierLayer<>());
    }

    public static void removePlayer(AbstractClientPlayer player) {
        animationLayers.remove(player);
    }

}
