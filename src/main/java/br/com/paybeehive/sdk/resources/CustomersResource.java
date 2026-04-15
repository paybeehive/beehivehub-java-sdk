package br.com.paybeehive.sdk.resources;

import br.com.paybeehive.sdk.ApiClient;
import br.com.paybeehive.sdk.models.Customer;
import br.com.paybeehive.sdk.requests.CreateCustomerRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomersResource {

    private final ApiClient client;

    public CustomersResource(ApiClient client) {
        this.client = client;
    }

    /**
     * Creates a new customer.
     *
     * @param data customer creation data
     * @return the created customer
     */
    public Customer create(CreateCustomerRequest data) {
        return client.post("/customers", data, Customer.class);
    }

    /**
     * Lists customers by email. The email parameter is mandatory.
     *
     * @param email the customer's email address
     * @return list of matching customers
     */
    @SuppressWarnings("unchecked")
    public List<Customer> list(String email) {
        Map<String, String> query = new HashMap<>();
        query.put("email", email);
        return (List<Customer>) client.get("/customers", query, List.class);
    }

    /**
     * Retrieves a customer by ID.
     *
     * @param id the customer ID
     * @return the customer
     */
    public Customer get(Long id) {
        return client.get("/customers/" + id, null, Customer.class);
    }
}
