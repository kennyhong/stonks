package com.kennyhong.stonks.helper;

import com.kennyhong.stonks.model.Stock;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {
    public static final String TYPE = "text/csv";
    static String [] HEADERS = HeaderConstants.getAllHeaders();

    public static boolean isCSVFormat(MultipartFile file) {
        return file.getContentType() != null && file.getContentType().equals(TYPE);
    }

    public static List<Stock> fileToStocks(InputStream stream) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            String [] headers = reader.readLine().split(",");

            for (String header : HEADERS) {
                if(!Arrays.asList(headers).contains(header)) {
                    throw new Exception(header + " is required in the csv");
                }
            }

            for (String header : headers) {
                if(!Arrays.asList(HEADERS).contains(header)) {
                    throw new Exception("Invalid Header Found!");
                }
            }

            List<StockBean> beans = new CsvToBeanBuilder<StockBean>(reader)
                    .withType(StockBean.class)
                    .build()
                    .parse();
            List<Stock> stocks = new ArrayList<>();
            beans.forEach(stockBean -> {
                String [] stockBeanDate = stockBean.getDate().trim().split("/");
                int stockYear = Integer.parseInt(stockBeanDate[2]);
                int stockMonth = Integer.parseInt(stockBeanDate[0]);
                int stockDay = Integer.parseInt(stockBeanDate[1]);

                Float prevWkPctChangeVol = null;
                Long prevWkVol = null;

                if (!stockBean.getPrevWkPercentChangeVolume().isEmpty()) {
                    prevWkPctChangeVol = Float.parseFloat(stockBean.getPrevWkPercentChangeVolume().trim());
                }

                if (!stockBean.getPrevWkVolume().isEmpty()) {
                    prevWkVol = Long.parseLong(stockBean.getPrevWkVolume().trim());
                }
                Stock stock = new Stock(
                        stockBean.getStockName().trim(),
                        Integer.parseInt(stockBean.getQuarter().trim()),
                        LocalDate.of(stockYear, stockMonth, stockDay),
                        new BigDecimal(stockBean.getOpen().trim().replace("$","")),
                        new BigDecimal(stockBean.getHigh().trim().replace("$","")),
                        new BigDecimal(stockBean.getLow().trim().replace("$","")),
                        new BigDecimal(stockBean.getClose().trim().replace("$","")),
                        Long.parseLong(stockBean.getVolume()),
                        Float.parseFloat(stockBean.getPercentChangePrice()),
                        prevWkPctChangeVol,
                        prevWkVol,
                        new BigDecimal(stockBean.getNextWkOpen().trim().replace("$","")),
                        new BigDecimal(stockBean.getNextWkClose().trim().replace("$","")),
                        Float.parseFloat(stockBean.getNextWkPercentChangePrice().trim()),
                        Integer.parseInt(stockBean.getDaysToNextDividend().trim()),
                        Float.parseFloat(stockBean.getPercentDividendReturn().trim())
                );
                stocks.add(stock);
            });
            return stocks;
        } catch (Exception e) {
            throw new IOException("Cannot process csv file: " + e.getMessage());
        }
    }
}
