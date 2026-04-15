package br.com.paybeehive.sdk.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BankAccount {

    private Long id;

    @JsonProperty("bank_code")
    private String bankCode;

    @JsonProperty("agency_number")
    private String agencyNumber;

    @JsonProperty("agency_digit")
    private String agencyDigit;

    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("account_digit")
    private String accountDigit;

    private String type;

    @JsonProperty("legal_name")
    private String legalName;

    @JsonProperty("document_number")
    private String documentNumber;

    @JsonProperty("document_type")
    private String documentType;

    @JsonProperty("is_active")
    private Boolean isActive;

    @JsonProperty("is_visible")
    private Boolean isVisible;

    @JsonProperty("created_at")
    private String createdAt;

    public BankAccount() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBankCode() { return bankCode; }
    public void setBankCode(String bankCode) { this.bankCode = bankCode; }

    public String getAgencyNumber() { return agencyNumber; }
    public void setAgencyNumber(String agencyNumber) { this.agencyNumber = agencyNumber; }

    public String getAgencyDigit() { return agencyDigit; }
    public void setAgencyDigit(String agencyDigit) { this.agencyDigit = agencyDigit; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getAccountDigit() { return accountDigit; }
    public void setAccountDigit(String accountDigit) { this.accountDigit = accountDigit; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getLegalName() { return legalName; }
    public void setLegalName(String legalName) { this.legalName = legalName; }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public String getDocumentType() { return documentType; }
    public void setDocumentType(String documentType) { this.documentType = documentType; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    public Boolean getIsVisible() { return isVisible; }
    public void setIsVisible(Boolean isVisible) { this.isVisible = isVisible; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
