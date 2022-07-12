package com.lbg.interview.trades.rules;

import com.lbg.interview.trades.RulesEngineApp;
import com.lbg.interview.trades.domain.BondTrade;
import com.lbg.interview.trades.util.LoadTradesUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BondTradeRulesEngine.class, LoadTradesUtil.class})
public class BondTradeRulesEngineTest {

    @Autowired
    private BondTradeRulesEngine rulesEngine;

    @Test
    public void testRulesEngine() {

        Map<BondTrade, Boolean> results = rulesEngine.runAllTradeRules();

        assertNotNull(results);
    }

}