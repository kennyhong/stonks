package com.kennyhong.stonks.response;

import com.kennyhong.stonks.model.Stock;
import lombok.Data;

@Data
public class StockResponse {
    private Stock stock;
    private String error;

    public StockResponse(Stock stock, String error) {
        this.stock = stock;
        this.error = error;
    }
}
