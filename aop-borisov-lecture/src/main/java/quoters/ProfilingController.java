package quoters;

// MBean Server.
// Когда поднимается Java Process, вместе с ним поднимается MBean Server и все объекты, которые зарегистрированы в нём, можно изменять через JMX Consul. Запускать их методы и т.п.
// Для этого согласно конвенции нужно:
//  1. Имплементировать интерфейс, который будет называться точно так же как и класс + MBean (создаём сами этот интерфейс)
//  2. В этом интерфейсе мы прописываем методы, которые мы хотим, чтобы были доступны через JMX Consul
//
// ПРИМЕЧАНИЕ: То, что у нас сейчас этот класс написан и мы его вызываем в ProfilingHandlerBeanPostProcessor, не значит, что мы зарегистрировали его в MBean Server.
// Для этого нужно это явно прописать. Мы будем прописывать в конструкторе ProfilingHandlerBeanPostProcessor
public class ProfilingController implements ProfilingControllerMBean{
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
