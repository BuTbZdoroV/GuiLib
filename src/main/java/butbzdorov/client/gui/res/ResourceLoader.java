package butbzdorov.client.gui.res;

import net.minecraft.util.ResourceLocation;
import java.util.HashSet;
import java.util.Set;

public class ResourceLoader extends ResourceLocation {

    public static final Set<ResourceLocation> resources = new HashSet<>();



    public ResourceLoader(ResourceLocation resourceLocation){
        super(resourceLocation.getResourceDomain(), resourceLocation.getResourcePath());
        addResource(this);
    }

    public ResourceLoader(String modname, String path) {
        super(modname, path);
        addResource(this);
    }

    public ResourceLoader(String path) {
        super(path);
        addResource(this);
    }

    private static void addResource(ResourceLocation resource) {
        resources.add(resource);
    }
}
