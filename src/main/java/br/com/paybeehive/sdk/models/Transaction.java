package br.com.paybeehive.sdk.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {

    private Long id;
    private String status;
    private Long amount;

    @JsonProperty("payment_method")
    private String paymentMethod;

    private Customer customer;
    private List<Object> items;
    private Object fees;
    private List<Object> refunds;

    @JsonProperty("delivery_status")
    private Object deliveryStatus;

    @JsonProperty("postback_url")
    private String postbackUrl;

    private Map<String, String> metadata;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    public Transaction() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getAmount() { return amount; }
    public void setAmount(Long amount) { this.amount = amount; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public List<Object> getItems() { return items; }
    public void setItems(List<Object> items) { this.items = items; }

    public Object getFees() { return fees; }
    public void setFees(Object fees) { this.fees = fees; }

    public List<Object> getRefunds() { return refunds; }
    public void setRefunds(List<Object> refunds) { this.refunds = refunds; }

    public Object getDeliveryStatus() { return deliveryStatus; }
    public void setDeliveryStatus(Object deliveryStatus) { this.deliveryStatus = deliveryStatus; }

    public String getPostbackUrl() { return postbackUrl; }
    public void setPostbackUrl(String postbackUrl) { this.postbackUrl = postbackUrl; }

    public Map<String, String> getMetadata() { return metadata; }
    public void setMetadata(Map<String, String> metadata) { this.metadata = metadata; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
}
