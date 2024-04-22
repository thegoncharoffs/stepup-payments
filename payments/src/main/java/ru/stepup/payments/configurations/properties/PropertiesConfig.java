package ru.stepup.payments.configurations.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        ProductsProperties.class,
        LimitsProperties.class
})
public class PropertiesConfig {
}
