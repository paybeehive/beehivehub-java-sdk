package br.com.paybeehive.sdk.exceptions;

import br.com.paybeehive.sdk.BeehiveHubConstants;

public class BeehiveHubError extends RuntimeException {

    private final Integer statusCode;
    private final String errorCode;
    private final Object details;

    public BeehiveHubError(String message) {
        super(message + " — Docs: " + BeehiveHubConstants.DOCS_URL);
        this.statusCode = null;
        this.errorCode = null;
        this.details = null;
    }

    public BeehiveHubError(String message, Integer statusCode, String errorCode, Object details) {
        super(message + " — Docs: " + BeehiveHubConstants.DOCS_URL);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.details = details;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Object getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "message='" + getMessage() + '\'' +
                ", statusCode=" + statusCode +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
}
