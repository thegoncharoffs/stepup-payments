package ru.stepup.payments.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.stepup.payments.dtos.ExecutePaymentDtoRq;
import ru.stepup.payments.exceptions.BadRequestException;
import ru.stepup.payments.services.PaymentsService;

@RestController
@RequestMapping("/api/v1/payments")
@AllArgsConstructor
public class PaymentsController {

    private final PaymentsService paymentsService;

    @PostMapping("/execute")
    public void execute(@RequestBody ExecutePaymentDtoRq executePaymentDtoRq, @RequestHeader("USERID") String userId) {
        // Check for USERID header
        if (userId == null) {
            throw new BadRequestException("EMPTY_USERID", "USERID header is not set");
        }

        paymentsService.execute(executePaymentDtoRq, userId);
    }
}
