package ru.stepup.limits.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LimitDto {
    private long userId;
    private long paymentLimit;
}

