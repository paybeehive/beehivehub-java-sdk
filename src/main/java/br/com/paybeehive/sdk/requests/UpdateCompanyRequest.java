package br.com.paybeehive.sdk.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateCompanyRequest {

    @JsonProperty("invoice_descriptor")
    private String invoiceDescriptor;

    @JsonProperty("average_revenue")
    private Long averageRevenue;

    @JsonProperty("average_ticket")
    private Long averageTicket;

    @JsonProperty("product_info")
    private String productInfo;

    private String website;
    private String email;
    private String phone;

    public UpdateCompanyRequest() {}

    public String getInvoiceDescriptor() { return invoiceDescriptor; }
    public void setInvoiceDescriptor(String invoiceDescriptor) { this.invoiceDescriptor = invoiceDescriptor; }

    public Long getAverageRevenue() { return averageRevenue; }
    public void setAverageRevenue(Long averageRevenue) { this.averageRevenue = averageRevenue; }

    public Long getAverageTicket() { return averageTicket; }
    public void setAverageTicket(Long averageTicket) { this.averageTicket = averageTicket; }

    public String getProductInfo() { return productInfo; }
    public void setProductInfo(String productInfo) { this.productInfo = productInfo; }

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
