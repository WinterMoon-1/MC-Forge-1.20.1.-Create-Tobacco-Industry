package net.kike.tobacco_industry.animation;

import net.kike.tobacco_industry.TobaccoIndustry;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TobaccoIndustry.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AnimationHandler {
    // Update all animations every client tick
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            if (ModAnimations.SMOKING.isPlaying()) {
                ModAnimations.SMOKING.update();
            }
        }
    }
}
