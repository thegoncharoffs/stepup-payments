package ru.stepup.limits.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.stepup.limits.entities.LimitEntity;
import ru.stepup.limits.repositories.LimitsRepository;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class ScheduledTasks {
    private final LimitsRepository limitsRepository;

    @Scheduled(cron = "${cron.reset-limits}")
    public void resetLimit() {
        List<LimitEntity> limitEntities = limitsRepository.findAll();
        limitEntities.forEach(limitEntity -> limitEntity.setPaymentLimit(10_000L));
        limitsRepository.saveAll(limitEntities);
        log.info("CRON: resetLimit");
    }
}
