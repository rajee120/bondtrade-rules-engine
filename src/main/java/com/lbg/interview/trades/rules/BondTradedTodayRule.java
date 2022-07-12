package com.lbg.interview.trades.rules;

import com.lbg.interview.trades.domain.BondTrade;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BondTradedTodayRule implements BondTradeRule {

    private static final LocalDate TODAY = LocalDate.now();

    @Override
    public boolean executeRule(BondTrade bondTrade) {
        return bondTrade.getTradeDate().toLocalDate().isEqual(TODAY);
    }
}
