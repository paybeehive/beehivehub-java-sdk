package br.com.paybeehive.sdk.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Document {

    private String type;
    private String number;

    public Document() {}

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
}
