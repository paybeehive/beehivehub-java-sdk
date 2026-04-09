package br.com.paybeehive.sdk.exceptions;

public class BeehiveHubAPIError extends BeehiveHubError {

    public BeehiveHubAPIError(String message, Integer statusCode, Object details) {
        super(message, statusCode, "api_error", details);
    }

    public BeehiveHubAPIError(String message) {
        super(message, null, "api_error", null);
    }
}
