package butbzdorov.client.gui;

import butbzdorov.client.guiLib.window.IWindow;
import butbzdorov.client.guiLib.window.Window;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class MainScreen extends GuiScreen {

    public final MainMenu mainMenu = new MainMenu(this);
    public final TestMenu guiScreen = new TestMenu(this);

    public static float mouseX, mouseY;

    public boolean initialized = false;

    public MainScreen() {
    }

    @Override
    public void initGui() {
        super.initGui();

        guiScreen.isVisible = true;

        ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        width = scaledresolution.getScaledWidth();
        height = scaledresolution.getScaledHeight();

        List<IWindow> windowsSnapshot = new ArrayList<>(Window.Windows);

        for (IWindow window : windowsSnapshot) {
            window.initWindow();
        }
    }

    @Override
    public void drawScreen(int MouseX, int MouseY, float frametime) {
        super.drawScreen(MouseX, MouseY, frametime);
        mouseX = MouseX;
        mouseY = MouseY;

        List<IWindow> windowsSnapshot = new ArrayList<>(Window.Windows);



        for (IWindow window : windowsSnapshot) {
            GL11.glPushMatrix();
            GL11.glTranslated(0, 0, window.getLayer());
            window.renderWindow(MouseX, MouseY, frametime);
            GL11.glPopMatrix();
        }
    }

}
