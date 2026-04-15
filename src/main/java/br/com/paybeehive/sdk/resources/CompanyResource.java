package br.com.paybeehive.sdk.resources;

import br.com.paybeehive.sdk.ApiClient;
import br.com.paybeehive.sdk.models.Company;
import br.com.paybeehive.sdk.requests.UpdateCompanyRequest;

public class CompanyResource {

    private final ApiClient client;

    public CompanyResource(ApiClient client) {
        this.client = client;
    }

    /**
     * Retrieves company information.
     *
     * @return the company data
     */
    public Company get() {
        return client.get("/company", null, Company.class);
    }

    /**
     * Updates company information.
     *
     * @param data update data
     * @return the updated company
     */
    public Company update(UpdateCompanyRequest data) {
        return client.put("/company", data, Company.class);
    }
}
