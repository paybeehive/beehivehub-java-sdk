package br.com.paybeehive.sdk.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateCompanyRequest {

    @JsonProperty("invoice_descriptor")
    private String invoiceDescriptor;

    private Details details;

    public UpdateCompanyRequest() {}

    public String getInvoiceDescriptor() { return invoiceDescriptor; }
    public void setInvoiceDescriptor(String invoiceDescriptor) { this.invoiceDescriptor = invoiceDescriptor; }

    public Details getDetails() { return details; }
    public void setDetails(Details details) { this.details = details; }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Details {

        @JsonProperty("average_revenue")
        private Long averageRevenue;

        @JsonProperty("average_ticket")
        private Long averageTicket;

        @JsonProperty("physical_products")
        private Boolean physicalProducts;

        @JsonProperty("products_description")
        private String productsDescription;

        @JsonProperty("site_url")
        private String siteUrl;

        private String phone;

        private String email;

        public Details() {}

        public Long getAverageRevenue() { return averageRevenue; }
        public void setAverageRevenue(Long averageRevenue) { this.averageRevenue = averageRevenue; }

        public Long getAverageTicket() { return averageTicket; }
        public void setAverageTicket(Long averageTicket) { this.averageTicket = averageTicket; }

        public Boolean getPhysicalProducts() { return physicalProducts; }
        public void setPhysicalProducts(Boolean physicalProducts) { this.physicalProducts = physicalProducts; }

        public String getProductsDescription() { return productsDescription; }
        public void setProductsDescription(String productsDescription) { this.productsDescription = productsDescription; }

        public String getSiteUrl() { return siteUrl; }
        public void setSiteUrl(String siteUrl) { this.siteUrl = siteUrl; }

        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }
}
