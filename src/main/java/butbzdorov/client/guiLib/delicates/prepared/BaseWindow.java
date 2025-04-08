package butbzdorov.client.guiLib.delicates.prepared;

import butbzdorov.client.gui.MainScreen;
import butbzdorov.client.guiLib.window.Resolution;
import butbzdorov.client.guiLib.window.Window;
import net.minecraft.client.Minecraft;

public class BaseWindow extends Window {

    private static final Minecraft mc = Minecraft.getMinecraft();

    public BaseWindow(MainScreen mainScreen) {
        super(mainScreen, new Resolution(0, 0, 1920, 1080));
    }


    @Override
    public String getWindowId() {
        return "base";
    }

    @Override
    public void renderWindow(float mouseX, float mouseY, float frametime) {
        super.renderWindow(mouseX, mouseY, frametime);
    }


}
