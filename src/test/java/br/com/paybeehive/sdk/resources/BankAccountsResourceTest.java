package br.com.paybeehive.sdk.resources;

import br.com.paybeehive.sdk.ApiClient;
import br.com.paybeehive.sdk.models.BankAccount;
import br.com.paybeehive.sdk.requests.CreateBankAccountRequest;
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
class BankAccountsResourceTest {

    @Mock
    private ApiClient apiClient;

    private BankAccountsResource resource;

    @BeforeEach
    void setUp() {
        resource = new BankAccountsResource(apiClient);
    }

    @Test
    void createCallsCorrectPath() {
        BankAccount expected = new BankAccount();
        expected.setId(1L);
        expected.setBankCode("341");
        when(apiClient.post(eq("/recipients/20/bank-accounts"), any(), eq(BankAccount.class))).thenReturn(expected);

        CreateBankAccountRequest req = new CreateBankAccountRequest();
        req.setBankCode("341");
        req.setAgency("0001");
        req.setAccount("12345-6");
        req.setType("checking");
        BankAccount result = resource.create(20L, req);

        assertEquals("341", result.getBankCode());
        verify(apiClient).post(eq("/recipients/20/bank-accounts"), eq(req), eq(BankAccount.class));
    }

    @Test
    void listCallsCorrectPath() {
        when(apiClient.get(eq("/recipients/20/bank-accounts"), isNull(), eq(List.class))).thenReturn(List.of());

        List<BankAccount> result = resource.list(20L);

        assertNotNull(result);
        verify(apiClient).get(eq("/recipients/20/bank-accounts"), isNull(), eq(List.class));
    }
}
