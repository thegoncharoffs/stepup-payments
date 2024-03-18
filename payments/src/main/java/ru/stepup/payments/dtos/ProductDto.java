package ru.stepup.payments.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductDto {
    private Long id;

    private Long accountNumber;

    private Long balance;

    private String type;
}
