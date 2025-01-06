package butbzdorov.client.guiLib.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Delicate {
    String id() default ""; // Дополнительно можно добавить идентификатор компонента.
}
