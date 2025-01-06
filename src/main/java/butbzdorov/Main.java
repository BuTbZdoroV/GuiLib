package butbzdorov;

import butbzdorov.client.ClientProxy;
import butbzdorov.common.CommonProxy;
import butbzdorov.server.ServerProxy;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.relauncher.Side;
import ru.justagod.cutter.GradleSide;
import ru.justagod.cutter.GradleSideOnly;
import ru.justagod.cutter.invoke.Invoke;


@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION)
public class Main {

    public static final String VERSION = "0.00.01";
    public static final String NAME = "Core Mod 1.7.10";
    public static final String MODID = "butbzdorov";

    @Mod.Instance(Main.MODID)
    public static Main getInstance;

    @GradleSideOnly(GradleSide.CLIENT)
    public ClientProxy clientProxy;

    public CommonProxy commonProxy;

    @GradleSideOnly(GradleSide.SERVER)
    public ServerProxy serverProxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Invoke.client(() -> {
            this.clientProxy = new ClientProxy();

            if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
                this.clientProxy.preInit(event);
            }
        });

        this.commonProxy = new CommonProxy();
        this.commonProxy.preInit(event);

        Invoke.server(() -> {
            this.serverProxy = new ServerProxy();

            if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
                this.serverProxy.preInit(event);
            }
        });
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Invoke.client(() -> {
            if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
                this.clientProxy.init(event);
            }
        });

        this.commonProxy.init(event);

        Invoke.server(() -> {
            if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
                this.serverProxy.init(event);
            }
        });
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        Invoke.client(() -> {
            if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
                this.clientProxy.postInit(event);
            }
        });

        this.commonProxy.postInit(event);

        Invoke.server(() -> {
            if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
                this.serverProxy.postInit(event);
            }
        });
    }

    @Mod.EventHandler
    @GradleSideOnly(GradleSide.SERVER)
    public void serverStarting(FMLServerStartingEvent event) {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
            this.serverProxy.serverStarting(event);
        }
    }

}