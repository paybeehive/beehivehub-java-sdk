package br.com.paybeehive.sdk.exceptions;

public class BeehiveHubAuthenticationError extends BeehiveHubError {

    public BeehiveHubAuthenticationError(String message, Object details) {
        super(message, 401, "authentication_error", details);
    }

    public BeehiveHubAuthenticationError() {
        super("Invalid API key. Check your credentials.", 401, "authentication_error", null);
    }
}
