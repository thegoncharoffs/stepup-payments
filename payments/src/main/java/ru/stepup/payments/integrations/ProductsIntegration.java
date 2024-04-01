package ru.stepup.payments.integrations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import ru.stepup.payments.dtos.ExecutePaymentDtoRq;
import ru.stepup.payments.dtos.ProductDto;

import java.util.Collections;

@AllArgsConstructor
@Slf4j
public class ProductsIntegration {
    private final RestTemplate restTemplate;

    public ProductDto getProductById(long id, String userId) {
        // Set headers
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.set("USERID", userId);
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        // Send request
        ResponseEntity<ProductDto> responseEntity = restTemplate.exchange(
                "/products/" + id,
                HttpMethod.GET,
                requestEntity,
                ProductDto.class
        );

        log.info("getProductById: " + responseEntity.getBody());
        return responseEntity.getBody();
    }

    public void execute(ExecutePaymentDtoRq executePaymentDtoRq, String userId) {
        // Set headers and body
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.set("USERID", userId);
        HttpEntity<ExecutePaymentDtoRq> request = new HttpEntity<>(executePaymentDtoRq, httpHeaders);

        // Send request
        restTemplate.postForLocation(
                "/products/execute-payment",
                request
        );

        log.info("execute: " + executePaymentDtoRq);
    }
}
