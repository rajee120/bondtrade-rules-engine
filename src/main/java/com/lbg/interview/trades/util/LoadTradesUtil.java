package com.lbg.interview.trades.util;

import com.lbg.interview.trades.domain.BondTrade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class LoadTradesUtil {

    public List<BondTrade> loadTradesFromFile(String fileName) {

        if(fileName == null)
            return null;

        final Path filePath = Paths.get(fileName);

        try {

            List<String> tradeList = Files.readAllLines(filePath);

            return tradeList.stream()
                .map( tradeCsv -> createBondTradeFromCSV(tradeCsv) )
                .collect(Collectors.toList());

        } catch (Exception e) {
            log.error("Failed to process trade file");
        }

        return null;
    }

    public BondTrade createBondTradeFromCSV(String tradeCsv) {

        String[] tradeFieldValueArray = tradeCsv.split(",");

        String tradeId = tradeFieldValueArray[0];
        String instrumentId = tradeFieldValueArray[1];
        String instrumentDesc = tradeFieldValueArray[2];
        Long size = Long.parseLong(tradeFieldValueArray[3]);
        double price = Double.parseDouble(tradeFieldValueArray[4]);
        LocalDateTime tradeDate = LocalDateTime.parse(tradeFieldValueArray[5], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        LocalDate settlemetDate = LocalDate.parse(tradeFieldValueArray[6], DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return new BondTrade(tradeId, instrumentDesc, instrumentId, size, price, tradeDate, settlemetDate);
    }
}
