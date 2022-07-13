package com.lbg.interview.trades;

import com.lbg.interview.trades.domain.BondTrade;
import com.lbg.interview.trades.rules.BondTradeRulesEngine;
import com.lbg.interview.trades.rules.BondTradeSizeTooLargeRule;
import com.lbg.interview.trades.rules.BondTradedTodayRule;
import com.lbg.interview.trades.util.LoadTradesUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BondTradeRulesEngine.class, LoadTradesUtil.class, BondTradedTodayRule.class, BondTradeSizeTooLargeRule.class})
public class BondTradeRulesEngineTest {

    @Autowired
    private BondTradeRulesEngine rulesEngine;

    @Test
    public void testRulesEngine() {

        Map<BondTrade, Boolean> results = rulesEngine.runAllTradeRules();
        assertEquals(5, results.size());
    }

}