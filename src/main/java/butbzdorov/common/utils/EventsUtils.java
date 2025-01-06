package butbzdorov.common.utils;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.MinecraftForge;

public class EventsUtils {
	
    public static boolean isServerSide() {
        return FMLCommonHandler.instance().getSide() == Side.SERVER;
    }
    
    public static <T> void unregisterFMLEvents(Object object) {
        FMLCommonHandler.instance().bus().unregister(object);
    }

    public static <T> void unregisterEvents(Object object) {
        MinecraftForge.EVENT_BUS.unregister(object);
    }
    
    public static <T> T registerEvents(Class<T> clazz) {
        try {
            T object = clazz.newInstance();
            MinecraftForge.EVENT_BUS.register(object);
            return object;
        } catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
        return null;
    }

    public static <T> T registerFMLEvents(Class<T> clazz) {
        try {
            T object = clazz.newInstance();
            FMLCommonHandler.instance().bus().register(object);
            return object;
        } catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
        return null;
    }

    public static void registerFMLEvents(Object object) {
        FMLCommonHandler.instance().bus().register(object);
    }

    public static void registerEvents(Object object) {
        MinecraftForge.EVENT_BUS.register(object);
    }

    public static <T> T registerAllEvents(Class<T> clazz) {
        try {
            T object = clazz.newInstance();
            FMLCommonHandler.instance().bus().register(object);
            MinecraftForge.EVENT_BUS.register(object);
            return object;
        } catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
        return null;
    }
}
