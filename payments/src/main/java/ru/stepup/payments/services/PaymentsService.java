package ru.stepup.payments.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stepup.payments.dtos.ExecutePaymentDtoRq;
import ru.stepup.payments.dtos.ProductDto;
import ru.stepup.payments.exceptions.BadRequestException;
import ru.stepup.payments.integrations.LimitsIntegration;
import ru.stepup.payments.integrations.ProductsIntegration;

@Service
@AllArgsConstructor
public class PaymentsService {
    private final ProductsIntegration productsIntegration;
    private final LimitsIntegration limitsIntegration;


    public void execute(ExecutePaymentDtoRq executePaymentDtoRq, long userId) {
        // Check for existing product
        ProductDto productDto = productsIntegration.getProductById(executePaymentDtoRq.getProductId(), userId);

        // Check for balance
        if (productDto.getBalance() < executePaymentDtoRq.getPrice()) {
            throw new BadRequestException("LOW_BALANCE", "Low balance");
        }

        // Lower limit or create new one if not exists
        limitsIntegration.lowerLimit(executePaymentDtoRq.getPrice(), userId);

        try  {
            // Update product balance
            productsIntegration.execute(executePaymentDtoRq, userId);
        } catch (RuntimeException exception) {
            // Restore limit in case of execute problems
            limitsIntegration.restoreLimit(executePaymentDtoRq.getPrice(), userId);
            // Throw exception further
            throw exception;
        }
    }
}
