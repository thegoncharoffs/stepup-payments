package ru.stepup.products.mappers;

import ru.stepup.products.dtos.ProductDto;
import ru.stepup.products.entities.ProductEntity;
import ru.stepup.products.enums.ProductType;

public class ProductMapper {
    public static ProductEntity dtoToEntity(ProductDto productDto) {
        return ProductEntity.builder()
                .id(productDto.getId())
                .accountNumber(productDto.getAccountNumber())
                .balance(productDto.getBalance())
                .type(productDto.getType().toString().toLowerCase())
                .build();
    }

    public static ProductDto entityToDto(ProductEntity productEntity) {
        return ProductDto.builder()
                .id(productEntity.getId())
                .accountNumber(productEntity.getAccountNumber())
                .balance(productEntity.getBalance())
                .type(ProductType.valueOf(productEntity.getType().toUpperCase()))
                .build();
    }
}
