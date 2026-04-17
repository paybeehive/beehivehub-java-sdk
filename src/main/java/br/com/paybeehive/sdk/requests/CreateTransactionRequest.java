package br.com.paybeehive.sdk.requests;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateTransactionRequest {

    private Long amount;
    private String paymentMethod;
    private Integer installments;
    private String postbackUrl;

    private Map<String, Object> metadata;

    private Object customer;

    private Object shipping;

    private List<Object> items;

    private List<Object> splits;

    public CreateTransactionRequest() {}

    public Long getAmount() { return amount; }
    public void setAmount(Long amount) { this.amount = amount; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public Integer getInstallments() { return installments; }
    public void setInstallments(Integer installments) { this.installments = installments; }

    public String getPostbackUrl() { return postbackUrl; }
    public void setPostbackUrl(String postbackUrl) { this.postbackUrl = postbackUrl; }

    public Map<String, Object> getMetadata() { return metadata; }
    public void setMetadata(Map<String, Object> metadata) { this.metadata = metadata; }

    public Object getCustomer() { return customer; }
    public void setCustomer(Object customer) { this.customer = customer; }

    public Object getShipping() { return shipping; }
    public void setShipping(Object shipping) { this.shipping = shipping; }

    public List<Object> getItems() { return items; }
    public void setItems(List<Object> items) { this.items = items; }

    public List<Object> getSplits() { return splits; }
    public void setSplits(List<Object> splits) { this.splits = splits; }
}
