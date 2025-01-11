package butbzdorov.client.guiLib.screen;

import net.minecraft.client.Minecraft;

import java.util.HashMap;
import java.util.Map;

public class ScreenManager {

    private static final Map<String, IScreen> screens = new HashMap<>();
    public static IScreen currentScreen;

    // Регистрация экрана
    public static void registerScreen(IScreen screen) {
        screens.put(screen.getScreenId(), screen);
    }

    // Переключение между экранами
    public static void switchToScreen(String screenId) {
        IScreen newScreen = screens.get(screenId);
        if (newScreen != null) {
            if (currentScreen != null) {
                ScreenController.clearDelicatesForScreen(currentScreen.getScreenId());
            }
            currentScreen = newScreen;
            Minecraft.getMinecraft().currentScreen = (net.minecraft.client.gui.GuiScreen) currentScreen;
            ScreenController.registerDelicates(currentScreen, currentScreen.getDelicates());
        } else {
            System.out.println("Экран с ID " + screenId + " не найден!");
        }
    }

    // Отрисовка текущего экрана
    public static void renderCurrentScreen() {
        if (currentScreen != null) {
            currentScreen.renderScreen();
        }
    }
}
