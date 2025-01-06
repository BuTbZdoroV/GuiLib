package butbzdorov.common;

import butbzdorov.common.utils.EventsUtils;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;


public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {

    }

    public void init(FMLInitializationEvent event) {
        EventsUtils.registerAllEvents(CommonEvents.class);
    }

    public void postInit(FMLPostInitializationEvent event) {}

}