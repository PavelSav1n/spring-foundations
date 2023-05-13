package quoters;

import javax.annotation.PostConstruct;

/*  Задача: добавить профилирование методов. Время старта, запуск метода, конец, вывести разницу на экран.
*   1. Добавляем пишем с нуля аннотацию @Profiling
*   2. Пишем BeanPostProcessor, который:
*       - отловит эту аннотацию
*       - создаст новый прокси объект
*       - пропишет логику профилирования для нашего изначального объекта
*       - вернёт новый объект, но с контрактом изначального, чтобы была незаметна "подмена" объекта
*
*   Есть два разных подхода для генерации "на лету" нового прокси класса:
*   1. (CGLib) - Наследование от оригинального класса, переопределение его методов и добавление новой логики
*   2. (Dynamic-proxy) - Имплементирование тех же самых интерфейсов, что и у оригинального класса + добавление новой логики
*
*   Spring AOP работает так: если у объекта есть интерфейсы -- создаёт через Dynamic-proxy, если нет -- CGLib
*
*   Как узнать, что работая в BPP с объектом мы работаем с оригинальным объектом со всеми его аннотациями, а не с прокси, где уже нет метадаты?
*   По конвенции спринга те BPP, которые что-то в классе меняют, должны это делать на этапе Object postProcessAfterInitialization(Object bean, String beanName)
*   Поэтому @PostConstruct всегда работает на оригинальном методе, до того как все BPP отработали на нём.
*
* */
@Profiling // бенчмарк методов
public class TerminatorQuoter implements Quoter {

    @InjectRandomInt(min = 2, max = 8)
    private int repeat;

    private String message;

    public TerminatorQuoter() {
        // Тут будет 0, потому что спринг сначала создаёт объект, а затем отдаёт его в BeanPostProcessor
        System.out.println("Phase 1 -- constructor");
    }
    // поэтому пишем init метод, чтобы работать с объектом в BeanPostProcessor:
    @PostConstruct// не работает до тех пор:
                    // 1. пока не подгрузим в pom.xml javax.annotation-api и пакет в этот класс (import javax.annotation.PostConstruct)
                    // 2. пока не пропишем org.springframework.context.annotation.CommonAnnotationBeanPostProcessor в context.xml
                    // - или <context:annotation-config/>
    public void init() {
        System.out.println("Phase 2 -- init method");
        System.out.println(repeat);
    }

    // для xml обязательно делать сеттеры
    // с точки зрения спринга без сеттера поле не проперти (будет попытка через рефлекшн вызвать сеттер и она провалится)
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void sayQuote() {
        for (int i = 0; i < repeat; i++) {
            System.out.println(i + 1 + ". message = " + message);
        }
    }
}
