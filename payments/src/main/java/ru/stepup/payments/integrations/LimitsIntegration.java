package ru.stepup.payments.integrations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import ru.stepup.payments.dtos.ChangeLimitDto;
import ru.stepup.payments.dtos.LimitDto;

import java.util.Collections;

@AllArgsConstructor
@Slf4j
public class LimitsIntegration {
    private final RestTemplate restTemplate;

    public LimitDto getLimitById(Long userId) {
        // Send request
        ResponseEntity<LimitDto> responseEntity = restTemplate.getForEntity(
                "/limits/" + userId,
                LimitDto.class
        );

        log.info("getLimitById: " + responseEntity.getBody());
        return responseEntity.getBody();
    }

    public void lowerLimit(long price, Long userId) {
        // Set headers and body
        ChangeLimitDto changeLimitDto = new ChangeLimitDto(price);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<ChangeLimitDto> request = new HttpEntity<>(changeLimitDto, httpHeaders);

        // Send request
        restTemplate.postForLocation(
                String.format("/limits/%s/lower", userId),
                request
        );

        log.info("lowerLimit: " + changeLimitDto);
    }

    public void restoreLimit(long price, Long userId) {
        // Set headers and body
        ChangeLimitDto changeLimitDto = new ChangeLimitDto(price);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<ChangeLimitDto> request = new HttpEntity<>(changeLimitDto, httpHeaders);

        // Send request
        restTemplate.postForLocation(
                String.format("/limits/%s/restore", userId),
                request
        );

        log.info("restoreLimit: " + changeLimitDto);
    }
}
