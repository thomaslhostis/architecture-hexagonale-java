package com.github.thomaslhostis.architecturehexagonalejava.core.infrastructure.apis.idf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class IdfTrainStationsTemplateConfiguration {
    private final RestTemplateBuilder templateBuilder;

    @Value("${timeout.rest-template.connect}")
    private Long connectTimeout;
    @Value("${timeout.rest-template.read}")
    private Long readTimeout;

    @Autowired
    public IdfTrainStationsTemplateConfiguration(RestTemplateBuilder templateBuilder) {
        this.templateBuilder = templateBuilder;
    }

    @Bean
    public RestTemplate idfTrainStationsRestTemplate() {
        return templateBuilder
                .setConnectTimeout(Duration.ofSeconds(connectTimeout))
                .setReadTimeout(Duration.ofSeconds(readTimeout))
                .build();
    }
}
