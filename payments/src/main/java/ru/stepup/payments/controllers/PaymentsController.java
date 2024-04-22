package ru.stepup.payments.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.stepup.payments.dtos.ExecutePaymentDtoRq;
import ru.stepup.payments.services.PaymentsService;

@RestController
@RequestMapping("/api/v1/payments")
@AllArgsConstructor
public class PaymentsController {

    private final PaymentsService paymentsService;

    @PostMapping("/execute")
    public void execute(@RequestBody ExecutePaymentDtoRq executePaymentDtoRq, @RequestHeader("USERID") long userId) {
        paymentsService.execute(executePaymentDtoRq, userId);
    }
}
