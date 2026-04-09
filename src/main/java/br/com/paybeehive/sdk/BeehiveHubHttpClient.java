package br.com.paybeehive.sdk;

import br.com.paybeehive.sdk.exceptions.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import java.util.Map;
import java.util.stream.Collectors;

public class BeehiveHubHttpClient implements ApiClient {

    private final String baseUrl;
    private final String authHeader;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public BeehiveHubHttpClient(String apiKey, String environment) {
        this.baseUrl = "sandbox".equals(environment)
                ? BeehiveHubConstants.SANDBOX_API_URL
                : BeehiveHubConstants.PRODUCTION_API_URL;

        String encoded = Base64.getEncoder().encodeToString((apiKey + ":x").getBytes(StandardCharsets.UTF_8));
        this.authHeader = "Basic " + encoded;

        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(30))
                .build();

        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    BeehiveHubHttpClient(String apiKey, String baseUrl, HttpClient httpClient) {
        this.baseUrl = baseUrl;

        String encoded = Base64.getEncoder().encodeToString((apiKey + ":x").getBytes(StandardCharsets.UTF_8));
        this.authHeader = "Basic " + encoded;

        this.httpClient = httpClient;

        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public <T> T get(String path, Map<String, String> queryParams, Class<T> responseType) {
        String url = buildUrl(path, queryParams);
        HttpRequest request = buildRequest(url)
                .GET()
                .build();
        return execute(request, responseType);
    }

    @Override
    public <T> T post(String path, Object body, Class<T> responseType) {
        String url = buildUrl(path, null);
        HttpRequest request = buildRequest(url)
                .POST(bodyPublisher(body))
                .build();
        return execute(request, responseType);
    }

    @Override
    public <T> T put(String path, Object body, Class<T> responseType) {
        String url = buildUrl(path, null);
        HttpRequest request = buildRequest(url)
                .PUT(bodyPublisher(body))
                .build();
        return execute(request, responseType);
    }

    @Override
    public void delete(String path) {
        String url = buildUrl(path, null);
        HttpRequest request = buildRequest(url)
                .DELETE()
                .build();
        execute(request, Void.class);
    }

    private String buildUrl(String path, Map<String, String> queryParams) {
        String url = baseUrl + path;
        if (queryParams != null && !queryParams.isEmpty()) {
            String query = queryParams.entrySet().stream()
                    .filter(e -> e.getValue() != null)
                    .map(e -> URLEncoder.encode(e.getKey(), StandardCharsets.UTF_8)
                            + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
                    .collect(Collectors.joining("&"));
            url = url + "?" + query;
        }
        return url;
    }

    private HttpRequest.Builder buildRequest(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(30))
                .header("Authorization", authHeader)
                .header("Content-Type", "application/json")
                .header("User-Agent", BeehiveHubConstants.USER_AGENT);
    }

    private HttpRequest.BodyPublisher bodyPublisher(Object body) {
        if (body == null) {
            return HttpRequest.BodyPublishers.noBody();
        }
        try {
            return HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(body));
        } catch (JsonProcessingException e) {
            throw new BeehiveHubError("Failed to serialize request body: " + e.getMessage());
        }
    }

    private <T> T execute(HttpRequest request, Class<T> responseType) {
        HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new BeehiveHubNetworkError("Network error: " + e.getMessage(), e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new BeehiveHubNetworkError("Request interrupted: " + e.getMessage(), e);
        }

        int statusCode = response.statusCode();
        String responseBody = response.body();

        if (statusCode >= 200 && statusCode < 300) {
            if (responseType == Void.class || responseBody == null || responseBody.isBlank()) {
                return null;
            }
            try {
                return objectMapper.readValue(responseBody, responseType);
            } catch (JsonProcessingException e) {
                throw new BeehiveHubAPIError("Failed to parse response: " + e.getMessage(), statusCode, responseBody);
            }
        }

        Object errorDetails = parseErrorDetails(responseBody);
        String errorMessage = extractErrorMessage(responseBody, statusCode);

        switch (statusCode) {
            case 400:
                throw new BeehiveHubValidationError(errorMessage, errorDetails);
            case 401:
                throw new BeehiveHubAuthenticationError(errorMessage, errorDetails);
            case 404:
                throw new BeehiveHubNotFoundError(errorMessage, errorDetails);
            case 429:
                throw new BeehiveHubRateLimitError(errorMessage, errorDetails);
            default:
                throw new BeehiveHubAPIError(errorMessage, statusCode, errorDetails);
        }
    }

    private Object parseErrorDetails(String responseBody) {
        if (responseBody == null || responseBody.isBlank()) return null;
        try {
            return objectMapper.readValue(responseBody, Object.class);
        } catch (Exception e) {
            return responseBody;
        }
    }

    private String extractErrorMessage(String responseBody, int statusCode) {
        if (responseBody == null || responseBody.isBlank()) {
            return "HTTP " + statusCode + " error";
        }
        try {
            Map<?, ?> parsed = objectMapper.readValue(responseBody, Map.class);
            Object msg = parsed.get("message");
            if (msg != null) return msg.toString();
        } catch (Exception ignored) {}
        return "HTTP " + statusCode + " error";
    }
}
