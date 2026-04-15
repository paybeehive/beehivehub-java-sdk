package br.com.paybeehive.sdk.resources;

import br.com.paybeehive.sdk.ApiClient;
import br.com.paybeehive.sdk.models.PaymentLink;
import br.com.paybeehive.sdk.requests.CreatePaymentLinkRequest;
import br.com.paybeehive.sdk.requests.UpdatePaymentLinkRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentLinksResourceTest {

    @Mock
    private ApiClient apiClient;

    private PaymentLinksResource productionResource;
    private PaymentLinksResource sandboxResource;

    @BeforeEach
    void setUp() {
        productionResource = new PaymentLinksResource(apiClient, "production");
        sandboxResource = new PaymentLinksResource(apiClient, "sandbox");
    }

    @Test
    void createAutoGeneratesAliasWhenMissing() {
        PaymentLink returned = new PaymentLink();
        returned.setId(1L);
        returned.setAlias("abc123xyz0");
        when(apiClient.post(eq("/payment-links"), any(), eq(PaymentLink.class))).thenReturn(returned);

        CreatePaymentLinkRequest req = new CreatePaymentLinkRequest();
        req.setAmount(10000L);
        PaymentLink result = productionResource.create(req);

        assertNotNull(req.getAlias());
        assertEquals(10, req.getAlias().length());
    }

    @Test
    void createAppendProductionUrl() {
        PaymentLink returned = new PaymentLink();
        returned.setAlias("mylink123x");
        when(apiClient.post(any(), any(), eq(PaymentLink.class))).thenReturn(returned);

        CreatePaymentLinkRequest req = new CreatePaymentLinkRequest();
        req.setAlias("mylink123x");
        PaymentLink result = productionResource.create(req);

        assertEquals("https://link.conta.paybeehive.com.br/mylink123x", result.getUrl());
    }

    @Test
    void createAppendsSandboxUrl() {
        PaymentLink returned = new PaymentLink();
        returned.setAlias("sandboxlink");
        when(apiClient.post(any(), any(), eq(PaymentLink.class))).thenReturn(returned);

        CreatePaymentLinkRequest req = new CreatePaymentLinkRequest();
        req.setAlias("sandboxlink");
        PaymentLink result = sandboxResource.create(req);

        assertEquals("https://link.sandbox.hopysplit.com.br/sandboxlink", result.getUrl());
    }

    @Test
    void listCallsGetEndpoint() {
        when(apiClient.get(eq("/payment-links"), isNull(), eq(List.class))).thenReturn(List.of());

        List<PaymentLink> result = productionResource.list();

        assertNotNull(result);
        verify(apiClient).get(eq("/payment-links"), isNull(), eq(List.class));
    }

    @Test
    void getAppendsUrl() {
        PaymentLink returned = new PaymentLink();
        returned.setId(5L);
        returned.setAlias("testAlias");
        when(apiClient.get(eq("/payment-links/5"), isNull(), eq(PaymentLink.class))).thenReturn(returned);

        PaymentLink result = productionResource.get(5L);

        assertTrue(result.getUrl().contains("testAlias"));
    }

    @Test
    void updateAutoGeneratesAliasWhenBlank() {
        PaymentLink returned = new PaymentLink();
        returned.setAlias("newAlias001");
        when(apiClient.put(eq("/payment-links/3"), any(), eq(PaymentLink.class))).thenReturn(returned);

        UpdatePaymentLinkRequest req = new UpdatePaymentLinkRequest();
        productionResource.update(3L, req);

        assertNotNull(req.getAlias());
    }

    @Test
    void deleteCallsClientDelete() {
        doNothing().when(apiClient).delete(eq("/payment-links/8"));

        productionResource.delete(8L);

        verify(apiClient).delete(eq("/payment-links/8"));
    }
}
