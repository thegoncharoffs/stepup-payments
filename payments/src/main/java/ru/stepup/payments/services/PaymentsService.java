package ru.stepup.payments.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stepup.payments.dtos.ExecutePaymentDtoRq;
import ru.stepup.payments.dtos.ProductDto;
import ru.stepup.payments.exceptions.BadRequestException;
import ru.stepup.payments.integrations.ProductsIntegration;

@Service
@AllArgsConstructor
public class PaymentsService {
    private final ProductsIntegration productsIntegration;

    public void execute(ExecutePaymentDtoRq executePaymentDtoRq, String userId) {
        // Check for existing product
        ProductDto productDto = productsIntegration.getProductById(executePaymentDtoRq.getProductId(), userId);

        // Check for balance
        if (productDto.getBalance() < executePaymentDtoRq.getPrice()) {
            throw new BadRequestException("LOW_BALANCE", "Low balance");
        }

        // Update product balance
        productsIntegration.execute(executePaymentDtoRq, userId);
    }

}
