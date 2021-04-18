package com.kennyhong.stonks.repository;

import com.kennyhong.stonks.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StocksRepository extends JpaRepository <Stock, Long> {
    @Query("SELECT s FROM Stock s where s.stockName = ?1")
    public List<Stock> findByStockName(String stockName);
}
