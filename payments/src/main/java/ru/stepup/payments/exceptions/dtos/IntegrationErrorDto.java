package ru.stepup.payments.exceptions.dtos;

import java.time.LocalDateTime;

public record IntegrationErrorDto(
        String code,
        String message,
        LocalDateTime date
) {
}
