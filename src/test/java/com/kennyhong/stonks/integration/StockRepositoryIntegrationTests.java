package com.kennyhong.stonks.integration;

import com.kennyhong.stonks.model.Stock;
import com.kennyhong.stonks.repository.StocksRepository;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;

import static com.kennyhong.stonks.mocks.MockStocks.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = {StockRepositoryIntegrationTests.Initializer.class})
public class StockRepositoryIntegrationTests {
    @ClassRule
    public static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres")
            .withDatabaseName("postgres")
            .withUsername("test")
            .withPassword("test");

    @Test
    public void contextLoads() {

    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgres.getJdbcUrl(),
                    "spring.datasource.username=" + postgres.getUsername(),
                    "spring.datasource.password=" + postgres.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Autowired
    StocksRepository repo;

    @Test
    public void repository_addStock_whereValidStockHasAllInputs() {
        Stock stockSaved = repo.save(validStock1);
        assertThat(stockSaved.getId()).isNotNull();
        assertThat(stockSaved.getStockName()).isEqualTo(validStock1.getStockName());
        assertThat(stockSaved.getQuarter()).isEqualTo(validStock1.getQuarter());
        assertThat(stockSaved.getDate()).isEqualTo(validStock1.getDate());
        assertThat(stockSaved.getOpen()).isEqualTo(validStock1.getOpen());
        assertThat(stockSaved.getClose()).isEqualTo(validStock1.getClose());
        assertThat(stockSaved.getHigh()).isEqualTo(validStock1.getHigh());
        assertThat(stockSaved.getLow()).isEqualTo(validStock1.getLow());
        assertThat(stockSaved.getVolume()).isEqualTo(validStock1.getVolume());
        assertThat(stockSaved.getPercentChangePrice()).isEqualTo(validStock1.getPercentChangePrice());
        assertThat(stockSaved.getPrevWkVolume()).isEqualTo(validStock1.getPrevWkVolume());
        assertThat(stockSaved.getPrevWkPercentChangeVolume()).isEqualTo(validStock1.getPrevWkPercentChangeVolume());
        assertThat(stockSaved.getNextWkOpen()).isEqualTo(validStock1.getNextWkOpen());
        assertThat(stockSaved.getNextWkClose()).isEqualTo(validStock1.getNextWkClose());
        assertThat(stockSaved.getNextWkPercentChangePrice()).isEqualTo(validStock1.getNextWkPercentChangePrice());
        assertThat(stockSaved.getDaysToNextDividend()).isEqualTo(validStock1.getDaysToNextDividend());
        assertThat(stockSaved.getPercentDividendReturn()).isEqualTo(validStock1.getPercentDividendReturn());
    }

    @Test
    public void repository_addStock_whereValidStockHasNullableInput () {
        Stock stockSaved = repo.save(validStock2);
        assertThat(stockSaved.getId()).isNotNull();
        assertThat(stockSaved.getStockName()).isEqualTo(validStock2.getStockName());
        assertThat(stockSaved.getQuarter()).isEqualTo(validStock2.getQuarter());
        assertThat(stockSaved.getDate()).isEqualTo(validStock2.getDate());
        assertThat(stockSaved.getOpen()).isEqualTo(validStock2.getOpen());
        assertThat(stockSaved.getClose()).isEqualTo(validStock2.getClose());
        assertThat(stockSaved.getHigh()).isEqualTo(validStock2.getHigh());
        assertThat(stockSaved.getLow()).isEqualTo(validStock2.getLow());
        assertThat(stockSaved.getVolume()).isEqualTo(validStock2.getVolume());
        assertThat(stockSaved.getPercentChangePrice()).isEqualTo(validStock2.getPercentChangePrice());
        assertThat(stockSaved.getPrevWkVolume()).isNull();
        assertThat(stockSaved.getPrevWkPercentChangeVolume()).isNull();
        assertThat(stockSaved.getNextWkOpen()).isEqualTo(validStock2.getNextWkOpen());
        assertThat(stockSaved.getNextWkClose()).isEqualTo(validStock2.getNextWkClose());
        assertThat(stockSaved.getNextWkPercentChangePrice()).isEqualTo(validStock2.getNextWkPercentChangePrice());
        assertThat(stockSaved.getDaysToNextDividend()).isEqualTo(validStock2.getDaysToNextDividend());
        assertThat(stockSaved.getPercentDividendReturn()).isEqualTo(validStock2.getPercentDividendReturn());
    }

    @Test(expected = Exception.class)
    public void repository_addStock_throwsExceptionWithInvalidInput() {
        repo.save(invalidStock);
    }

    @Test
    public void repository_afterSaves_mustHaveEntries() {
        insertStocks();
        List<Stock> stocks = repo.findAll();
        assertThat(stocks.size()).isGreaterThan(0);
    }

    private void insertStocks() {
        repo.save(validStock1);
        repo.save(validStock2);
        repo.flush();
    }
}
