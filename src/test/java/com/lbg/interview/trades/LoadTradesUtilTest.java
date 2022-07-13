package com.lbg.interview.trades;

import com.lbg.interview.trades.domain.BondTrade;
import com.lbg.interview.trades.util.LoadTradesUtil;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
public class LoadTradesUtilTest {

    @Test
    public void loadTradesFromFile() {
        LoadTradesUtil loadTradesUtil = new LoadTradesUtil();

        List<BondTrade> bondTradeList = loadTradesUtil.loadTradesFromFile(
                "./src/test/resources/trades/bond-trades-dev.csv");

        assertEquals(5, bondTradeList.size());
    }

    @Test
    public void loadTradesFromFileWhenFileIsNotPresent() {
        LoadTradesUtil loadTradesUtil = new LoadTradesUtil();

        List<BondTrade> bondTradeList = loadTradesUtil.loadTradesFromFile(
                "./src/test/resources/trades/bond-trades-dev1.csv");

        assertEquals(0, bondTradeList.size());
    }

    @Test
    public void loadTradesFromFileWhenFileIsValid() {
        LoadTradesUtil loadTradesUtil = new LoadTradesUtil();

        try {
            List<BondTrade> bondTradeList = loadTradesUtil.loadTradesFromFile("");
        } catch (Exception e){
            assertTrue(e instanceof IllegalArgumentException);
            assertEquals("Invalid File Name", e.getMessage());
        }
    }
}