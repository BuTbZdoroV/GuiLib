package butbzdorov.server;

import butbzdorov.common.utils.EventsUtils;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;


public class ServerProxy {

    public static final String CONFIG_DIR = "server_configs";


    public ServerProxy() {

    }

    public void preInit(FMLPreInitializationEvent e) {

    }

    public void init(FMLInitializationEvent e) {
        EventsUtils.registerAllEvents(ServerEvents.class);
    }

    public void postInit(FMLPostInitializationEvent e) {

    }

    public void serverStarting(FMLServerStartingEvent event) {

    }

}