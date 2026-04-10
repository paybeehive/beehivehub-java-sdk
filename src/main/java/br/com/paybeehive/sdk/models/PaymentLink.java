package br.com.paybeehive.sdk.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentLink {

    private Long id;
    private String title;
    private String alias;
    private String url;
    private Long amount;
    private String status;

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

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    public PaymentLink() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public Long getAmount() { return amount; }
    public void setAmount(Long amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

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

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
}
