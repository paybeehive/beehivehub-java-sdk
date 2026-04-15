package br.com.paybeehive.sdk.resources;

import br.com.paybeehive.sdk.ApiClient;
import br.com.paybeehive.sdk.models.BankAccount;
import br.com.paybeehive.sdk.requests.CreateBankAccountRequest;

import java.util.List;

public class BankAccountsResource {

    private final ApiClient client;

    public BankAccountsResource(ApiClient client) {
        this.client = client;
    }

    /**
     * Creates a bank account for a recipient.
     *
     * @param recipientId the recipient ID
     * @param data        bank account data
     * @return the created bank account
     */
    public BankAccount create(Long recipientId, CreateBankAccountRequest data) {
        return client.post("/recipients/" + recipientId + "/bank-accounts", data, BankAccount.class);
    }

    /**
     * Lists all bank accounts for a recipient.
     *
     * @param recipientId the recipient ID
     * @return list of bank accounts
     */
    @SuppressWarnings("unchecked")
    public List<BankAccount> list(Long recipientId) {
        return (List<BankAccount>) client.get("/recipients/" + recipientId + "/bank-accounts", null, List.class);
    }
}
