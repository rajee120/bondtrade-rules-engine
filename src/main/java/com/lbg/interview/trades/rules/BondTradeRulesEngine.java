package com.lbg.interview.trades.rules;

import com.lbg.interview.trades.domain.BondTrade;
import com.lbg.interview.trades.util.LoadTradesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BondTradeRulesEngine {

    private static final BondTradeRule IS_TODAY_TRADE_RULE = new BondTradedTodayRule();
    private static final BondTradeRule MAX_TRADE_SIZE_RULE = new BondTradeSizeTooLargeRule(3000000);

    @Autowired
    LoadTradesUtil loadTradesUtil;

    private final Map<BondTrade, Boolean> bondTradeResults = new HashMap<>();


    public Map<BondTrade, Boolean> runAllTradeRules() {

        List<BondTrade> dataList = loadTradesUtil.loadTradesFromFile("./src/test/resources/trades/bond-trades-dev.csv");

        for(BondTrade data : dataList) {

            boolean isTodaysTrade = IS_TODAY_TRADE_RULE.executeRule(data);

            if(isTodaysTrade) {
                boolean isTradeWithinSize = MAX_TRADE_SIZE_RULE.executeRule(data);

                bondTradeResults.put(data, isTradeWithinSize);
            } else {
                bondTradeResults.put(data, isTodaysTrade);
            }
        }

        return bondTradeResults;
    }
}
