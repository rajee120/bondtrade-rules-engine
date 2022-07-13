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
    private BondTradeRulesEngine rulesEngine;

    public static void main(String[] args) {

        SpringApplication.run(RulesEngineApp.class, args);
    }

    @Override
    public void run(String... args) {

        log.info("Starting the application ...");

        Map<BondTrade, Boolean> rulesEngineResults = rulesEngine.runAllTradeRules();

        log.info("Processed {} BondTrades, printing the results", rulesEngineResults.size());

        rulesEngineResults.entrySet().forEach(this::printResult);
    }

    private void printResult(Map.Entry<BondTrade, Boolean> entry){

        final BondTrade bondTrade = entry.getKey();
        final Boolean ruleResult = entry.getValue();

        System.out.println(String.format("tradeId = %s, instDesc = %s, tradeDate = %s, tradeSize = %d, RuleResult = %s",
                bondTrade.getTradeId(), bondTrade.getInstrumentDesc(), bondTrade.getTradeDate().toLocalDate().toString(), bondTrade.getSize(), ruleResult ? "PASS" : "FAIL"));

    }
}