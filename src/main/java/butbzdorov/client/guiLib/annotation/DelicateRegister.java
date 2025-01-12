package butbzdorov.client.guiLib.annotation;

import butbzdorov.client.guiLib.DelicateController;
import butbzdorov.client.guiLib.IDelicate;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

public class DelicateRegister {

    public static void registerAllComponents() {
        try {
            Reflections reflections = new Reflections(new ConfigurationBuilder()
                    .forPackages("butbzdorov.client")
                    .addScanners(Scanners.TypesAnnotated, Scanners.FieldsAnnotated));

            // Находим классы с аннотированными полями
            Set<Field> delicateFields = reflections.getFieldsAnnotatedWith(Delicate.class);

            for (Field field : delicateFields) {
                field.setAccessible(true); // Делаем поле доступным

                // Получаем класс, в котором находится поле
                Class<?> declaringClass = field.getDeclaringClass();
                Object instance = declaringClass.getDeclaredConstructor().newInstance();

                // Извлекаем значение поля
                Object value = field.get(instance);

                if (value instanceof IDelicate) {
                    // Если это объект IDelicate
                    registerDelicate((IDelicate) value);
                } else if (value instanceof List<?>) {
                    // Если это список, проверяем элементы
                    for (Object item : (List<?>) value) {
                        if (item instanceof IDelicate) {
                            registerDelicate((IDelicate) item);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void registerDelicate(IDelicate delicate) {
        System.out.println("Registered: " + delicate.getClass().getSimpleName());
        DelicateController.delicates.add(delicate);
    }
}
