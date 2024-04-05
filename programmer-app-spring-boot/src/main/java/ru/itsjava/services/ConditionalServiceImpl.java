package ru.itsjava.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itsjava.domain.ConditionalDomain;

// Commented because we are testing configuration conditional props
// If @Service and @Configuration annotations would be both up, there will be an error, that bean has already been defined.
// Could be overridden by setting spring.main.allow-bean-definition-overriding=true
//@Service
public class ConditionalServiceImpl implements ConditionalService {
    private final String info;
    private final String valueFromProperties;


    // Without @Service annotation @Value annotations would be ignored:
    public ConditionalServiceImpl(@Value("${conditional-domain.info}") String info,
                                  @Value("${conditional-domain.valueFromProperties}") String valueFromProperties) {

        this.info = info;
        this.valueFromProperties = valueFromProperties;

    }

    @Override
    public ConditionalDomain getConditionalDomain() {
        return new ConditionalDomain(info, valueFromProperties);
    }
}
