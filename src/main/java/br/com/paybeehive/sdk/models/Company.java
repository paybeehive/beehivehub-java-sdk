package br.com.paybeehive.sdk.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Company {

    private Long id;

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

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    public Company() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
}
