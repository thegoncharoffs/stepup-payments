package ru.stepup.limits.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.stepup.limits.entities.LimitEntity;
import ru.stepup.limits.repositories.LimitsRepository;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class ScheduledTasks {
    @Value("${payments.limit}")
    private long paymentLimit;

    private final LimitsRepository limitsRepository;

    @Scheduled(cron = "${cron.reset-limits}")
    public void resetLimit() {
        List<LimitEntity> limitEntities = limitsRepository.findAll();
        limitEntities.forEach(limitEntity -> limitEntity.setPaymentLimit(paymentLimit));
        limitsRepository.saveAll(limitEntities);
        log.info("CRON: resetLimit");
    }
}
