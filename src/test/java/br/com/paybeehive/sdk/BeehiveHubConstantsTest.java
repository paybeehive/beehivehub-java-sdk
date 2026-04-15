package br.com.paybeehive.sdk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BeehiveHubConstantsTest {

    @Test
    void productionApiUrlIsCorrect() {
        assertEquals("https://api.conta.paybeehive.com.br/v1", BeehiveHubConstants.PRODUCTION_API_URL);
    }

    @Test
    void sandboxApiUrlIsCorrect() {
        assertEquals("https://api.sandbox.hopysplit.com.br/v1", BeehiveHubConstants.SANDBOX_API_URL);
    }

    @Test
    void productionPaymentLinkUrlIsCorrect() {
        assertEquals("https://link.conta.paybeehive.com.br", BeehiveHubConstants.PRODUCTION_PAYMENT_LINK_URL);
    }

    @Test
    void sandboxPaymentLinkUrlIsCorrect() {
        assertEquals("https://link.sandbox.hopysplit.com.br", BeehiveHubConstants.SANDBOX_PAYMENT_LINK_URL);
    }

    @Test
    void docsUrlIsSet() {
        assertNotNull(BeehiveHubConstants.DOCS_URL);
        assertTrue(BeehiveHubConstants.DOCS_URL.startsWith("https://"));
    }

    @Test
    void userAgentContainsVersion() {
        assertTrue(BeehiveHubConstants.USER_AGENT.contains("beehivehub-java-sdk"));
        assertTrue(BeehiveHubConstants.USER_AGENT.contains(BeehiveHubConstants.VERSION));
    }
}
