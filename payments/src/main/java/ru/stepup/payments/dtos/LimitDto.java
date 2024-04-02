package ru.stepup.payments.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LimitDto {
    private long userId;
    private long paymentLimit;
}

