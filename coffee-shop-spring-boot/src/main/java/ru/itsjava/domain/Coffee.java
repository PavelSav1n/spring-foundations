package ru.itsjava.domain;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Coffee {
    private final String type;
    private final double price;
}
