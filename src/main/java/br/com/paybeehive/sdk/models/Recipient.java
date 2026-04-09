package br.com.paybeehive.sdk.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipient {

    private Long id;

    @JsonProperty("legal_name")
    private String legalName;

    private Document document;

    @JsonProperty("transfer_settings")
    private Object transferSettings;

    @JsonProperty("bank_account")
    private BankAccount bankAccount;

    private Object balance;
    private String status;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    public Recipient() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLegalName() { return legalName; }
    public void setLegalName(String legalName) { this.legalName = legalName; }

    public Document getDocument() { return document; }
    public void setDocument(Document document) { this.document = document; }

    public Object getTransferSettings() { return transferSettings; }
    public void setTransferSettings(Object transferSettings) { this.transferSettings = transferSettings; }

    public BankAccount getBankAccount() { return bankAccount; }
    public void setBankAccount(BankAccount bankAccount) { this.bankAccount = bankAccount; }

    public Object getBalance() { return balance; }
    public void setBalance(Object balance) { this.balance = balance; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
}
