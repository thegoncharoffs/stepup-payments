package ru.stepup.limits.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stepup.limits.entities.LimitEntity;


public interface LimitsRepository extends JpaRepository<LimitEntity, Long> {
}
