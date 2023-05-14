package quoters;

// В этом интерфейсе мы прописываем методы, которые мы хотим, чтобы были доступны через JMX Consul
public interface ProfilingControllerMBean {
    public void setEnabled(boolean enabled);
}
