package quoters;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        // Представим, что у нас работает программа и нужно включить профилирование.
        // Без перезапуска программы и смены флага вручную в коде это можно сделать через Java VisualVM:
        // Устанавливаем Java VisualVM
        // Запускаем -> Tools -> Plugins -> Available Plugins -> ищем VisualVM-MBean
        // Когда JVVM настроен, ищем текущий процесс, заходим во вкладку MBeans, ищем по нашему названию "profiling" -> "controller" и прописываем атрибуту "Enabled" -- true

//        while (true) {
//            Thread.sleep(1000);
//            // тут у Борисова код не работал, и падал с ошибкой NoSuchBeanDefinition
//            // потому что у него context.getBean(Quoter.class).getClass() выдавал com.sun.proxy.@Proxy7, а ожидаем мы quoters.TerminatorQuoter
//            // у меня было всё ок, никаких прокси7
//            // context.getBean(TerminatorQuoter.class).sayQuote();
//
//            // Но всё равно лучше обращаться к классу по его интерфейсу, который он имплементирует:
//            context.getBean(Quoter.class).sayQuote();
//            // В дебаге можно просмотреть с помощью context.getDefinitionNames() все созданные в контексте бины.
//        }

        // Для включения профилирования можно поставить флаг вручную в ProfilingController.java. Сейчас он жёстко проставлен.

    }
}
