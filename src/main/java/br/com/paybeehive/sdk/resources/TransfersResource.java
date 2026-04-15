package br.com.paybeehive.sdk.resources;

import br.com.paybeehive.sdk.ApiClient;
import br.com.paybeehive.sdk.models.Transfer;
import br.com.paybeehive.sdk.requests.CreateTransferRequest;

public class TransfersResource {

    private final ApiClient client;

    public TransfersResource(ApiClient client) {
        this.client = client;
    }

    /**
     * Creates a new transfer.
     *
     * @param data transfer creation data (amount and recipientId are required)
     * @return the created transfer
     */
    public Transfer create(CreateTransferRequest data) {
        return client.post("/transfers", data, Transfer.class);
    }

    /**
     * Retrieves a transfer by ID.
     *
     * @param id the transfer ID
     * @return the transfer
     */
    public Transfer get(Long id) {
        return client.get("/transfers/" + id, null, Transfer.class);
    }
}
