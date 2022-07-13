package com.lbg.interview.trades.rules;

import com.lbg.interview.trades.domain.BondTrade;
import com.lbg.interview.trades.util.LoadTradesUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BondTradeRuleEngineTest {

    @Mock
    private LoadTradesUtil loadTradesUtil;

    @InjectMocks
    private BondTradeRulesEngine rulesEngine;

    BondTrade bondTrade, bondTrade1, bondTrade2;

    @Before
    public void setUp() throws Exception {

        Set<BondTradeRule> bondTradeRules = newHashSet(new BondTradedTodayRule(), new BondTradeSizeTooLargeRule(300000));
        ReflectionTestUtils.setField(rulesEngine, "fileName", "./src/test/resources/trades/bond-trades-dev.csv");
        ReflectionTestUtils.setField(rulesEngine, "bondTradeRules", bondTradeRules);
    }

    @Test
    public void testRulesEngine() {

        when(loadTradesUtil.loadTradesFromFile(anyString())).thenReturn(getBondTrades());
        Map<BondTrade, Boolean> results = rulesEngine.runAllTradeRules();
        assertEquals(3, results.size());
        assertEquals(true, results.get(bondTrade));
        assertEquals(false, results.get(bondTrade1));
        assertEquals(false, results.get(bondTrade2));
    }

    @Test
    public void testRulesEngineWhenFileNamePassedIsInvalid() {

        ReflectionTestUtils.setField(rulesEngine, "fileName", "");
        when(loadTradesUtil.loadTradesFromFile(anyString())).thenThrow(new IllegalArgumentException("Invalid File Name"));
        Map<BondTrade, Boolean> results = rulesEngine.runAllTradeRules();
        assertEquals(0, results.size());
    }


    private List<BondTrade> getBondTrades(){
        List<BondTrade> bondTrades = newArrayList();

        bondTrade = new BondTrade.Builder().withTradeId("trade1").withInstrumentId("ins").withInstrumentDesc("desc").withSize(200000l).withPrice(101.3).withTradeDate(LocalDateTime.now()).withSettlementDate(LocalDate.now().plusDays(3)).build();
        bondTrades.add(bondTrade);

        bondTrade1 = new BondTrade.Builder().withTradeId("trade2").withInstrumentId("ins").withInstrumentDesc("desc").withSize(200000l).withPrice(101.3).withTradeDate(LocalDateTime.now().minusDays(10)).withSettlementDate(LocalDate.now().minusDays(7)).build();
        bondTrades.add(bondTrade1);

        bondTrade2 = new BondTrade.Builder().withTradeId("trade3").withInstrumentId("ins").withInstrumentDesc("desc").withSize(2000000l).withPrice(101.3).withTradeDate(LocalDateTime.now()).withSettlementDate(LocalDate.now().plusDays(3)).build();
        bondTrades.add(bondTrade2);

        return bondTrades;
    }
}