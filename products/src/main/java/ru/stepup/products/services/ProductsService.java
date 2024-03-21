package ru.stepup.products.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stepup.products.dtos.ExecutePaymentDto;
import ru.stepup.products.entities.ProductEntity;
import ru.stepup.products.exceptions.BadRequestException;
import ru.stepup.products.exceptions.ResourceNotFoundException;
import ru.stepup.products.repositories.ProductsRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductsService {

    private final ProductsRepository productsRepository;

    public List<ProductEntity> findAll() {
        return productsRepository.findAll();
    }

    public Optional<ProductEntity> findById(Long id) {
        return productsRepository.findById(id);
    }

    public void update(ProductEntity productEntity) {
        productsRepository.save(productEntity);
    }

    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

    public void create(ProductEntity productEntity) {
        productsRepository.save(productEntity);
    }

    public void executePayment(ExecutePaymentDto executePaymentDto) {
        Optional<ProductEntity> product = this.findById(executePaymentDto.getProductId());
        if (product.isEmpty()) {
            throw new ResourceNotFoundException("PRODUCT_NOT_FOUND", String.format("Product with id=%s not found", executePaymentDto.getProductId()));
        }

        ProductEntity productEntity = product.get();

        if (productEntity.getBalance() < executePaymentDto.getPrice()) {
            throw new BadRequestException("LOW_BALANCE", String.format("Product with id=%s has less balance than requested amount", executePaymentDto.getProductId()));
        }

        productEntity.setBalance(productEntity.getBalance() - executePaymentDto.getPrice());
        this.update(productEntity);
    }
}
