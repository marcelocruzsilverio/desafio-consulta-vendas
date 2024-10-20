package com.devsuperior.dsmeta.dto;

public class SaleReportDTO {

    private Long id;
    private String date;  
    private Double amount;
    private String sellerName;

    public SaleReportDTO(Long id, String date, Double amount, String sellerName) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.sellerName = sellerName;
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getSellerName() {
        return sellerName;
    }
}
