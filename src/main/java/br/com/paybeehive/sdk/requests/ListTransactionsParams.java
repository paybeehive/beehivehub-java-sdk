package br.com.paybeehive.sdk.requests;

public class ListTransactionsParams {

    private Integer limit;
    private Integer offset;
    private String startDate;
    private String endDate;

    public ListTransactionsParams() {}

    public Integer getLimit() { return limit; }
    public void setLimit(Integer limit) { this.limit = limit; }

    public Integer getOffset() { return offset; }
    public void setOffset(Integer offset) { this.offset = offset; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
}
