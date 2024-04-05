package ru.itsjava.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itsjava.services.ConditionalService;
import ru.itsjava.services.ConditionalServiceImpl;

@Configuration
public class ConditionalConfiguration {

    @Value("${conditional-domain.info}")
    private String conditionalServiceInfo;

    @Value("${conditional-domain.valueFromProperties}")
    private String conditionalServiceValueFromProperties;

    // This is an example of using @ConditionOnProperty.
    // We can manage bean creating via this annotation and specified property in yaml or properties file.
    // name and havingValue (or just Value if its String type)
    @Bean
    @ConditionalOnProperty(name = "conditional-domain.valueFromProperties", havingValue = "da")
    public ConditionalService conditionalServiceImpl() {
        return new ConditionalServiceImpl(conditionalServiceInfo, conditionalServiceValueFromProperties);
    }
}
