package ru.laverno.service;

import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.net.URL;

public abstract class BasicService {

    protected <T> T sendRequest(final URL url, final Class<T> clazz) {
        try {
            RestTemplate rt = new RestTemplate();
            final var response = rt.getForEntity(url.toURI(), clazz);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody();
            }
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
