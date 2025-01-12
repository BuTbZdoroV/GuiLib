package butbzdorov.client.guiLib.screen;

import butbzdorov.client.guiLib.IDelicate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenController {

    private static final Map<String, List<IDelicate>> delicatesByScreen = new HashMap<>();

    // Регистрация одного деликейта с проверкой на уникальность
    public static void registerDelicate(IScreen screen, IDelicate delicate) {
        List<IDelicate> delicates = delicatesByScreen
                .computeIfAbsent(screen.getScreenId(), k -> new ArrayList<>());

        // Если деликейт существует с таким же ID, удаляем старый и добавляем новый
        if (delicate != null) {
            delicates.removeIf(d -> d.getId().equals(delicate.getId()));
            delicates.add(delicate);
        }
    }

    // Регистрация нескольких деликейтов с заменой старых
    public static void registerDelicates(IScreen screen, List<? extends IDelicate> delicates) {
        List<IDelicate> existingDelicates = delicatesByScreen
                .computeIfAbsent(screen.getScreenId(), k -> new ArrayList<>());

        // Проходим по каждому деликейту и заменяем старые объекты с таким же ID
        for (IDelicate delicate : delicates) {
            if (delicate != null) {
                existingDelicates.removeIf(d -> d.getId().equals(delicate.getId()));  // Удаление старого объекта
                existingDelicates.add(delicate);  // Добавляем новый объект
            }
        }
    }


    // Получение списка деликейтов для экрана
    public static List<IDelicate> getDelicatesForScreen(String screenId) {
        return delicatesByScreen.getOrDefault(screenId, new ArrayList<>());
    }

    // Очистка деликейтов для определенного экрана
    public static void clearDelicatesForScreen(String screenId) {
        delicatesByScreen.remove(screenId);
    }

    // Очистка всех деликейтов
    public static void clearAll() {
        delicatesByScreen.clear();
    }
}
