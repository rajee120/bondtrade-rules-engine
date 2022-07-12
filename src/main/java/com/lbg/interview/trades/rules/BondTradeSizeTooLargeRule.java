package com.lbg.interview.trades.rules;

import com.lbg.interview.trades.domain.BondTrade;

public class BondTradeSizeTooLargeRule implements BondTradeRule {

    private final int maxAllwedTradeSize;

    public BondTradeSizeTooLargeRule(int maxAllwedTradeSize) {
        this.maxAllwedTradeSize = maxAllwedTradeSize;
    }

    @Override
    public boolean executeRule(BondTrade bondTrade) {
        return bondTrade.getSize() <= maxAllwedTradeSize;
    }
}
