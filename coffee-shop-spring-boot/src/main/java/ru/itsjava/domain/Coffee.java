package ru.itsjava.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Coffee {
    private final String type;
    private final double price;
}
