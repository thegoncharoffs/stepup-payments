package ru.stepup.payments.integrations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.stepup.payments.dtos.ProductDto;
import ru.stepup.payments.exceptions.IntegrationException;

import java.util.Collections;

@AllArgsConstructor
@Slf4j
public class ProductsIntegration {
    private final RestTemplate restTemplate;

    public ProductDto getProductById(String id, String userId) {
        try {
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
        } catch (IntegrationException e) {
            log.info("getProductById error: " + e.getIntegrationErrorDto());
            return null;
        }
    }

    public void execute(ProductDto productDto, String userId) {
        try {
            // Set headers and body
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            httpHeaders.set("USERID", userId);
            HttpEntity<ProductDto> request = new HttpEntity<>(productDto, httpHeaders);

            // Send request
            restTemplate.put(
                    "/products",
                    request
            );

            log.info("execute: " + productDto);
        } catch (IntegrationException e) {
            log.info("execute error: " + e.getIntegrationErrorDto());
        }
    }
}
