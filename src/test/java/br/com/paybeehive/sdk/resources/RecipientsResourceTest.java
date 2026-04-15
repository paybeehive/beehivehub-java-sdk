package br.com.paybeehive.sdk.resources;

import br.com.paybeehive.sdk.ApiClient;
import br.com.paybeehive.sdk.models.Recipient;
import br.com.paybeehive.sdk.requests.CreateRecipientRequest;
import br.com.paybeehive.sdk.requests.UpdateRecipientRequest;
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
class RecipientsResourceTest {

    @Mock
    private ApiClient apiClient;

    private RecipientsResource resource;

    @BeforeEach
    void setUp() {
        resource = new RecipientsResource(apiClient);
    }

    @Test
    void createCallsPost() {
        Recipient expected = new Recipient();
        expected.setId(5L);
        when(apiClient.post(eq("/recipients"), any(), eq(Recipient.class))).thenReturn(expected);

        CreateRecipientRequest req = new CreateRecipientRequest();
        req.setLegalName("Company LTDA");
        Recipient result = resource.create(req);

        assertEquals(5L, result.getId());
        verify(apiClient).post(eq("/recipients"), eq(req), eq(Recipient.class));
    }

    @Test
    void listCallsGetWithNoQueryParams() {
        when(apiClient.get(eq("/recipients"), isNull(), eq(List.class))).thenReturn(List.of());

        List<Recipient> result = resource.list();

        assertNotNull(result);
        verify(apiClient).get(eq("/recipients"), isNull(), eq(List.class));
    }

    @Test
    void getCallsCorrectPath() {
        Recipient expected = new Recipient();
        expected.setId(10L);
        when(apiClient.get(eq("/recipients/10"), isNull(), eq(Recipient.class))).thenReturn(expected);

        Recipient result = resource.get(10L);

        assertEquals(10L, result.getId());
    }

    @Test
    void updateCallsPut() {
        Recipient expected = new Recipient();
        expected.setLegalName("Updated Co");
        when(apiClient.put(eq("/recipients/3"), any(), eq(Recipient.class))).thenReturn(expected);

        UpdateRecipientRequest req = new UpdateRecipientRequest();
        req.setLegalName("Updated Co");
        Recipient result = resource.update(3L, req);

        assertEquals("Updated Co", result.getLegalName());
        verify(apiClient).put(eq("/recipients/3"), eq(req), eq(Recipient.class));
    }
}
