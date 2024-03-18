package ru.stepup.payments.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.stepup.payments.exceptions.dtos.IntegrationErrorDto;

@AllArgsConstructor
@Getter
public class IntegrationException extends RuntimeException {
    private IntegrationErrorDto integrationErrorDto;

    public IntegrationException(String message, IntegrationErrorDto integrationErrorDto) {
        super(message);
        this.integrationErrorDto = integrationErrorDto;
    }
}
