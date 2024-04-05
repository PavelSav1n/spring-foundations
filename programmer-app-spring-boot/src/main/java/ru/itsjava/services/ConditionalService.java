package ru.itsjava.services;

import ru.itsjava.domain.ConditionalDomain;


public interface ConditionalService {

    // We are using factory pattern, where some services would create domains for us via similar methods.
    ConditionalDomain getConditionalDomain();
}
