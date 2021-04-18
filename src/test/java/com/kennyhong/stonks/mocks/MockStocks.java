package com.kennyhong.stonks.mocks;

import com.kennyhong.stonks.model.Stock;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MockStocks {
    public static final Stock validStock1 = new Stock(
            "GME",
            1,
            LocalDate.now(),
            BigDecimal.valueOf(1.00),
            BigDecimal.valueOf(2.00),
            BigDecimal.valueOf(3.00),
            BigDecimal.valueOf(4.00),
            Long.valueOf("323423423423234"),
            Float.parseFloat("0.1111"),
            Float.parseFloat("1.1222222"),
            Long.valueOf("3423423423234234"),
            BigDecimal.valueOf(5.00),
            BigDecimal.valueOf(6.00),
            Float.parseFloat("7.99999999"),
            20,
            Float.parseFloat("0.333333")
    );

    public static final Stock validStock2 = new Stock(
            "GME",
            1,
            LocalDate.now(),
            BigDecimal.valueOf(1.00),
            BigDecimal.valueOf(2.00),
            BigDecimal.valueOf(3.00),
            BigDecimal.valueOf(4.00),
            Long.valueOf("323423423423234"),
            Float.parseFloat("0.1111"),
            null,
            null,
            BigDecimal.valueOf(5.00),
            BigDecimal.valueOf(6.00),
            Float.parseFloat("7.99999999"),
            20,
            Float.parseFloat("0.333333")
    );

    public static final Stock validStock3 = new Stock(
            "GME",
            1,
            LocalDate.now(),
            BigDecimal.valueOf(1.00),
            BigDecimal.valueOf(2.00),
            BigDecimal.valueOf(3.00),
            BigDecimal.valueOf(4.00),
            Long.valueOf("323423423423234"),
            Float.parseFloat("0.1111"),
            null,
            null,
            BigDecimal.valueOf(5.00),
            BigDecimal.valueOf(6.00),
            Float.parseFloat("7.99999999"),
            20,
            Float.parseFloat("0.333333")
    );

    public static final Stock invalidStock = new Stock(
            "GME",
            1,
            LocalDate.now(),
            null,
            BigDecimal.valueOf(2.00),
            BigDecimal.valueOf(3.00),
            null,
            null,
            Float.parseFloat("0.1111"),
            null,
            null,
            BigDecimal.valueOf(5.00),
            BigDecimal.valueOf(6.00),
            Float.parseFloat("7.99999999"),
            20,
            Float.parseFloat("0.333333")
    );

    public static final String validStockCreate = "{\n" +
            "        \"stockName\": \"GME\",\n" +
            "        \"quarter\": 1,\n" +
            "        \"date\": \"2011-06-24\",\n" +
            "        \"open\": 14.67,\n" +
            "        \"high\": 15.60,\n" +
            "        \"low\": 14.56,\n" +
            "        \"close\": 15.23,\n" +
            "        \"volume\": 99423717,\n" +
            "        \"percentChangePrice\": 0.222222,\n" +
            "        \"prevWkPercentChangeVolume\": null,\n" +
            "        \"prevWkVolume\": null,\n" +
            "        \"nextWkOpen\": 15.22,\n" +
            "        \"nextWkClose\": 16.31,\n" +
            "        \"nextWkPercentChangePrice\": 7.16163,\n" +
            "        \"daysToNextDividend\": 40,\n" +
            "        \"percentDividendReturn\": 0.19698\n" +
            "    }";

    public static final String invalidStockCreate = "{\n" +
            "        \"stockName\": \"GME\",\n" +
            "        \"quarter\": 1,\n" +
            "        \"date\": \"2011-06-24\",\n" +
            "        \"open\": 14.67,\n" +
            "        \"high\": 15.60,\n" +
            "        \"low\": 14.56,\n" +
            "        \"close\": 15.23,\n" +
            "        \"volume\": 99423717,\n" +
            "        \"percentChangePrice\": null,\n" +
            "        \"prevWkPercentChangeVolume\": null,\n" +
            "        \"prevWkVolume\": null,\n" +
            "        \"nextWkOpen\": null,\n" +
            "        \"nextWkClose\": 16.31,\n" +
            "        \"nextWkPercentChangePrice\": 7.16163,\n" +
            "        \"daysToNextDividend\": 40,\n" +
            "        \"percentDividendReturn\": 0.19698\n" +
            "    }";

    public static final String validCSV = "quarter,stock,date,open,high,low,close,volume,percent_change_price," +
            "percent_change_volume_over_last_wk,previous_weeks_volume,next_weeks_open,next_weeks_close," +
            "percent_change_next_weeks_price,days_to_next_dividend,percent_return_next_dividend\n" +
            "1,GME,1/7/2011,$15.82 ,$16.72 ,$15.78 ,$16.42 ,239655616,3.79267,,,$16.71 ,$15.97 ,-4.42849,26,0.182704\n" +
            "1,GME,1/14/2011,$16.71 ,$16.71 ,$15.64 ,$15.97 ,242963398,-4.42849,1.380223028,239655616,$16.19 ,$15.79 ,-2.47066,19,0.187852\n" +
            "1,GME,1/21/2011,$16.19 ,$16.38 ,$15.60 ,$15.79 ,138428495,-2.47066,-43.02495926,242963398,$15.87 ,$16.13 ,1.63831,12,0.189994\n" +
            "1,GME,1/28/2011,$15.87 ,$16.63 ,$15.82 ,$16.13 ,151379173,1.63831,9.355500109,138428495,$16.18 ,$17.14 ,5.93325,5,0.185989\n" +
            "1,GME,2/4/2011,$16.18 ,$17.39 ,$16.18 ,$17.14 ,154387761,5.93325,1.987451735,151379173,$17.33 ,$17.37 ,0.230814,97,0.175029\n" +
            "1,GME,2/11/2011,$17.33 ,$17.48 ,$16.97 ,$17.37 ,114691279,0.230814,-25.71219489,154387761,$17.39 ,$17.28 ,-0.632547,90,0.172712\n" +
            "1,GME,2/18/2011,$17.39 ,$17.68 ,$17.28 ,$17.28 ,80023895,-0.632547,-30.22669579,114691279,$16.98 ,$16.68 ,-1.76678,83,0.173611";

    public static final String invalidHeadersCSV = "quarter,stock,date,open,high,low,close,volume,percent_change_price," +
            "percent_change_volume_over_last_wk,previous_weeks_volume,next_weeks_open,next_weeks_close\n" +
            "1,GME,1/7/2011,$15.82 ,$16.72 ,$15.78 ,$16.42 ,239655616,3.79267,,,$16.71 ,$15.97 ,-4.42849,26";
}
