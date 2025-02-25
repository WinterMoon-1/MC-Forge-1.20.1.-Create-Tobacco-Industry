package net.kike.tobacco_industry.animation;

import net.minecraft.resources.ResourceLocation;

public class ModAnimation {
    private final ResourceLocation location;
    private final int duration;
    private boolean isPlaying = false;
    private int currentFrame = 0;

    public ModAnimation(ResourceLocation location, int duration) {
        this.location = location;
        this.duration = duration;
    }

    public ResourceLocation getLocation() {
        return location;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int frame) {
        this.currentFrame = frame;
    }

    public void update() {
        if (isPlaying && currentFrame < duration) {
            currentFrame++;
        } else {
            isPlaying = false;
        }
    }
}
