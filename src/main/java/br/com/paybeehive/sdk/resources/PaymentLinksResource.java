package br.com.paybeehive.sdk.resources;

import br.com.paybeehive.sdk.ApiClient;
import br.com.paybeehive.sdk.BeehiveHubConstants;
import br.com.paybeehive.sdk.models.PaymentLink;
import br.com.paybeehive.sdk.requests.CreatePaymentLinkRequest;
import br.com.paybeehive.sdk.requests.UpdatePaymentLinkRequest;

import java.util.List;
import java.util.UUID;

public class PaymentLinksResource {

    private final ApiClient client;
    private final String environment;

    public PaymentLinksResource(ApiClient client, String environment) {
        this.client = client;
        this.environment = environment;
    }

    /**
     * Creates a new payment link. If no alias is set, one is auto-generated.
     *
     * @param data payment link creation data
     * @return the created payment link with URL
     */
    public PaymentLink create(CreatePaymentLinkRequest data) {
        if (data.getAlias() == null || data.getAlias().isBlank()) {
            data.setAlias(generateAlias());
        }
        PaymentLink link = client.post("/payment-links", data, PaymentLink.class);
        return withUrl(link);
    }

    /**
     * Lists all payment links.
     *
     * @return list of payment links
     */
    @SuppressWarnings("unchecked")
    public List<PaymentLink> list() {
        return (List<PaymentLink>) client.get("/payment-links", null, List.class);
    }

    /**
     * Retrieves a payment link by ID.
     *
     * @param id the payment link ID
     * @return the payment link with URL
     */
    public PaymentLink get(Long id) {
        PaymentLink link = client.get("/payment-links/" + id, null, PaymentLink.class);
        return withUrl(link);
    }

    /**
     * Updates a payment link. If alias is empty, one is auto-generated.
     *
     * @param id   the payment link ID
     * @param data update data
     * @return the updated payment link with URL
     */
    public PaymentLink update(Long id, UpdatePaymentLinkRequest data) {
        if (data.getAlias() == null || data.getAlias().isBlank()) {
            data.setAlias(generateAlias());
        }
        PaymentLink link = client.put("/payment-links/" + id, data, PaymentLink.class);
        return withUrl(link);
    }

    /**
     * Deletes a payment link.
     *
     * @param id the payment link ID
     */
    public void delete(Long id) {
        client.delete("/payment-links/" + id);
    }

    private PaymentLink withUrl(PaymentLink link) {
        if (link != null && link.getAlias() != null && !link.getAlias().isBlank()) {
            String baseUrl = "sandbox".equals(environment)
                    ? BeehiveHubConstants.SANDBOX_PAYMENT_LINK_URL
                    : BeehiveHubConstants.PRODUCTION_PAYMENT_LINK_URL;
            link.setUrl(baseUrl + "/" + link.getAlias());
        }
        return link;
    }

    private String generateAlias() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    }
}
