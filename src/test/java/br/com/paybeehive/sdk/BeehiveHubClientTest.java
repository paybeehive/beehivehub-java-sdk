package br.com.paybeehive.sdk;

import br.com.paybeehive.sdk.exceptions.BeehiveHubError;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BeehiveHubClientTest {

    @Test
    void throwsWhenApiKeyIsNull() {
        assertThrows(BeehiveHubError.class, () -> new BeehiveHubClient(null));
    }

    @Test
    void throwsWhenApiKeyIsBlank() {
        assertThrows(BeehiveHubError.class, () -> new BeehiveHubClient("   "));
        assertThrows(BeehiveHubError.class, () -> new BeehiveHubClient(""));
    }

    @Test
    void createsProductionClientSuccessfully() {
        BeehiveHubClient client = new BeehiveHubClient("test_key");
        assertNotNull(client.transactions);
        assertNotNull(client.customers);
        assertNotNull(client.balance);
        assertNotNull(client.recipients);
        assertNotNull(client.bankAccounts);
        assertNotNull(client.transfers);
        assertNotNull(client.company);
        assertNotNull(client.paymentLinks);
    }

    @Test
    void createsSandboxClientSuccessfully() {
        BeehiveHubClient client = new BeehiveHubClient("test_key", "sandbox");
        assertNotNull(client.transactions);
        assertNotNull(client.paymentLinks);
    }

    @Test
    void errorMessageContainsApiKeyHint() {
        BeehiveHubError error = assertThrows(BeehiveHubError.class, () -> new BeehiveHubClient(null));
        assertTrue(error.getMessage().toLowerCase().contains("api key"));
    }
}
