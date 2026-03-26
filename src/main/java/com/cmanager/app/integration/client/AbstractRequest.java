package com.cmanager.app.integration.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class AbstractRequest<T> {

    private final RestTemplate restTemplate;

    private static final String BAR = "/";

    public AbstractRequest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<T> getById(String url, Object id, Class<T> typeReference) {
        url = url + BAR + id.toString();
        return restTemplate.getForEntity(url, typeReference);
    }

    public ResponseEntity<List<Object>> getById(String url) {

        URI uri = UriComponentsBuilder.fromUriString(url)
                .build()
                .toUri();

        return restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>(){});
    }

    public ResponseEntity<List<Object>> getByText(String url, String text) {
        URI uri = UriComponentsBuilder.fromUriString(url)
                .queryParam("q", text)
                .build()
                .toUri();

        return restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>(){});
    }
}
