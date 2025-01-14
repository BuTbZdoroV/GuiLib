package butbzdorov.client;

import butbzdorov.client.gui.MainScreen;
import butbzdorov.client.guiLib.utils.newCustomNPC.CustomFontRenderer;
import butbzdorov.common.CommonProxy;
import butbzdorov.common.utils.EventsUtils;
import butbzdorov.server.ServerEvents;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import static butbzdorov.client.guiLib.utils.newCustomNPC.CustomFontRenderer.registerColors;


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
        EventsUtils.registerAllEvents(CustomFontRenderer.class);
        registerColors();

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

}