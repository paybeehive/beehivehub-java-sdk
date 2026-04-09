package br.com.paybeehive.sdk.resources;

import br.com.paybeehive.sdk.ApiClient;
import br.com.paybeehive.sdk.models.Recipient;
import br.com.paybeehive.sdk.requests.CreateRecipientRequest;
import br.com.paybeehive.sdk.requests.UpdateRecipientRequest;

import java.util.List;

public class RecipientsResource {

    private final ApiClient client;

    public RecipientsResource(ApiClient client) {
        this.client = client;
    }

    /**
     * Creates a new recipient.
     *
     * @param data recipient creation data
     * @return the created recipient
     */
    public Recipient create(CreateRecipientRequest data) {
        return client.post("/recipients", data, Recipient.class);
    }

    /**
     * Lists all recipients.
     *
     * @return list of recipients
     */
    @SuppressWarnings("unchecked")
    public List<Recipient> list() {
        return (List<Recipient>) client.get("/recipients", null, List.class);
    }

    /**
     * Retrieves a recipient by ID.
     *
     * @param id the recipient ID
     * @return the recipient
     */
    public Recipient get(Long id) {
        return client.get("/recipients/" + id, null, Recipient.class);
    }

    /**
     * Updates a recipient.
     *
     * @param id   the recipient ID
     * @param data update data
     * @return the updated recipient
     */
    public Recipient update(Long id, UpdateRecipientRequest data) {
        return client.put("/recipients/" + id, data, Recipient.class);
    }
}
