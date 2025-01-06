package butbzdorov.client.guiLib.annotation;

import butbzdorov.client.guiLib.DelicateController;
import butbzdorov.client.guiLib.IDelicate;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.Set;

public class DelicateRegister {
    public static void registerAllComponents(String packageName) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(Delicate.class);

        for (Class<?> clazz : annotatedClasses) {
            try {
                if (Delicate.class.isAssignableFrom(clazz) && !Modifier.isAbstract(clazz.getModifiers())) {
                    Delicate delicate = (Delicate) clazz.getDeclaredConstructor().newInstance();
                    DelicateController.registerComponent(delicate);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void registerDelicate(IDelicate delicate) {
        DelicateController.registerComponent(delicate);
    }
}
