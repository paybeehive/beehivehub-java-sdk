package br.com.paybeehive.sdk.requests;

import br.com.paybeehive.sdk.models.Document;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateRecipientRequest {

    private String legalName;
    private Document document;
    private Object transferSettings;
    private Object bankAccount;

    public CreateRecipientRequest() {}

    public String getLegalName() { return legalName; }
    public void setLegalName(String legalName) { this.legalName = legalName; }

    public Document getDocument() { return document; }
    public void setDocument(Document document) { this.document = document; }

    public Object getTransferSettings() { return transferSettings; }
    public void setTransferSettings(Object transferSettings) { this.transferSettings = transferSettings; }

    public Object getBankAccount() { return bankAccount; }
    public void setBankAccount(Object bankAccount) { this.bankAccount = bankAccount; }
}
