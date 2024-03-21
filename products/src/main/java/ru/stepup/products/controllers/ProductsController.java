package ru.stepup.products.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.stepup.products.dtos.AllProductsResponseDto;
import ru.stepup.products.dtos.ExecutePaymentDto;
import ru.stepup.products.dtos.ProductDto;
import ru.stepup.products.exceptions.ResourceNotFoundException;
import ru.stepup.products.mappers.ProductMapper;
import ru.stepup.products.services.ProductsService;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {
    private final ProductsService productsService;

    @GetMapping
    public AllProductsResponseDto getAll() {
        List<ProductDto> list = productsService.findAll().stream().map(ProductMapper::entityToDto).toList();
        log.info("Products all " + list.toString());
        return new AllProductsResponseDto(list, list.size());
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        log.info("Product find by id " + id);
        return productsService.findById(id).map(ProductMapper::entityToDto).orElseThrow(() -> new ResourceNotFoundException("PRODUCTS_NOT_FOUND", "Product not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ProductDto productDto) {
        productsService.create(ProductMapper.dtoToEntity(productDto));
        log.info("Product " + productDto + " created");
    }

    @PutMapping
    public void update(@RequestBody ProductDto productDto) {
        productsService.update(ProductMapper.dtoToEntity(productDto));
        log.info("Product " + productDto + " updated");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productsService.deleteById(id);
        log.info("Product with id" + id + " deleted");
    }

    @PostMapping("/execute-payment")
    public void executePayment(@RequestBody ExecutePaymentDto executePaymentDto) {
        productsService.executePayment(executePaymentDto);
        log.info("Product " + executePaymentDto + " created");
    }
}
