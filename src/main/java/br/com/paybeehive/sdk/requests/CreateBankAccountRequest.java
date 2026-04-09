package br.com.paybeehive.sdk.requests;

import br.com.paybeehive.sdk.models.Document;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateBankAccountRequest {

    @JsonProperty("bank_code")
    private String bankCode;

    private String agency;
    private String account;
    private String type;
    private Document document;

    public CreateBankAccountRequest() {}

    public String getBankCode() { return bankCode; }
    public void setBankCode(String bankCode) { this.bankCode = bankCode; }

    public String getAgency() { return agency; }
    public void setAgency(String agency) { this.agency = agency; }

    public String getAccount() { return account; }
    public void setAccount(String account) { this.account = account; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Document getDocument() { return document; }
    public void setDocument(Document document) { this.document = document; }
}
