package ru.stepup.products.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class AllProductsResponseDto {
    private List<ProductDto> list;
    private int total;
}
