package quoters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.Method;

// Тут стоит дженерик <?>, чтобы не обрабатывать все ApplicationEvent
public class PostProxyInvokerContextListener implements ApplicationListener<ContextRefreshedEvent> {

    // Подключаем BeanFactory, чтобы можно было достать из прокси бинов оригинальный класс.
    @Autowired
    private ConfigurableListableBeanFactory factory;

    @Override // теперь у нас есть этот конкретный ивент: ContextRefreshedEvent
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext(); // получаем контекст
        String[] names = context.getBeanDefinitionNames();// из контекста получаем список всех бинов
        // и будем проверять, у кого из них стояла аннотация @PostProxy
        for (String name : names) {
            // Можно ли брать имя бина и доставать из него класс (name.getClass)?
            // Нет, потому что тут у нас уже однозначно стоит $Proxy7.
            // Поэтому нам нужно общаться с главной фабрикой спринга (BeanFactory), только который умеет делать getBeanDefinition().
            // Нельзя на этом моменте вытаскивать бины (getBean), потому что какие-то из них могут быть проаннотированы @Lazy (в том числе и singleton) и не должны быть созданы сейчас.
            // Поэтому мы вытаскиваем BeanDefinition и там будем искать нашу аннотацию @PostProxy
            BeanDefinition beanDefinition = factory.getBeanDefinition(name); // получаем beanDefinition
            String originalClassName = beanDefinition.getBeanClassName();// вытаскиваем оригинальное название класса (которое ещё в XML прописано)
            try {
                Class<?> originalClass = Class.forName(originalClassName); // вытаскиваем оригинальный класс
                Method[] methods = originalClass.getMethods(); // вытаскиваем у класса все методы
                for (Method method : methods) { // пробегаемся по всем методам
                    if (method.isAnnotationPresent(PostProxy.class)) { // если есть аннотация @PostProxy
                        // тут нельзя делать method.invoke(), потому что бин может быть создан с помощью Dynamic Proxy (если CGLib, то всё ок)
                        // потому что method -- из оригинального класса, а текущий бин создан из Proxy класса -- это два разных класса.
                        // поэтому нужно вытащить сам бин и у него запустить этот метод:
                        Object bean = context.getBean(name); // берём бин
                        Class<?> proxyClass = bean.getClass(); // вытаскиваем у него его Proxy класс
                        Method currentMethod = proxyClass.getMethod(method.getName(), method.getParameterTypes());
                        currentMethod.invoke(bean); // вот теперь можно запускать.
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
