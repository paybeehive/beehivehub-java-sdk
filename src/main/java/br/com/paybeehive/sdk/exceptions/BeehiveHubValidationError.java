package br.com.paybeehive.sdk.exceptions;

public class BeehiveHubValidationError extends BeehiveHubError {

    public BeehiveHubValidationError(String message, Object details) {
        super(message, 400, "validation_error", details);
    }

    public BeehiveHubValidationError(String message) {
        super(message, 400, "validation_error", null);
    }
}
