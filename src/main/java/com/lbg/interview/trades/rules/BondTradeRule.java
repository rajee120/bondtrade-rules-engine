package com.lbg.interview.trades.rules;

import com.lbg.interview.trades.domain.BondTrade;

public interface BondTradeRule {

    /**
     * Called to execute implementation of a rule.
     *
     * @param bondTrade Trade to evaluate;
     *
     * @return <Code>True</Code> if the rule passed, other wise false.
     */
    public boolean executeRule(BondTrade bondTrade);
}
