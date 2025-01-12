package butbzdorov.client.guiLib.window;

import butbzdorov.client.guiLib.IDelicate;

import java.util.List;

public interface IWindow {
    String getWindowId(); // Уникальный ID экрана
    List<? extends IDelicate> getDelicates(); // Список деликейтов для экрана
    void initWindow();
    void renderWindow(float mouseX, float mouseY);
    default Resolution getResolution() {
        return null;
    }
}
