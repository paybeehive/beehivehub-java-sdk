package br.com.paybeehive.sdk.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BeehiveHubExceptionsTest {

    @Test
    void baseErrorStoresFields() {
        BeehiveHubError error = new BeehiveHubError("Something went wrong", 500, "api_error", "details");
        assertEquals(500, error.getStatusCode());
        assertEquals("api_error", error.getErrorCode());
        assertEquals("details", error.getDetails());
        assertTrue(error.getMessage().contains("Something went wrong"));
    }

    @Test
    void baseErrorAppendsDocs() {
        BeehiveHubError error = new BeehiveHubError("Test");
        assertTrue(error.getMessage().contains("https://"));
    }

    @Test
    void apiErrorHasCorrectCode() {
        BeehiveHubAPIError error = new BeehiveHubAPIError("API failure", 503, null);
        assertEquals("api_error", error.getErrorCode());
        assertEquals(503, error.getStatusCode());
    }

    @Test
    void authenticationErrorDefaults() {
        BeehiveHubAuthenticationError error = new BeehiveHubAuthenticationError();
        assertEquals(401, error.getStatusCode());
        assertEquals("authentication_error", error.getErrorCode());
    }

    @Test
    void authenticationErrorWithMessage() {
        BeehiveHubAuthenticationError error = new BeehiveHubAuthenticationError("Invalid key", null);
        assertEquals(401, error.getStatusCode());
        assertTrue(error.getMessage().contains("Invalid key"));
    }

    @Test
    void validationErrorHasCorrectStatusAndCode() {
        BeehiveHubValidationError error = new BeehiveHubValidationError("Bad request", "field errors");
        assertEquals(400, error.getStatusCode());
        assertEquals("validation_error", error.getErrorCode());
        assertEquals("field errors", error.getDetails());
    }

    @Test
    void notFoundErrorHasCorrectStatusAndCode() {
        BeehiveHubNotFoundError error = new BeehiveHubNotFoundError("Resource not found");
        assertEquals(404, error.getStatusCode());
        assertEquals("not_found", error.getErrorCode());
    }

    @Test
    void rateLimitErrorDefaults() {
        BeehiveHubRateLimitError error = new BeehiveHubRateLimitError();
        assertEquals(429, error.getStatusCode());
        assertEquals("rate_limit_error", error.getErrorCode());
    }

    @Test
    void networkErrorStoresCause() {
        RuntimeException cause = new RuntimeException("Connection refused");
        BeehiveHubNetworkError error = new BeehiveHubNetworkError("Network error", cause);
        assertEquals("network_error", error.getErrorCode());
        assertNull(error.getStatusCode());
        assertNotNull(error.getCause());
    }

    @Test
    void allErrorsAreRuntimeExceptions() {
        assertInstanceOf(RuntimeException.class, new BeehiveHubError("e"));
        assertInstanceOf(RuntimeException.class, new BeehiveHubAPIError("e"));
        assertInstanceOf(RuntimeException.class, new BeehiveHubAuthenticationError());
        assertInstanceOf(RuntimeException.class, new BeehiveHubValidationError("e"));
        assertInstanceOf(RuntimeException.class, new BeehiveHubNotFoundError("e"));
        assertInstanceOf(RuntimeException.class, new BeehiveHubRateLimitError());
        assertInstanceOf(RuntimeException.class, new BeehiveHubNetworkError("e"));
    }

    @Test
    void errorToStringContainsClassName() {
        BeehiveHubAPIError error = new BeehiveHubAPIError("test");
        assertTrue(error.toString().contains("BeehiveHubAPIError"));
    }
}
