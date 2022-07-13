package com.lbg.interview.trades.rules;

import com.lbg.interview.trades.domain.BondTrade;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BondTradedTodayRule implements BondTradeRule {

    @Override
    public boolean executeRule(BondTrade bondTrade) {
        return bondTrade.getTradeDate().toLocalDate().isEqual(LocalDate.now());
    }

    @Override
    public String getRuleName() {
        return "TradedTodayRule";
    }
}
