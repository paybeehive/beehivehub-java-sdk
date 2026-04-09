package br.com.paybeehive.sdk.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreatePaymentLinkRequest {

    private String alias;
    private Long amount;

    @JsonProperty("payment_methods")
    private List<String> paymentMethods;

    @JsonProperty("expires_at")
    private String expiresAt;

    @JsonProperty("collect_address")
    private Boolean collectAddress;

    @JsonProperty("collect_document")
    private Boolean collectDocument;

    @JsonProperty("max_installments")
    private Integer maxInstallments;

    public CreatePaymentLinkRequest() {}

    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }

    public Long getAmount() { return amount; }
    public void setAmount(Long amount) { this.amount = amount; }

    public List<String> getPaymentMethods() { return paymentMethods; }
    public void setPaymentMethods(List<String> paymentMethods) { this.paymentMethods = paymentMethods; }

    public String getExpiresAt() { return expiresAt; }
    public void setExpiresAt(String expiresAt) { this.expiresAt = expiresAt; }

    public Boolean getCollectAddress() { return collectAddress; }
    public void setCollectAddress(Boolean collectAddress) { this.collectAddress = collectAddress; }

    public Boolean getCollectDocument() { return collectDocument; }
    public void setCollectDocument(Boolean collectDocument) { this.collectDocument = collectDocument; }

    public Integer getMaxInstallments() { return maxInstallments; }
    public void setMaxInstallments(Integer maxInstallments) { this.maxInstallments = maxInstallments; }
}
