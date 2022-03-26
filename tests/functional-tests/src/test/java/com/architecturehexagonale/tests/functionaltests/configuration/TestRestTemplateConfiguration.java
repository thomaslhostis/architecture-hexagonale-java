package com.architecturehexagonale.tests.functionaltests.configuration;

import org.springframework.boot.test.web.client.LocalHostUriTemplateHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TestRestTemplateConfiguration {

    @Bean
    public RestTemplate testRestTemplate(Environment environment) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new LocalHostUriTemplateHandler(environment));
        restTemplate.setErrorHandler(new NoOpResponseErrorHandler());
        return restTemplate;
    }

    private static class NoOpResponseErrorHandler extends DefaultResponseErrorHandler {
        @Override
        public void handleError(ClientHttpResponse response) {
            // Définir un handler vide permet de gérer les cas d'utilisation en échec.
            // Par exemple, lorsque dans un cas de test la réponse attendue est une erreur 400.
        }
    }
}
