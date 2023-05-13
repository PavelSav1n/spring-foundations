package quoters;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Random;

public class InjectRandomIntAnnotationBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields(); // берём все поля у данного класса
        for (Field field : fields) {
            InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class); // у каждого поля попытаемся обнаружить аннотацию @InjectRandomInt
            if (annotation != null) { // проверяем, была ли аннотация над полем
                int min = annotation.min(); // получаем из этой аннотации поля
                int max = annotation.max();
                Random random = new Random();
                int randomInt = min+random.nextInt(max-min); // генерим случайное число
                field.setAccessible(true); // правим field
//                field.set(randomInt); // чтобы не прокидывать исключения и try/catch используем следующую особенность спринга:
                ReflectionUtils.setField(field, bean, randomInt); // 1. для какого поля будем вставлять значение, 2. для какого объекта поле нужно будет засунуть 3. значение
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
