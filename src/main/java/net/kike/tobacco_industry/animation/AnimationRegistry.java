package net.kike.tobacco_industry.animation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.logging.LogUtils;
import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import dev.kosmx.playerAnim.core.data.gson.AnimationSerializing;
import dev.kosmx.playerAnim.core.data.gson.GeckoLibSerializer;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import net.minecraft.server.packs.resources.ResourceManager;
import org.slf4j.Logger;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimationRegistry {
    static final Logger LOGGER = LogUtils.getLogger();
    public static Map<String, KeyframeAnimation> animations = new HashMap<>();

    public static void load(ResourceManager resourceManager) {
        var dataFolder = "player_animations";
        for (var entry : resourceManager.listResources(dataFolder, fileName -> fileName.getPath().endsWith(".json")).entrySet()) {
            var identifier = entry.getKey();
            var resource = entry.getValue();
            try (InputStream inputStream = resource.open()) {
                String jsonString = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

                List<KeyframeAnimation> readAnimations = AnimationSerializing.deserializeAnimation(
                        new java.io.StringReader(jsonString)
                );

                if (readAnimations == null || readAnimations.isEmpty()) {
                    LOGGER.error("Deserialization returned null or empty list for " + identifier);
                } else {
                    KeyframeAnimation animation = readAnimations.get(0);
                    var id = identifier.getPath();
                    id = id.substring(id.lastIndexOf('/') + 1, id.lastIndexOf('.'));
                    AnimationRegistry.animations.put(id, animation);
                }
            } catch (Exception e) {
                LOGGER.error("Failed to load animation " + identifier.toString(), e);
            }
        }
    }
}