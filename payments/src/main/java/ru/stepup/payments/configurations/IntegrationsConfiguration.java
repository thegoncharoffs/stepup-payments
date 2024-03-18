package ru.stepup.payments.configurations;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.stepup.payments.configurations.properties.ProductsProperties;
import ru.stepup.payments.exceptions.RestTemplateResponseErrorHandler;
import ru.stepup.payments.integrations.ProductsIntegration;

@Configuration
public class IntegrationsConfiguration {
    @Bean
    public ProductsIntegration productsIntegration(
            ProductsProperties productsProperties,
            RestTemplateResponseErrorHandler restTemplateResponseErrorHandler
    ) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(productsProperties.getClient().getUrl())
                .setConnectTimeout(productsProperties.getClient().getConnectTimeout())
                .setReadTimeout(productsProperties.getClient().getReadTimeout())
                .errorHandler(restTemplateResponseErrorHandler)
                .build();

        return new ProductsIntegration(restTemplate);
    }
}
