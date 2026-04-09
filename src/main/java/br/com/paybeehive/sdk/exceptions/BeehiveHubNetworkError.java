package br.com.paybeehive.sdk.exceptions;

public class BeehiveHubNetworkError extends BeehiveHubError {

    public BeehiveHubNetworkError(String message, Throwable cause) {
        super(message, null, "network_error", cause != null ? cause.getMessage() : null);
        if (cause != null) {
            initCause(cause);
        }
    }

    public BeehiveHubNetworkError(String message) {
        super(message, null, "network_error", null);
    }
}
