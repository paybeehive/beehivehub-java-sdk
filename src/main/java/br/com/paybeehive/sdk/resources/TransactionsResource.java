package br.com.paybeehive.sdk.resources;

import br.com.paybeehive.sdk.ApiClient;
import br.com.paybeehive.sdk.models.Transaction;
import br.com.paybeehive.sdk.requests.CreateTransactionRequest;
import br.com.paybeehive.sdk.requests.ListTransactionsParams;
import br.com.paybeehive.sdk.requests.UpdateDeliveryStatusRequest;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionsResource {

    private final ApiClient client;

    public TransactionsResource(ApiClient client) {
        this.client = client;
    }

    /**
     * Creates a new transaction.
     *
     * @param data transaction creation data
     * @return the created transaction
     */
    public Transaction create(CreateTransactionRequest data) {
        return client.post("/transactions", data, Transaction.class);
    }

    /**
     * Lists transactions with optional filters.
     *
     * @param params optional filter parameters (limit, offset, startDate, endDate)
     * @return list of transactions
     */
    @SuppressWarnings("unchecked")
    public List<Transaction> list(ListTransactionsParams params) {
        Map<String, String> query = new HashMap<>();
        if (params != null) {
            if (params.getLimit() != null) query.put("limit", String.valueOf(params.getLimit()));
            if (params.getOffset() != null) query.put("offset", String.valueOf(params.getOffset()));
            if (params.getStartDate() != null) query.put("start_date", params.getStartDate());
            if (params.getEndDate() != null) query.put("end_date", params.getEndDate());
        }
        return (List<Transaction>) client.get("/transactions", query, List.class);
    }

    /**
     * Retrieves a transaction by ID.
     *
     * @param id the transaction ID
     * @return the transaction
     */
    public Transaction get(Long id) {
        return client.get("/transactions/" + id, null, Transaction.class);
    }

    /**
     * Refunds a transaction. If amount is null, a full refund is performed.
     *
     * @param id     the transaction ID
     * @param amount optional partial refund amount in cents
     * @return the refunded transaction
     */
    public Transaction refund(Long id, Long amount) {
        Map<String, Object> body = new HashMap<>();
        if (amount != null) body.put("amount", amount);
        return client.post("/transactions/" + id + "/refund", body.isEmpty() ? null : body, Transaction.class);
    }

    /**
     * Updates the delivery status of a transaction.
     *
     * @param id   the transaction ID
     * @param data delivery status update data
     * @return the updated transaction
     */
    public Transaction updateDelivery(Long id, UpdateDeliveryStatusRequest data) {
        return client.put("/transactions/" + id + "/delivery", data, Transaction.class);
    }
}
