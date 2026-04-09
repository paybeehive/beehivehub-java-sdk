package br.com.paybeehive.sdk.resources;

import br.com.paybeehive.sdk.ApiClient;
import br.com.paybeehive.sdk.models.Customer;
import br.com.paybeehive.sdk.requests.CreateCustomerRequest;
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
class CustomersResourceTest {

    @Mock
    private ApiClient apiClient;

    private CustomersResource resource;

    @BeforeEach
    void setUp() {
        resource = new CustomersResource(apiClient);
    }

    @Test
    void createCallsPost() {
        Customer expected = new Customer();
        expected.setId(1L);
        expected.setEmail("user@example.com");
        when(apiClient.post(eq("/customers"), any(), eq(Customer.class))).thenReturn(expected);

        CreateCustomerRequest req = new CreateCustomerRequest();
        req.setEmail("user@example.com");
        Customer result = resource.create(req);

        assertEquals(1L, result.getId());
        verify(apiClient).post(eq("/customers"), eq(req), eq(Customer.class));
    }

    @Test
    void listSendsEmailQueryParam() {
        when(apiClient.get(eq("/customers"), any(), eq(List.class))).thenReturn(List.of());

        resource.list("test@example.com");

        verify(apiClient).get(eq("/customers"), argThat(map -> {
            Map<?, ?> m = (Map<?, ?>) map;
            return "test@example.com".equals(m.get("email"));
        }), eq(List.class));
    }

    @Test
    void getCallsCorrectPath() {
        Customer expected = new Customer();
        expected.setId(99L);
        when(apiClient.get(eq("/customers/99"), isNull(), eq(Customer.class))).thenReturn(expected);

        Customer result = resource.get(99L);

        assertEquals(99L, result.getId());
    }
}
