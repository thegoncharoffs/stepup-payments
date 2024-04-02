package ru.stepup.limits.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.stepup.limits.entities.LimitEntity;
import ru.stepup.limits.exceptions.BadRequestException;
import ru.stepup.limits.exceptions.ResourceNotFoundException;
import ru.stepup.limits.repositories.LimitsRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LimitsService {

    private final LimitsRepository limitsRepository;

    public Optional<LimitEntity> findById(Long id) {
        return limitsRepository.findById(id);
    }

    public void lowerLimit(long userId, long price) {
        Optional<LimitEntity> limitEntityOptional = limitsRepository.findById(userId);

        if (limitEntityOptional.isEmpty()) {
            limitsRepository.save(new LimitEntity(userId, 10_000L - price));
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

        if (limitEntityOptional.isEmpty()) {
            throw new ResourceNotFoundException("LIMIT_NOT_FOUND", "Limit is exceeded");
        } else {
            LimitEntity limitEntity = limitEntityOptional.get();
            limitEntity.setPaymentLimit(limitEntity.getPaymentLimit() + price);
            limitsRepository.save(limitEntity);
        }
    }
}
