package ru.stepup.payments.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ExecutePaymentDtoRq {
    private long productId;
    private int price;
}
