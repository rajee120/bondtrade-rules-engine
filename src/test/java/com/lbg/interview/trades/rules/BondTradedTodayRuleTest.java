package com.lbg.interview.trades.rules;

import com.lbg.interview.trades.domain.BondTrade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BondTradedTodayRuleTest {

    @InjectMocks
    BondTradedTodayRule bondTradedTodayRule;

    @Test
    public void executeRuleWhenBondTradeIsFromToday() {

        BondTrade bondTrade = mock(BondTrade.class);
        when(bondTrade.getTradeDate()).thenReturn(LocalDateTime.now());
        boolean result = bondTradedTodayRule.executeRule(bondTrade);
        assertTrue(result);
    }

    @Test
    public void executeRuleWhenBondTradeIsNotFromToday() {

        BondTrade bondTrade = mock(BondTrade.class);
        when(bondTrade.getTradeDate()).thenReturn(LocalDateTime.now().minusDays(10));
        assertFalse(bondTradedTodayRule.executeRule(bondTrade));
    }

    @Test
    public void getRuleName() {
        assertEquals("TradedTodayRule", bondTradedTodayRule.getRuleName());
    }
}