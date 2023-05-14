package quoters;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) // обязательно проставляем RUNTIME. У Борисова Интели по дефолту проставляет RUNTIME
public @interface Profiling {
}
