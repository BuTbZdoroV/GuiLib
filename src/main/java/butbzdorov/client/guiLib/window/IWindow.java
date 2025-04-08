package butbzdorov.client.guiLib.window;

import butbzdorov.client.guiLib.IDelicate;

import java.util.List;

public interface IWindow {
    String getWindowId();
    void initWindow();
    Resolution getResolution();
    void renderWindow(float mouseX, float mouseY, float frametime);
    int getLayer();
}

