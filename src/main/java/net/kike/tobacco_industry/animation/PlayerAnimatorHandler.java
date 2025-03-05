package net.kike.tobacco_industry.animation;

import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;

public class PlayerAnimatorHandler {
    private final ModifierLayer<IAnimation> animationLayer = new ModifierLayer<>();

    public ModifierLayer<IAnimation> getAnimationLayer() {
        return animationLayer;
    }
}
