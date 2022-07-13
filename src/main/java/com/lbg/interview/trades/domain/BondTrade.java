package com.lbg.interview.trades.domain;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class BondTrade {

    private String tradeId;
    private String instrumentId;
    private String instrumentDesc;
    private long size;
    private double price;
    private LocalDateTime tradeDate;
    private LocalDate settlementDate;

    private BondTrade(Builder builder) {

        // Initialise bond values
        this.tradeId = builder.tradeId;
        this.instrumentId = builder.instrumentId;
        this.instrumentDesc = builder.instrumentDesc;
        this.size = builder.size;
        this.price = builder.price;
        this.tradeDate = builder.tradeDate;
        this.settlementDate = builder.settlementDate;
    }

    public String getTradeId() {
        return tradeId;
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public String getInstrumentDesc() {
        return instrumentDesc;
    }

    public long getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getTradeDate() {
        return tradeDate;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BondTrade trade = (BondTrade) o;

        if (size != trade.size) return false;
        if (Double.compare(trade.price, price) != 0) return false;
        if (!tradeId.equals(trade.tradeId)) return false;
        if (!instrumentId.equals(trade.instrumentId)) return false;
        if (!instrumentDesc.equals(trade.instrumentDesc)) return false;
        if (!tradeDate.equals(trade.tradeDate)) return false;
        return settlementDate.equals(trade.settlementDate);
    }

    @Override
    public int hashCode() {
        int result = tradeId.hashCode();
        long temp;
        result = 31 * result + instrumentId.hashCode();
        result = 31 * result + instrumentDesc.hashCode();
        result = 31 * result + (int) (size ^ (size >>> 32));
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + tradeDate.hashCode();
        result = 31 * result + settlementDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BondTrade{" +
                "tradeId='" + tradeId + '\'' +
                ", instrumentId='" + instrumentId + '\'' +
                ", instrumentDesc='" + instrumentDesc + '\'' +
                ", size=" + size +
                ", price=" + price +
                ", tradeDate=" + tradeDate +
                ", settlementDate=" + settlementDate +
                '}';
    }

    public static class Builder {

        private String tradeId;
        private String instrumentId;
        private String instrumentDesc;
        private long size;
        private double price;
        private LocalDateTime tradeDate;
        private LocalDate settlementDate;

        public Builder withTradeId(String tradeId){
            this.tradeId = tradeId;
            return this;
        }

        public Builder withInstrumentId(String instrumentId){
            this.instrumentId = instrumentId;
            return this;
        }

        public Builder withInstrumentDesc(String instrumentDesc){
            this.instrumentDesc = instrumentDesc;
            return this;
        }

        public Builder withSize(long size){
            this.size = size;
            return this;
        }

        public Builder withPrice(double price){
            this.price = price;
            return this;
        }

        public Builder withTradeDate(LocalDateTime tradeDate){
            this.tradeDate = tradeDate;
            return this;
        }

        public Builder withSettlementDate(LocalDate settlementDate){
            this.settlementDate = settlementDate;
            return this;
        }

        public BondTrade build(){
            return new BondTrade(this);
        }
    }
}
