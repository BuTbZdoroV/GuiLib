package butbzdorov.client.guiLib.utils;

import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class ResourceCache {
    private static final Map<String, ResourceLocation> resCache = new HashMap<>();

    public static ResourceLocation getResource(String path) {
        return resCache.computeIfAbsent(path, ResourceLocation::new);
    }
}
