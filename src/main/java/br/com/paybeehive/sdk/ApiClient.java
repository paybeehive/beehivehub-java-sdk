package br.com.paybeehive.sdk;

import java.util.Map;

public interface ApiClient {

    <T> T get(String path, Map<String, String> queryParams, Class<T> responseType);

    <T> T post(String path, Object body, Class<T> responseType);

    <T> T put(String path, Object body, Class<T> responseType);

    void delete(String path);
}
