package com.kennyhong.stonks.helper;

public class HeaderConstants {
    public final static String STOCK_NAME = "stock";
    public final static String QUARTER = "quarter";
    public final static String DATE = "date";
    public final static String OPEN = "open";
    public final static String HIGH = "high";
    public final static String LOW = "low";
    public final static String CLOSE = "close";
    public final static String VOLUME = "volume";
    public final static String PCT_CHG_PRICE = "percent_change_price";
    public final static String PCT_CHG_VOL_OVR_LST_WK = "percent_change_volume_over_last_wk";
    public final static String PREV_WK_VOLUME = "previous_weeks_volume";
    public final static String NEXT_WK_OPEN = "next_weeks_open";
    public final static String NEXT_WK_CLOSE = "next_weeks_close";
    public final static String PCT_CHG_NXT_WK_PRICE = "percent_change_next_weeks_price";
    public final static String DAYS_TO_DIVIDEND = "days_to_next_dividend";
    public final static String PCT_RT_NXT_DIVIDEND = "percent_return_next_dividend";

    public static String[] getAllHeaders() {
        return new String[] {
                STOCK_NAME,
                QUARTER,
                DATE,
                OPEN,
                HIGH,
                LOW,
                CLOSE,
                VOLUME,
                PCT_CHG_PRICE,
                PCT_CHG_VOL_OVR_LST_WK,
                PREV_WK_VOLUME,
                NEXT_WK_OPEN,
                NEXT_WK_CLOSE,
                PCT_CHG_NXT_WK_PRICE,
                DAYS_TO_DIVIDEND,
                PCT_RT_NXT_DIVIDEND
        };
    }
}
