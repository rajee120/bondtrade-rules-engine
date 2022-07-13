package com.lbg.interview.trades.rules;

import com.lbg.interview.trades.domain.BondTrade;
import org.apache.el.util.ReflectionUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.ReflectionUtils;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BondTradeSizeTooLargeRuleTest {

    BondTradeSizeTooLargeRule bondTradeSizeTooLargeRule = new BondTradeSizeTooLargeRule(300000);

    @Test
    public void executeRuleWhenBondTradeSizeIsTooLarge() {
        BondTrade bondTrade = mock(BondTrade.class);
        when(bondTrade.getSize()).thenReturn(2000000l);
        boolean result = bondTradeSizeTooLargeRule.executeRule(bondTrade);
        assertFalse(result);
    }

    @Test
    public void executeRuleWhenBondTradeSizeIsNotTooLarge() {
        BondTrade bondTrade = mock(BondTrade.class);
        when(bondTrade.getSize()).thenReturn(200000l);
        boolean result = bondTradeSizeTooLargeRule.executeRule(bondTrade);
        assertTrue(result);
    }

    @Test
    public void getRuleName() {
        assertEquals("TradeSizeLargeRule", bondTradeSizeTooLargeRule.getRuleName());
    }
}