package com.lbg.interview.trades.rules;

import com.lbg.interview.trades.domain.BondTrade;
import com.lbg.interview.trades.util.LoadTradesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;

@Slf4j
@Component
public class BondTradeRulesEngine {

    @Autowired
    LoadTradesUtil loadTradesUtil;

    @Autowired
    Set<BondTradeRule> bondTradeRules;

    @Value("${input.file.name:./src/test/resources/trades/bond-trades-dev.csv}")
    String fileName;

    private final Map<BondTrade, Boolean> bondTradeResults = new HashMap<>();

    /***
     * Loads BondTrades from the input file, executes all BondRules against them and builds a result map
     * @return Map<BondTrade, Boolean> - Result map
     */
    public Map<BondTrade, Boolean> runAllTradeRules() {

        List<BondTrade> bondTrades;

        try{
            bondTrades = loadTradesUtil.loadTradesFromFile(fileName);
        } catch (Exception e){
            log.info("Exception while loading trades from the file {}", fileName, e);
            return bondTradeResults;
        }

        for(BondTrade trade : bondTrades) {

            boolean result = true;

            for (BondTradeRule rule : bondTradeRules){

                if (!rule.executeRule(trade)){
                    result = false;
                    log.debug("Rule {} failed for BondTrade {}", rule.getRuleName(), trade);
                    break;
                }
            }

            bondTradeResults.put(trade, result);
        }

        return bondTradeResults;
    }
}
