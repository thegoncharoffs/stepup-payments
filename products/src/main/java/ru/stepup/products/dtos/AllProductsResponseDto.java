package ru.stepup.products.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Builder
public class AllProductsResponseDto {
    private List<ProductDto> list;
    private int total;
}
