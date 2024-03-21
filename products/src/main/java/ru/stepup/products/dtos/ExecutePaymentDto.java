package ru.stepup.products.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ExecutePaymentDto {
    private long productId;
    private int price;
}

