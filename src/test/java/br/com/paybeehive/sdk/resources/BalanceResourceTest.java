package br.com.paybeehive.sdk.resources;

import br.com.paybeehive.sdk.ApiClient;
import br.com.paybeehive.sdk.models.Balance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BalanceResourceTest {

    @Mock
    private ApiClient apiClient;

    private BalanceResource resource;

    @BeforeEach
    void setUp() {
        resource = new BalanceResource(apiClient);
    }

    @Test
    void getCallsCorrectEndpoint() {
        Balance expected = new Balance();
        expected.setAmount(100000L);
        expected.setRecipientId(42L);
        when(apiClient.get(eq("/balance/available"), isNull(), eq(Balance.class))).thenReturn(expected);

        Balance result = resource.get();

        assertEquals(100000L, result.getAmount());
        assertEquals(42L, result.getRecipientId());
        verify(apiClient).get(eq("/balance/available"), isNull(), eq(Balance.class));
    }
}
