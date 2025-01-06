package butbzdorov.client;

import butbzdorov.client.gui.MainMenu;
import butbzdorov.client.gui.res.ResourceLoader;
import butbzdorov.client.guiLib.delicates.Button;
import butbzdorov.client.guiLib.delicates.Image;
import butbzdorov.client.guiLib.delicates.Text;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.AchievementEvent;
import org.newdawn.slick.Color;

import javax.swing.*;

public class ClientEvents {

    private final Minecraft mc = Minecraft.getMinecraft();

    public static final MainMenu mainMenu = new MainMenu();

    @SubscribeEvent
    public void onGuiOpenEvent(GuiOpenEvent event) {

        if (event.gui instanceof GuiMainMenu){
            event.gui = mainMenu;
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