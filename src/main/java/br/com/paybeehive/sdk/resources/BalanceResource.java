package br.com.paybeehive.sdk.resources;

import br.com.paybeehive.sdk.ApiClient;
import br.com.paybeehive.sdk.models.Balance;

public class BalanceResource {

    private final ApiClient client;

    public BalanceResource(ApiClient client) {
        this.client = client;
    }

    /**
     * Retrieves the available account balance.
     *
     * @return the current balance
     */
    public Balance get() {
        return client.get("/balance/available", null, Balance.class);
    }
}
