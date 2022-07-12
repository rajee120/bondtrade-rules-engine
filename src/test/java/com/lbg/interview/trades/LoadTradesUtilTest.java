package com.lbg.interview.trades;

import com.lbg.interview.trades.domain.BondTrade;
import com.lbg.interview.trades.util.LoadTradesUtil;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class LoadTradesUtilTest {

    @Test
    public void loadTradesFromFile() {
        LoadTradesUtil loadTradesUtil = new LoadTradesUtil();

        List<BondTrade> bondTradeList = loadTradesUtil.loadTradesFromFile(
            "./src/test/resources/trades/bond-trades-dev.csv");

        assertNotNull(bondTradeList);
        assertEquals(5, bondTradeList.size());
    }
}