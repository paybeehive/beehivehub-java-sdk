package br.com.paybeehive.sdk.requests;

import br.com.paybeehive.sdk.models.Address;
import br.com.paybeehive.sdk.models.Document;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateCustomerRequest {

    private String name;
    private String email;
    private Document document;
    private String phone;
    private Address address;

    public CreateCustomerRequest() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Document getDocument() { return document; }
    public void setDocument(Document document) { this.document = document; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
}
