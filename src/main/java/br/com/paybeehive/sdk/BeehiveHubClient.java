package br.com.paybeehive.sdk;

import br.com.paybeehive.sdk.exceptions.BeehiveHubError;
import br.com.paybeehive.sdk.resources.*;

/**
 * Official Java SDK client for the BeehiveHub payment platform.
 *
 * <p>Usage:
 * <pre>{@code
 * BeehiveHubClient beehive = new BeehiveHubClient("your_secret_key");
 *
 * // Sandbox
 * BeehiveHubClient sandbox = new BeehiveHubClient("your_secret_key", "sandbox");
 * }</pre>
 */
public class BeehiveHubClient {

    public final TransactionsResource transactions;
    public final CustomersResource customers;
    public final BalanceResource balance;
    public final RecipientsResource recipients;
    public final BankAccountsResource bankAccounts;
    public final TransfersResource transfers;
    public final CompanyResource company;
    public final PaymentLinksResource paymentLinks;

    /**
     * Creates a production client.
     *
     * @param apiKey your BeehiveHub secret key
     */
    public BeehiveHubClient(String apiKey) {
        this(apiKey, "production");
    }

    /**
     * Creates a client for a specific environment.
     *
     * @param apiKey      your BeehiveHub secret key
     * @param environment "production" or "sandbox"
     */
    public BeehiveHubClient(String apiKey, String environment) {
        if (apiKey == null || apiKey.isBlank()) {
            throw new BeehiveHubError("API key is required. Provide your BeehiveHub secret key.");
        }

        ApiClient httpClient = new BeehiveHubHttpClient(apiKey, environment);

        this.transactions = new TransactionsResource(httpClient);
        this.customers = new CustomersResource(httpClient);
        this.balance = new BalanceResource(httpClient);
        this.recipients = new RecipientsResource(httpClient);
        this.bankAccounts = new BankAccountsResource(httpClient);
        this.transfers = new TransfersResource(httpClient);
        this.company = new CompanyResource(httpClient);
        this.paymentLinks = new PaymentLinksResource(httpClient, environment);
    }

    BeehiveHubClient(ApiClient apiClient, String environment) {
        this.transactions = new TransactionsResource(apiClient);
        this.customers = new CustomersResource(apiClient);
        this.balance = new BalanceResource(apiClient);
        this.recipients = new RecipientsResource(apiClient);
        this.bankAccounts = new BankAccountsResource(apiClient);
        this.transfers = new TransfersResource(apiClient);
        this.company = new CompanyResource(apiClient);
        this.paymentLinks = new PaymentLinksResource(apiClient, environment);
    }
}
