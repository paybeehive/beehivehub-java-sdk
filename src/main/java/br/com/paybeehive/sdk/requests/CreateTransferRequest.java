package br.com.paybeehive.sdk.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateTransferRequest {

    private Long amount;

    @JsonProperty("recipient_id")
    private Long recipientId;

    @JsonProperty("bank_account")
    private BankAccount bankAccount;

    public CreateTransferRequest() {}

    public Long getAmount() { return amount; }
    public void setAmount(Long amount) { this.amount = amount; }

    public Long getRecipientId() { return recipientId; }
    public void setRecipientId(Long recipientId) { this.recipientId = recipientId; }

    public BankAccount getBankAccount() { return bankAccount; }
    public void setBankAccount(BankAccount bankAccount) { this.bankAccount = bankAccount; }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class BankAccount {

        @JsonProperty("bank_code")
        private String bankCode;

        @JsonProperty("agency_number")
        private String agencyNumber;

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

        public BankAccount() {}

        public String getBankCode() { return bankCode; }
        public void setBankCode(String bankCode) { this.bankCode = bankCode; }

        public String getAgencyNumber() { return agencyNumber; }
        public void setAgencyNumber(String agencyNumber) { this.agencyNumber = agencyNumber; }

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
    }
}
