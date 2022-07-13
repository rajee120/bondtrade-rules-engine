package com.lbg.interview.trades.util;

import com.lbg.interview.trades.domain.BondTrade;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Lists.newArrayList;

@Slf4j
@Component
public class LoadTradesUtil {

    private static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    /***
     * Reads a file passed in as argument and creates a List of BondTrade objects
     * @param fileName - Input file name.
     * @return List<BondTrade>
     */
    public List<BondTrade> loadTradesFromFile(String fileName) {

        checkArgument(!StringUtils.isEmpty(fileName), "Invalid File Name");

        List<BondTrade> bondTrades = newArrayList();

        try (Stream<String> lines = Files.lines(Paths.get(fileName))){

            bondTrades.addAll(lines.map(this::createBondTradeFromCSV)
                    .collect(Collectors.toList()));

        } catch (IOException e) {
            log.error("Failed to process trade file", e);
        }

        log.info("Loaded {} BondTrades from file {}", bondTrades.size(), fileName);

        return bondTrades;
    }

    private BondTrade createBondTradeFromCSV(String tradeCsv) {

        String[] tradeFieldValueArray = tradeCsv.split(",");

        return new BondTrade.Builder()
                            .withTradeId(tradeFieldValueArray[0])
                            .withInstrumentId(tradeFieldValueArray[1])
                            .withInstrumentDesc(tradeFieldValueArray[2])
                            .withSize(Long.parseLong(tradeFieldValueArray[3]))
                            .withPrice(Double.parseDouble(tradeFieldValueArray[4]))
                            .withTradeDate(LocalDateTime.parse(tradeFieldValueArray[5], DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)))
                            .withSettlementDate(LocalDate.parse(tradeFieldValueArray[6], DateTimeFormatter.ofPattern(DATE_FORMAT)))
                            .build();
    }
}