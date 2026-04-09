package br.com.paybeehive.sdk.exceptions;

public class BeehiveHubRateLimitError extends BeehiveHubError {

    public BeehiveHubRateLimitError(String message, Object details) {
        super(message, 429, "rate_limit_error", details);
    }

    public BeehiveHubRateLimitError() {
        super("Too many requests. Please slow down.", 429, "rate_limit_error", null);
    }
}
