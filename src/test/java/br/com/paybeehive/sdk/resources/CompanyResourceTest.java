package br.com.paybeehive.sdk.resources;

import br.com.paybeehive.sdk.ApiClient;
import br.com.paybeehive.sdk.models.Company;
import br.com.paybeehive.sdk.requests.UpdateCompanyRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyResourceTest {

    @Mock
    private ApiClient apiClient;

    private CompanyResource resource;

    @BeforeEach
    void setUp() {
        resource = new CompanyResource(apiClient);
    }

    @Test
    void getCallsCorrectEndpoint() {
        Company expected = new Company();
        expected.setId(1L);
        expected.setInvoiceDescriptor("BEEHIVE*STORE");
        when(apiClient.get(eq("/company"), isNull(), eq(Company.class))).thenReturn(expected);

        Company result = resource.get();

        assertEquals("BEEHIVE*STORE", result.getInvoiceDescriptor());
        verify(apiClient).get(eq("/company"), isNull(), eq(Company.class));
    }

    @Test
    void updateCallsPut() {
        Company expected = new Company();
        expected.setInvoiceDescriptor("NEW*DESC");
        when(apiClient.put(eq("/company"), any(), eq(Company.class))).thenReturn(expected);

        UpdateCompanyRequest req = new UpdateCompanyRequest();
        req.setInvoiceDescriptor("NEW*DESC");
        Company result = resource.update(req);

        assertEquals("NEW*DESC", result.getInvoiceDescriptor());
        verify(apiClient).put(eq("/company"), eq(req), eq(Company.class));
    }
}
