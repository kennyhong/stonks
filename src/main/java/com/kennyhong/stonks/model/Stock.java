package com.kennyhong.stonks.model;

import com.kennyhong.stonks.helper.HeaderConstants;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name=HeaderConstants.STOCK_NAME, nullable = false)
    private String stockName;

    @Column(name=HeaderConstants.QUARTER, nullable = false)
    private Integer quarter;

    @Column(name=HeaderConstants.DATE, nullable = false)
    private LocalDate date;

    @Column(name=HeaderConstants.OPEN, nullable = false)
    private BigDecimal open;

    @Column(name=HeaderConstants.HIGH, nullable = false)
    private BigDecimal high;

    @Column(name=HeaderConstants.LOW, nullable = false)
    private BigDecimal low;

    @Column(name=HeaderConstants.CLOSE, nullable = false)
    private BigDecimal close;

    @Column(name=HeaderConstants.VOLUME, nullable = false)
    private Long volume;

    @Column(name=HeaderConstants.PCT_CHG_PRICE, nullable = false)
    private Float percentChangePrice;

    @Column(name=HeaderConstants.PCT_CHG_VOL_OVR_LST_WK)
    private Float prevWkPercentChangeVolume;

    @Column(name=HeaderConstants.PREV_WK_VOLUME)
    private Long prevWkVolume;

    @Column(name=HeaderConstants.NEXT_WK_OPEN, nullable = false)
    private BigDecimal nextWkOpen;

    @Column(name=HeaderConstants.NEXT_WK_CLOSE, nullable = false)
    private BigDecimal nextWkClose;

    @Column(name=HeaderConstants.PCT_CHG_NXT_WK_PRICE, nullable = false)
    private Float nextWkPercentChangePrice;

    @Column(name=HeaderConstants.DAYS_TO_DIVIDEND, nullable = false)
    private Integer daysToNextDividend;

    @Column(name=HeaderConstants.PCT_RT_NXT_DIVIDEND, nullable = false)
    private Float percentDividendReturn;

    public Stock(String stockName, Integer quarter, LocalDate date,
                 BigDecimal open, BigDecimal high, BigDecimal low,
                 BigDecimal close, Long volume, Float percentChangePrice,
                 Float prevWkPercentChangeVolume, Long prevWkVolume, BigDecimal nextWkOpen,
                 BigDecimal nextWkClose, Float nextWkPercentChangePrice, Integer daysToNextDividend,
                 Float percentDividendReturn) {
        this.stockName = stockName;
        this.quarter = quarter;
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.percentChangePrice = percentChangePrice;
        this.prevWkPercentChangeVolume = prevWkPercentChangeVolume;
        this.prevWkVolume = prevWkVolume;
        this.nextWkOpen = nextWkOpen;
        this.nextWkClose = nextWkClose;
        this.nextWkPercentChangePrice = nextWkPercentChangePrice;
        this.daysToNextDividend = daysToNextDividend;
        this.percentDividendReturn = percentDividendReturn;
    }

    public Stock() {

    }
}
