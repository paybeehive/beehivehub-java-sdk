package br.com.paybeehive.sdk.exceptions;

public class BeehiveHubNotFoundError extends BeehiveHubError {

    public BeehiveHubNotFoundError(String message, Object details) {
        super(message, 404, "not_found", details);
    }

    public BeehiveHubNotFoundError(String message) {
        super(message, 404, "not_found", null);
    }
}
