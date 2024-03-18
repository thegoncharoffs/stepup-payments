package ru.stepup.payments.configurations.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "integrations.products-v1")
@Getter
public class ProductsProperties {
    private final RestTemplateProperties client;

    @ConstructorBinding
    public ProductsProperties(RestTemplateProperties client) {
        this.client = client;
    }
}
