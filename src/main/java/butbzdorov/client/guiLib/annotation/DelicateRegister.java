package butbzdorov.client.guiLib.annotation;

import butbzdorov.client.guiLib.DelicateController;
import butbzdorov.client.guiLib.IDelicate;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class DelicateRegister {

    public static void registerAllComponents(String packageName) {
        try {
            List<Class<?>> classes = getClasses(packageName);

            for (Class<?> clazz : classes) {
                if (clazz.isAnnotationPresent(Delicate.class) && IDelicate.class.isAssignableFrom(clazz)) {
                    if (!Modifier.isAbstract(clazz.getModifiers())) {
                        IDelicate delicate = (IDelicate) clazz.getDeclaredConstructor().newInstance();
                        DelicateController.registerComponent(delicate);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Class<?>> getClasses(String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();

        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }

        List<Class<?>> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }

        File[] files = directory.listFiles();
        if (files == null) return classes;

        for (File file : files) {
            if (file.isDirectory()) {
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                classes.add(Class.forName(className));
            }
        }
        return classes;
    }

    public static void registerDelicate(IDelicate delicate) {
        DelicateController.registerComponent(delicate);
    }
}
