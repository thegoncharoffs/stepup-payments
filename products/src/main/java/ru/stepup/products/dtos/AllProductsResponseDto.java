package ru.stepup.products.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class AllProductsResponseDto {
    List<ProductDto> list;

    int total;
}
