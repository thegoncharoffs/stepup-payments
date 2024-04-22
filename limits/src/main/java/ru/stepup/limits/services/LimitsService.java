package ru.stepup.limits.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stepup.limits.entities.LimitEntity;
import ru.stepup.limits.exceptions.BadRequestException;
import ru.stepup.limits.exceptions.ResourceNotFoundException;
import ru.stepup.limits.repositories.LimitsRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LimitsService {

    private final LimitsRepository limitsRepository;

    @Value("${payments.limit}")
    private long paymentLimit;

    public Optional<LimitEntity> findById(Long id) {
        return limitsRepository.findById(id);
    }

    @Transactional
    public void lowerLimit(long userId, long price) {
        Optional<LimitEntity> limitEntityOptional = limitsRepository.findById(userId);

        if (price < 0) {
            throw new BadRequestException("LIMIT_NEGATIVE_PRICE", "Price must be positive value")
        }

        if (limitEntityOptional.isEmpty()) {
            limitsRepository.save(new LimitEntity(userId, paymentLimit - price));
        } else {
            LimitEntity limitEntity = limitEntityOptional.get();
            long newLimit = limitEntity.getPaymentLimit() - price;

            if (newLimit < 0) {
                throw new BadRequestException("LIMIT_EXCEEDED", "Limit is exceeded");
            }

            limitEntity.setPaymentLimit(newLimit);
            limitsRepository.save(limitEntity);
        }
    }

    public void restoreLimit(long userId, long price) {
        Optional<LimitEntity> limitEntityOptional = limitsRepository.findById(userId);

        if (price < 0) {
            throw new BadRequestException("LIMIT_NEGATIVE_PRICE", "Price must be positive value")
        }

        if (limitEntityOptional.isEmpty()) {
            throw new ResourceNotFoundException("LIMIT_NOT_FOUND", "Limit not found");
        } else {
            LimitEntity limitEntity = limitEntityOptional.get();
            limitEntity.setPaymentLimit(limitEntity.getPaymentLimit() + price);
            limitsRepository.save(limitEntity);
        }
    }
}
