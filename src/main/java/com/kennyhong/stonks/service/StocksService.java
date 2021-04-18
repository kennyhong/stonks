package com.kennyhong.stonks.service;

import com.kennyhong.stonks.helper.CSVHelper;
import com.kennyhong.stonks.model.Stock;
import com.kennyhong.stonks.repository.StocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class StocksService {
    @Autowired
    StocksRepository repository;

    public void save(MultipartFile file) {
        try {
            List<Stock> stocks = CSVHelper.fileToStocks(file.getInputStream());
            repository.saveAll(stocks);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save csv data: " + e.getMessage());
        }
    }

    public Stock save(Stock stock) {
        return repository.save(stock);
    }

    public List<Stock> getAllStocks() {
        return repository.findAll();
    }

    public List<Stock> getStocksByName(String name) {
        return repository.findByStockName(name);
    }
}
