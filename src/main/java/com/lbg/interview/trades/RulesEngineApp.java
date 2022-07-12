package com.lbg.interview.trades;

import com.lbg.interview.trades.domain.BondTrade;
import com.lbg.interview.trades.rules.BondTradeRulesEngine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@Slf4j
@SpringBootApplication
public class RulesEngineApp implements CommandLineRunner {

    @Autowired
    private BondTradeRulesEngine rulesEngine= new BondTradeRulesEngine();

    public static void main(String[] args) {

        SpringApplication.run(RulesEngineApp.class, args);
    }

    @Override
    public void run(String... args) {

        log.info("Ready");

        Map<BondTrade, Boolean> rulesEngineResults = rulesEngine.runAllTradeRules();

        // Print the results
        // Delete this ---> more efficient to use streams ???
        for (Map.Entry<BondTrade, Boolean> resultsEntry : rulesEngineResults.entrySet()) {

            final BondTrade bondTrade = resultsEntry.getKey();
            final Boolean ruleResult = resultsEntry.getValue();

            System.out.println("tradeId = " + bondTrade.getTradeId() + " instDesc = " + bondTrade.getInstrumentDesc() +
                " tradeDate = " + bondTrade.getTradeDate() + " tradeSize = " + bondTrade.getSize() +
                " RuleResult = " + (ruleResult ? "PASS" : "FAIL"));
        }

        System.exit(0);
    }
}
