package com.kennyhong.stonks.controller;

import com.kennyhong.stonks.helper.CSVHelper;
import com.kennyhong.stonks.model.Stock;
import com.kennyhong.stonks.response.GenericResponse;
import com.kennyhong.stonks.response.StockResponse;
import com.kennyhong.stonks.service.StocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class StocksController {

    @Autowired
    StocksService stocksService;

    @GetMapping("/all-stocks")
    public List<Stock> getAllStocks() {
        return stocksService.getAllStocks();
    }

    // Upload Bulk Data Set Via CSV
    @PostMapping("/csv-upload")
    public ResponseEntity<GenericResponse> uploadCsv(@RequestParam("file") MultipartFile file) {
        String responseMessage;

        if (CSVHelper.isCSVFormat(file)) {
            try {
                stocksService.save(file);
                responseMessage = file.getOriginalFilename() + " has been successfully saved.";
                return ResponseEntity.status(HttpStatus.CREATED).body(new GenericResponse(responseMessage));
            } catch (Exception e) {
                responseMessage = "Upload failed! Please check the file: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new GenericResponse(responseMessage));
            }
        }

        responseMessage = "Please upload a valid file. (.csv)";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericResponse(responseMessage));
    }

    @GetMapping("/stocks")
    public ResponseEntity<List<Stock>> getStock(@RequestParam("stockName") String stockName) {
        return ResponseEntity.status(HttpStatus.OK).body(stocksService.getStocksByName(stockName));
    }

    // Create Single Record
    @PostMapping("/stock")
    public ResponseEntity<StockResponse> createStock(@RequestBody Stock stock) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new StockResponse(stocksService.save(stock), ""));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new StockResponse(null, "Stock failed to save, please check input."));
        }
    }
}
