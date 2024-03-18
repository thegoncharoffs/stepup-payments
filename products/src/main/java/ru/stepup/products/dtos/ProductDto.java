package ru.stepup.products.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.stepup.products.enums.ProductType;

@Getter
@Builder
@AllArgsConstructor
public class ProductDto {
    private Long id;

    private Long accountNumber;

    private Long balance;

    private ProductType type;
}
