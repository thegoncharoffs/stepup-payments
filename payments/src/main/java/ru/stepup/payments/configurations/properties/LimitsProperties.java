package ru.stepup.payments.configurations.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "integrations.limits-v1")
@Getter
public class LimitsProperties {
    private final RestTemplateProperties client;

    @ConstructorBinding
    public LimitsProperties(RestTemplateProperties client) {
        this.client = client;
    }
}
