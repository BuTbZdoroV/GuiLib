package butbzdorov.client.gui;

import butbzdorov.client.guiLib.window.IWindow;
import butbzdorov.client.guiLib.window.Window;
import butbzdorov.client.guiLib.window.WindowsController;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class MainScreen extends GuiScreen {

    public final MainMenu mainMenu = new MainMenu(this);
    public final GuiTest guiScreen = new GuiTest(this);

    @Override
    public void initGui() {
        super.initGui();

        ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        width = scaledresolution.getScaledWidth();
        height = scaledresolution.getScaledHeight();

        for (IWindow window : Window.Windows) {
            window.initWindow();
        }
    }

    @Override
    public void drawScreen(int MouseX, int MouseY, float p_73863_3_) {
        super.drawScreen(MouseX, MouseY, p_73863_3_);
       for (IWindow window : Window.Windows) {
            window.renderWindow(MouseX, MouseY);
        }
    }
}
