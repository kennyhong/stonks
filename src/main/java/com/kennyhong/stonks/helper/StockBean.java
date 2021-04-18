package com.kennyhong.stonks.helper;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;


@Data
public class StockBean {
    @CsvBindByPosition(position = 0, required = true)
    private String quarter;

    @CsvBindByPosition(position = 1, required = true)
    private String stockName;

    @CsvBindByPosition(position = 2, required = true)
    private String date;

    @CsvBindByPosition(position = 3, required = true)
    private String open;

    @CsvBindByPosition(position = 4, required = true)
    private String high;

    @CsvBindByPosition(position = 5, required = true)
    private String low;

    @CsvBindByPosition(position = 6, required = true)
    private String close;

    @CsvBindByPosition(position = 7, required = true)
    private String volume;

    @CsvBindByPosition(position = 8, required = true)
    private String percentChangePrice;

    @CsvBindByPosition(position = 9)
    private String prevWkPercentChangeVolume;

    @CsvBindByPosition(position = 10)
    private String prevWkVolume;

    @CsvBindByPosition(position = 11, required = true)
    private String nextWkOpen;

    @CsvBindByPosition(position = 12, required = true)
    private String nextWkClose;

    @CsvBindByPosition(position = 13, required = true)
    private String nextWkPercentChangePrice;

    @CsvBindByPosition(position = 14, required = true)
    private String daysToNextDividend;

    @CsvBindByPosition(position = 15, required = true)
    private String percentDividendReturn;
}
