package ru.stepup.products.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stepup.products.entities.ProductEntity;
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
}
