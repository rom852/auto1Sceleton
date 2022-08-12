package com.auto1.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.*;

public class BaseApi<T> {

    @Value("${urls.base_url}")
    public String baseUrl;

    public ResponseEntity postRequest(String url, T body, Class clazz) {
        HttpEntity payload = new HttpEntity(body);
        return getRestTemplate().exchange(url, POST, payload, clazz);
    }

    public ResponseEntity<String> deleteRequest(String url) {
        HttpEntity<?> payload = new HttpEntity<>(new HttpHeaders());
        return getRestTemplate().exchange(url, DELETE, payload, String.class);
    }

    public ResponseEntity getRequest(String url, Class clazz) {
        HttpEntity<?> payload = new HttpEntity<>(new HttpHeaders());
        return getRestTemplate().exchange(url, GET, payload, clazz);
    }

    public ResponseEntity putRequest(String url, T body, Class clazz) {
        HttpEntity payload = new HttpEntity(body);
        return getRestTemplate().exchange(url, PUT, payload, clazz);
    }

    private RestTemplate getRestTemplate() {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        return new RestTemplate(requestFactory);
    }

}