package ru.stepup.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stepup.products.entities.ProductEntity;

public interface ProductsRepository extends JpaRepository<ProductEntity, Long> {
}
