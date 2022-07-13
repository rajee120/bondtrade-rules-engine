package com.lbg.interview.trades.rules;

import com.lbg.interview.trades.domain.BondTrade;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BondTradeSizeTooLargeRule implements BondTradeRule {

    private final long maxAllowedTradeSize;

    public BondTradeSizeTooLargeRule(@Value("${max.allowed.trade.size:3000000}") long maxAllowedTradeSize) {
        this.maxAllowedTradeSize = maxAllowedTradeSize;
    }

    @Override
    public boolean executeRule(BondTrade bondTrade) {
        return bondTrade.getSize() <= maxAllowedTradeSize;
    }

    @Override
    public String getRuleName() {
        return "TradeSizeLargeRule";
    }
}
