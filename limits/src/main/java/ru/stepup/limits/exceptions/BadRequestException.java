package ru.stepup.limits.exceptions;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {
    private final String code;

    public BadRequestException(String code, String message) {
        super(message);
        this.code = code;
    }
}