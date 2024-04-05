package ru.itsjava.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@RequiredArgsConstructor
public class ConditionalDomain {
    private final String info;
    private final String valueFromProperties;
}
