package br.com.paybeehive.sdk.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreatePaymentLinkRequest {

    private String title;
    private Long amount;
    private String alias;
    private Settings settings;

    public CreatePaymentLinkRequest() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Long getAmount() { return amount; }
    public void setAmount(Long amount) { this.amount = amount; }

    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }

    public Settings getSettings() { return settings; }
    public void setSettings(Settings settings) { this.settings = settings; }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Settings {

        @JsonProperty("default_payment_method")
        private String defaultPaymentMethod;

        @JsonProperty("request_address")
        private Boolean requestAddress;

        @JsonProperty("request_phone")
        private Boolean requestPhone;

        @JsonProperty("request_document")
        private Boolean requestDocument;

        private Boolean traceable;

        private CardSettings card;
        private PixSettings pix;
        private BoletoSettings boleto;

        public Settings() {}

        public String getDefaultPaymentMethod() { return defaultPaymentMethod; }
        public void setDefaultPaymentMethod(String defaultPaymentMethod) { this.defaultPaymentMethod = defaultPaymentMethod; }

        public Boolean getRequestAddress() { return requestAddress; }
        public void setRequestAddress(Boolean requestAddress) { this.requestAddress = requestAddress; }

        public Boolean getRequestPhone() { return requestPhone; }
        public void setRequestPhone(Boolean requestPhone) { this.requestPhone = requestPhone; }

        public Boolean getRequestDocument() { return requestDocument; }
        public void setRequestDocument(Boolean requestDocument) { this.requestDocument = requestDocument; }

        public Boolean getTraceable() { return traceable; }
        public void setTraceable(Boolean traceable) { this.traceable = traceable; }

        public CardSettings getCard() { return card; }
        public void setCard(CardSettings card) { this.card = card; }

        public PixSettings getPix() { return pix; }
        public void setPix(PixSettings pix) { this.pix = pix; }

        public BoletoSettings getBoleto() { return boleto; }
        public void setBoleto(BoletoSettings boleto) { this.boleto = boleto; }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class CardSettings {
            private Boolean enabled;
            @JsonProperty("free_installments")
            private Integer freeInstallments;
            @JsonProperty("max_installments")
            private Integer maxInstallments;

            public CardSettings() {}
            public Boolean getEnabled() { return enabled; }
            public void setEnabled(Boolean enabled) { this.enabled = enabled; }
            public Integer getFreeInstallments() { return freeInstallments; }
            public void setFreeInstallments(Integer freeInstallments) { this.freeInstallments = freeInstallments; }
            public Integer getMaxInstallments() { return maxInstallments; }
            public void setMaxInstallments(Integer maxInstallments) { this.maxInstallments = maxInstallments; }
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class PixSettings {
            private Boolean enabled;
            @JsonProperty("expires_in_days")
            private Integer expiresInDays;

            public PixSettings() {}
            public Boolean getEnabled() { return enabled; }
            public void setEnabled(Boolean enabled) { this.enabled = enabled; }
            public Integer getExpiresInDays() { return expiresInDays; }
            public void setExpiresInDays(Integer expiresInDays) { this.expiresInDays = expiresInDays; }
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class BoletoSettings {
            private Boolean enabled;
            @JsonProperty("expires_in_days")
            private Integer expiresInDays;

            public BoletoSettings() {}
            public Boolean getEnabled() { return enabled; }
            public void setEnabled(Boolean enabled) { this.enabled = enabled; }
            public Integer getExpiresInDays() { return expiresInDays; }
            public void setExpiresInDays(Integer expiresInDays) { this.expiresInDays = expiresInDays; }
        }
    }
}
