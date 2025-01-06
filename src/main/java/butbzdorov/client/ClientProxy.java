package butbzdorov.client;

import butbzdorov.client.guiLib.DelicateController;
import butbzdorov.client.guiLib.annotation.DelicateRegister;
import butbzdorov.client.guiLib.functional.FunctionalDelicateController;
import butbzdorov.common.CommonEvents;
import butbzdorov.common.CommonProxy;
import butbzdorov.common.utils.EventsUtils;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;


public class ClientProxy extends CommonProxy {



    public ClientProxy() {

    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        EventsUtils.registerAllEvents(ClientEvents.class);
        new FunctionalDelicateController();
        EventsUtils.registerAllEvents(FunctionalDelicateController.class);
        new DelicateController();
        EventsUtils.registerAllEvents(DelicateController.class);

        DelicateController.registerComponent(ClientEvents.mainMenu);

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

}