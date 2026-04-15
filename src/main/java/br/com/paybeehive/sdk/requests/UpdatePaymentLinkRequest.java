package br.com.paybeehive.sdk.requests;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdatePaymentLinkRequest {

    private String title;
    private Long amount;
    private String alias;
    private CreatePaymentLinkRequest.Settings settings;

    public UpdatePaymentLinkRequest() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Long getAmount() { return amount; }
    public void setAmount(Long amount) { this.amount = amount; }

    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }

    public CreatePaymentLinkRequest.Settings getSettings() { return settings; }
    public void setSettings(CreatePaymentLinkRequest.Settings settings) { this.settings = settings; }
}
