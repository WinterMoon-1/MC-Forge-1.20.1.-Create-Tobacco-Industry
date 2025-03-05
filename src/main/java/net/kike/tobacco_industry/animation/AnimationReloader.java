package net.kike.tobacco_industry.animation;

import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class AnimationReloader implements PreparableReloadListener {
    @Override
    public CompletableFuture<Void> reload(PreparableReloadListener.PreparationBarrier barrier, ResourceManager resourceManager, ProfilerFiller profiler, ProfilerFiller profilerFiller, Executor executor, Executor executor2) {
        return CompletableFuture.runAsync(() -> {
            AnimationRegistry.load(resourceManager);
        }, executor).thenCompose(barrier::wait);
    }
}
