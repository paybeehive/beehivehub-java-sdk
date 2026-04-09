package br.com.paybeehive.sdk.resources;

import br.com.paybeehive.sdk.ApiClient;
import br.com.paybeehive.sdk.models.Transfer;
import br.com.paybeehive.sdk.requests.CreateTransferRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransfersResourceTest {

    @Mock
    private ApiClient apiClient;

    private TransfersResource resource;

    @BeforeEach
    void setUp() {
        resource = new TransfersResource(apiClient);
    }

    @Test
    void createCallsPost() {
        Transfer expected = new Transfer();
        expected.setId(1L);
        expected.setStatus("pending");
        expected.setAmount(50000L);
        when(apiClient.post(eq("/transfers"), any(), eq(Transfer.class))).thenReturn(expected);

        CreateTransferRequest req = new CreateTransferRequest();
        req.setAmount(50000L);
        req.setRecipientId(916L);
        Transfer result = resource.create(req);

        assertEquals(1L, result.getId());
        assertEquals("pending", result.getStatus());
        verify(apiClient).post(eq("/transfers"), eq(req), eq(Transfer.class));
    }

    @Test
    void getCallsCorrectPath() {
        Transfer expected = new Transfer();
        expected.setId(123456L);
        when(apiClient.get(eq("/transfers/123456"), isNull(), eq(Transfer.class))).thenReturn(expected);

        Transfer result = resource.get(123456L);

        assertEquals(123456L, result.getId());
        verify(apiClient).get(eq("/transfers/123456"), isNull(), eq(Transfer.class));
    }
}
