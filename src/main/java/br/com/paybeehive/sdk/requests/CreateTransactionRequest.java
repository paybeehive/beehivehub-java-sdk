package br.com.paybeehive.sdk.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateTransactionRequest {

    @JsonProperty("customer_id")
    private Long customerId;

    private List<Object> items;

    @JsonProperty("payment_method")
    private String paymentMethod;

    @JsonProperty("webhook_url")
    private String webhookUrl;

    private Map<String, String> metadata;
    private Object customer;
    private Object card;

    public CreateTransactionRequest() {}

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public List<Object> getItems() { return items; }
    public void setItems(List<Object> items) { this.items = items; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getWebhookUrl() { return webhookUrl; }
    public void setWebhookUrl(String webhookUrl) { this.webhookUrl = webhookUrl; }

    public Map<String, String> getMetadata() { return metadata; }
    public void setMetadata(Map<String, String> metadata) { this.metadata = metadata; }

    public Object getCustomer() { return customer; }
    public void setCustomer(Object customer) { this.customer = customer; }

    public Object getCard() { return card; }
    public void setCard(Object card) { this.card = card; }
}
