package ru.stepup.limits.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.stepup.limits.dtos.ChangeLimitDto;
import ru.stepup.limits.dtos.LimitDto;
import ru.stepup.limits.entities.LimitEntity;
import ru.stepup.limits.exceptions.ResourceNotFoundException;
import ru.stepup.limits.services.LimitsService;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/limits")
public class LimitsController {
    private final LimitsService limitsService;

    @GetMapping("/{userId}")
    public LimitDto get(@PathVariable Long userId) {
        Optional<LimitEntity> limitEntityOptional = limitsService.findById(userId);

        if (limitEntityOptional.isEmpty()) {
            throw new ResourceNotFoundException("LIMIT_NOT_FOUND", "Limit not found");
        }

        LimitDto limitDto = new LimitDto(limitEntityOptional.get().getUserId(), limitEntityOptional.get().getPaymentLimit());
        log.info("Limit for user " + limitDto.getUserId() + " is " + limitDto.getPaymentLimit());

        return limitDto;

    }

    @PostMapping("/{userId}/lower")
    public void lower(@RequestBody ChangeLimitDto changeLimitDto, @PathVariable Long userId) {
        limitsService.lowerLimit(userId, changeLimitDto.getPrice());
        log.info("Limit for user " + userId + " is lowered by " + changeLimitDto.getPrice());
    }

    @PostMapping("/{userId}/restore")
    public void restore(@RequestBody ChangeLimitDto changeLimitDto, @PathVariable Long userId) {
        limitsService.restoreLimit(userId, changeLimitDto.getPrice());
        log.info("Limit for user " + userId + " is lowered by " + changeLimitDto.getPrice());
    }
}
