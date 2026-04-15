package br.com.paybeehive.sdk;

import br.com.paybeehive.sdk.exceptions.*;
import br.com.paybeehive.sdk.models.Balance;
import br.com.paybeehive.sdk.models.Transaction;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

class BeehiveHubHttpClientTest {

    private WireMockServer wireMock;
    private BeehiveHubHttpClient client;

    @BeforeEach
    void setUp() {
        wireMock = new WireMockServer(WireMockConfiguration.options().dynamicPort());
        wireMock.start();

        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        client = new BeehiveHubHttpClient("test_api_key", wireMock.baseUrl() + "/v1", httpClient);
    }

    @AfterEach
    void tearDown() {
        wireMock.stop();
    }

    @Test
    void getDeserializesResponseSuccessfully() {
        wireMock.stubFor(get(urlEqualTo("/v1/balance/available"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"amount\": 50000, \"recipient_id\": 1}")));

        Balance balance = client.get("/balance/available", null, Balance.class);

        assertNotNull(balance);
        assertEquals(50000L, balance.getAmount());
        assertEquals(1L, balance.getRecipientId());
    }

    @Test
    void postSendsBodyAndDeserializesResponse() {
        wireMock.stubFor(post(urlEqualTo("/v1/transactions"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\": 1, \"status\": \"processing\", \"amount\": 10000}")));

        Transaction tx = client.post("/transactions", "{\"payment_method\":\"pix\"}", Transaction.class);

        assertEquals(1L, tx.getId());
        assertEquals("processing", tx.getStatus());
    }

    @Test
    void postWithNullBodySendsNoBody() {
        wireMock.stubFor(post(urlEqualTo("/v1/transactions"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\": 2, \"status\": \"processing\", \"amount\": 0}")));

        Transaction tx = client.post("/transactions", null, Transaction.class);

        assertNotNull(tx);
    }

    @Test
    void putSendsBodyAndDeserializesResponse() {
        wireMock.stubFor(put(urlEqualTo("/v1/company"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\": 1, \"invoice_descriptor\": \"BEEHIVE*PAY\"}")));

        br.com.paybeehive.sdk.models.Company company =
                client.put("/company", "{}", br.com.paybeehive.sdk.models.Company.class);

        assertEquals("BEEHIVE*PAY", company.getInvoiceDescriptor());
    }

    @Test
    void deleteReturnsNoContent() {
        wireMock.stubFor(delete(urlEqualTo("/v1/payment-links/5"))
                .willReturn(aResponse().withStatus(204)));

        assertDoesNotThrow(() -> client.delete("/payment-links/5"));
    }

    @Test
    void successResponseWithEmptyBodyReturnsNull() {
        wireMock.stubFor(delete(urlEqualTo("/v1/payment-links/9"))
                .willReturn(aResponse().withStatus(200).withBody("")));

        assertDoesNotThrow(() -> client.delete("/payment-links/9"));
    }

    @Test
    void throws401AsAuthenticationError() {
        wireMock.stubFor(get(urlEqualTo("/v1/balance/available"))
                .willReturn(aResponse()
                        .withStatus(401)
                        .withBody("{\"message\": \"Unauthorized\"}")));

        assertThrows(BeehiveHubAuthenticationError.class,
                () -> client.get("/balance/available", null, Balance.class));
    }

    @Test
    void throws400AsValidationError() {
        wireMock.stubFor(post(urlEqualTo("/v1/transactions"))
                .willReturn(aResponse()
                        .withStatus(400)
                        .withBody("{\"message\": \"Invalid payment method\"}")));

        assertThrows(BeehiveHubValidationError.class,
                () -> client.post("/transactions", null, Transaction.class));
    }

    @Test
    void throws404AsNotFoundError() {
        wireMock.stubFor(get(urlEqualTo("/v1/transactions/999"))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withBody("{\"message\": \"Transaction not found\"}")));

        assertThrows(BeehiveHubNotFoundError.class,
                () -> client.get("/transactions/999", null, Transaction.class));
    }

    @Test
    void throws429AsRateLimitError() {
        wireMock.stubFor(get(urlEqualTo("/v1/balance/available"))
                .willReturn(aResponse()
                        .withStatus(429)
                        .withBody("{\"message\": \"Too many requests\"}")));

        assertThrows(BeehiveHubRateLimitError.class,
                () -> client.get("/balance/available", null, Balance.class));
    }

    @Test
    void throws5xxAsApiError() {
        wireMock.stubFor(get(urlEqualTo("/v1/balance/available"))
                .willReturn(aResponse()
                        .withStatus(500)
                        .withBody("{\"message\": \"Internal server error\"}")));

        assertThrows(BeehiveHubAPIError.class,
                () -> client.get("/balance/available", null, Balance.class));
    }

    @Test
    void errorWithNoMessageFieldUsesDefaultMessage() {
        wireMock.stubFor(get(urlEqualTo("/v1/balance/available"))
                .willReturn(aResponse()
                        .withStatus(500)
                        .withBody("{\"error\": \"something went wrong\"}")));

        BeehiveHubAPIError error = assertThrows(BeehiveHubAPIError.class,
                () -> client.get("/balance/available", null, Balance.class));

        assertTrue(error.getMessage().contains("500"));
    }

    @Test
    void errorWithEmptyBodyUsesDefaultMessage() {
        wireMock.stubFor(get(urlEqualTo("/v1/balance/available"))
                .willReturn(aResponse()
                        .withStatus(503)
                        .withBody("")));

        BeehiveHubAPIError error = assertThrows(BeehiveHubAPIError.class,
                () -> client.get("/balance/available", null, Balance.class));

        assertTrue(error.getMessage().contains("503"));
    }

    @Test
    void sendsAuthorizationHeader() {
        wireMock.stubFor(get(urlEqualTo("/v1/balance/available"))
                .withHeader("Authorization", matching("Basic .*"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("{\"amount\": 0, \"recipient_id\": 1}")));

        assertDoesNotThrow(() -> client.get("/balance/available", null, Balance.class));
        wireMock.verify(getRequestedFor(urlEqualTo("/v1/balance/available"))
                .withHeader("Authorization", matching("Basic .*")));
    }

    @Test
    void sendsUserAgentHeader() {
        wireMock.stubFor(get(urlEqualTo("/v1/balance/available"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("{\"amount\": 0, \"recipient_id\": 1}")));

        client.get("/balance/available", null, Balance.class);

        wireMock.verify(getRequestedFor(urlEqualTo("/v1/balance/available"))
                .withHeader("User-Agent", containing("beehivehub-java-sdk")));
    }

    @Test
    void getWithQueryParams() {
        wireMock.stubFor(get(urlPathEqualTo("/v1/transactions"))
                .withQueryParam("limit", equalTo("10"))
                .withQueryParam("offset", equalTo("0"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("[]")));

        Map<String, String> params = new HashMap<>();
        params.put("limit", "10");
        params.put("offset", "0");

        assertDoesNotThrow(() -> client.get("/transactions", params, List.class));
    }

    @Test
    void getWithNullQueryParamValueIgnoresIt() {
        wireMock.stubFor(get(urlPathEqualTo("/v1/transactions"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("[]")));

        Map<String, String> params = new HashMap<>();
        params.put("limit", "10");
        params.put("start_date", null);

        assertDoesNotThrow(() -> client.get("/transactions", params, List.class));
    }
}
