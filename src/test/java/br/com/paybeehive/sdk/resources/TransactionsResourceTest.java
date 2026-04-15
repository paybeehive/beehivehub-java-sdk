package br.com.paybeehive.sdk.resources;

import br.com.paybeehive.sdk.ApiClient;
import br.com.paybeehive.sdk.models.Transaction;
import br.com.paybeehive.sdk.requests.CreateTransactionRequest;
import br.com.paybeehive.sdk.requests.ListTransactionsParams;
import br.com.paybeehive.sdk.requests.UpdateDeliveryStatusRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionsResourceTest {

    @Mock
    private ApiClient apiClient;

    private TransactionsResource resource;

    @BeforeEach
    void setUp() {
        resource = new TransactionsResource(apiClient);
    }

    @Test
    void createCallsPostWithCorrectPath() {
        Transaction expected = new Transaction();
        expected.setId(1L);
        expected.setStatus("paid");
        when(apiClient.post(eq("/transactions"), any(), eq(Transaction.class))).thenReturn(expected);

        CreateTransactionRequest request = new CreateTransactionRequest();
        request.setPaymentMethod("pix");
        Transaction result = resource.create(request);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("paid", result.getStatus());
        verify(apiClient).post(eq("/transactions"), eq(request), eq(Transaction.class));
    }

    @Test
    void listCallsGetWithNoParams() {
        when(apiClient.get(eq("/transactions"), any(), eq(List.class))).thenReturn(List.of());

        List<Transaction> result = resource.list(null);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(apiClient).get(eq("/transactions"), any(Map.class), eq(List.class));
    }

    @Test
    void listPassesFilterParams() {
        when(apiClient.get(eq("/transactions"), any(), eq(List.class))).thenReturn(List.of());

        ListTransactionsParams params = new ListTransactionsParams();
        params.setLimit(10);
        params.setOffset(0);
        params.setStartDate("2024-01-01");
        params.setEndDate("2024-12-31");

        resource.list(params);

        verify(apiClient).get(eq("/transactions"), argThat(map -> {
            Map<?, ?> m = (Map<?, ?>) map;
            return "10".equals(m.get("limit")) &&
                    "0".equals(m.get("offset")) &&
                    "2024-01-01".equals(m.get("start_date")) &&
                    "2024-12-31".equals(m.get("end_date"));
        }), eq(List.class));
    }

    @Test
    void getCallsCorrectPath() {
        Transaction expected = new Transaction();
        expected.setId(42L);
        when(apiClient.get(eq("/transactions/42"), isNull(), eq(Transaction.class))).thenReturn(expected);

        Transaction result = resource.get(42L);

        assertEquals(42L, result.getId());
    }

    @Test
    void refundWithAmountSendsBody() {
        Transaction expected = new Transaction();
        expected.setStatus("refunded");
        when(apiClient.post(eq("/transactions/10/refund"), any(), eq(Transaction.class))).thenReturn(expected);

        Transaction result = resource.refund(10L, 5000L);

        assertEquals("refunded", result.getStatus());
        verify(apiClient).post(eq("/transactions/10/refund"), argThat(b -> b != null), eq(Transaction.class));
    }

    @Test
    void refundWithoutAmountSendsNullBody() {
        Transaction expected = new Transaction();
        when(apiClient.post(eq("/transactions/5/refund"), isNull(), eq(Transaction.class))).thenReturn(expected);

        resource.refund(5L, null);

        verify(apiClient).post(eq("/transactions/5/refund"), isNull(), eq(Transaction.class));
    }

    @Test
    void updateDeliveryCallsPut() {
        Transaction expected = new Transaction();
        when(apiClient.put(eq("/transactions/7/delivery"), any(), eq(Transaction.class))).thenReturn(expected);

        UpdateDeliveryStatusRequest req = new UpdateDeliveryStatusRequest();
        req.setStatus("shipped");
        req.setTrackingCode("BR123456789");

        resource.updateDelivery(7L, req);

        verify(apiClient).put(eq("/transactions/7/delivery"), eq(req), eq(Transaction.class));
    }
}
