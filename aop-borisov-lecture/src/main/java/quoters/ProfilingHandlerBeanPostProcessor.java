package quoters;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

// не забываем прописать его в context.xml
public class ProfilingHandlerBeanPostProcessor implements BeanPostProcessor {
    // имя бина (beanName) всегда сохраняется оригинальным, вне зависимости, мы работаем с оригиналом или с прокси
    // но это не всегда верно по отношению к классам, поэтому один из способов сохранения оригинального класса и имени --
    // откладываем в сторонку имя бина + имя оригинального класса + , для которых что-то нужно будет сделать с помощью BPP
    // а на этапе postProcessAfterInitialization это "что-то" делать.

    // map с бинами, аннотированными @Profiling
    private Map<String, Class> map = new HashMap<>();

    // добавляем наш переключатель для профилирования
    private ProfilingController controller = new ProfilingController();

    public ProfilingHandlerBeanPostProcessor() throws Exception {
        MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer(); // получаем инстанс MBean Server'а в который можно регистрировать бины.
        // регистрируем: передаём наш контроллер и имя, через которое его в JMX Consul мы его будем искать:
        platformMBeanServer.registerMBean(controller, new ObjectName("profiling", "name","controller"));
        // Имя такого формата: new ObjectName():
        // profiling -- папочка, домен, под которым в JMX Consul этот контроллер будет находиться
        // name -- даём имя
        // controller -- само имя
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass(); // вытаскиваем класс бина
        if (beanClass.isAnnotationPresent(Profiling.class)) { // если у этого бина есть аннотация @Profiling
            map.put(beanName, beanClass); // кладём его в map наших бинов с аннотацией @Profiling
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = map.get(beanName); // пробуем вытащить этот бин из карты с @Profiling бинами
        if (beanClass != null) { // если такой класс есть, значит над ним стоял @Profiling
            // значит мы вернём новый Dynamic-proxy объект:
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
                // newProxyInstance -- статический метод, который создаёт объект из нового класса, который сгенерит он же сам на лету.
                // он принимает следующие параметры:
                //  - ClassLoader loader -- при помощи которого класс, который сгенерится на лету и загрузится в heap (любой класс знает какой ClassLoader его загрузил, поэтому beanClass.getClassLoader())
                //  - Class<?> interfaces -- список интерфейсов, которые должен имплементировать тот класс, который сгенериться на лету
                //  - InvocationHandler h -- некий объект, который будет инкапсулировать логику, которая попадёт во все методы класса, который сгенерится на лету.
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    // для усложнения задачи, чтобы можно было в запущенной программе через JMX Consul переключать флаг профилирования (вкл/выкл), создаём класс ProfilingController
                    // добавляем сюда в качестве поля
                    if (controller.isEnabled()) { // Если наш контроллер включен, то профилируем:
                        System.out.println("Профилирую...");
                        long before = System.nanoTime();
                        Object retVal = method.invoke(bean, args); // запускаем наш метод из бина
                        long after = System.nanoTime();
                        System.out.println(after - before);
                        System.out.println("Всё");
                        return retVal;
                    } else {
                        return method.invoke(bean, args); // если контроллер выключен, то просто делегируем на оригинальные методы оригинальные объекты и аргументы, ничего не делаем
                    }
                }
            });

        }

        return bean;
    }
}
