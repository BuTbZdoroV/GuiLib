package butbzdorov.client;

import butbzdorov.client.gui.MainScreen;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.AchievementEvent;

public class ClientEvents {

    private final Minecraft mc = Minecraft.getMinecraft();

    MainScreen baseMenu = new MainScreen();

    @SubscribeEvent
    public void onGuiOpenEvent(GuiOpenEvent event) {

        if (event.gui instanceof GuiMainMenu){
            event.gui = baseMenu;
        }
    }

    @SubscribeEvent
    public void onEntityJoinWorldEvent(EntityJoinWorldEvent event) {

    }

    @SubscribeEvent
    public void onPlayerTickEvent(TickEvent.PlayerTickEvent e) {

    }

    @SubscribeEvent
    public void onAchievementEvent(AchievementEvent e) {
        e.setCanceled(true);
    }




}