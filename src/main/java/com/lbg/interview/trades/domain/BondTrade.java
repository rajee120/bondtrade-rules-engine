package com.lbg.interview.trades.domain;


import java.time.*;

public class BondTrade {

    private String tradeId;
    private String instrumentId;
    private String instrumentDesc;
    private Long size;
    private double price;
    private LocalDateTime tradeDate;
    private LocalDate settlemetDate;

    public BondTrade(String tradeId, String instrumentId, String instrumentDesc, Long size, double price, LocalDateTime tradeDate, LocalDate settlemetDate) {

        // Initialise bond values
        this.tradeId = tradeId;
        this.instrumentId = instrumentId;
        this.instrumentDesc = instrumentDesc;
        this.size = size;
        this.price = price;
        this.tradeDate = tradeDate;
        this.settlemetDate = settlemetDate;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public String getInstrumentDesc() {
        return instrumentDesc;
    }

    public void setInstrumentDesc(String instrumentDesc) {
        this.instrumentDesc = instrumentDesc;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(LocalDateTime tradeDate) {
        this.tradeDate = tradeDate;
    }

    public LocalDate getSettlemetDate() {
        return settlemetDate;
    }

    public void setSettlemetDate(LocalDate settlemetDate) {
        this.settlemetDate = settlemetDate;
    }
}
