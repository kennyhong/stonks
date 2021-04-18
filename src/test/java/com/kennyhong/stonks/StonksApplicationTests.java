package com.kennyhong.stonks;

import com.kennyhong.stonks.controller.StocksController;

import static com.kennyhong.stonks.mocks.MockStocks.*;
import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kennyhong.stonks.repository.StocksRepository;
import com.kennyhong.stonks.service.StocksService;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {StonksApplicationTests.Initializer.class})
public class StonksApplicationTests {

	@ClassRule
	public static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres")
			.withDatabaseName("postgres")
			.withUsername("integrationUser")
			.withPassword("testPass");

	@Autowired
	private StocksRepository repo;

	@Autowired
	private StocksController controller;

	@Autowired
	private StocksService service;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Value("http://localhost:${local.server.port}")
	String url;

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
		assertThat(service).isNotNull();
	}

	private void insertTestData() {
		repo.save(validStock1);
		repo.save(validStock2);
		repo.save(validStock3);
		repo.flush();
	}

	@Test
	public void testGet_returns_200_with_expected_stocks() {
		insertTestData();
		given()
				.formParam("stockName", "GME")
				.when().get(url + "/stocks").then()
				.statusCode(200).body("size()", is(3));
	}

	@Test
	public void testGet_returns_400_with_bad_formValue() {
		given().
				formParam("stock33333", "GME").
				when().get(url + "/stocks").then()
				.statusCode(400);
	}

	@Test
	public void testGet_returns_200_withEmptyList_when_value_notFound() {
		insertTestData();
		given()
				.formParam("stockName", "AAAA")
				.when().get(url + "/stocks").then()
				.statusCode(200)
				.body("size()", is(0));
	}

	@Test
	public void testPost_returns_201_withValidCSV() throws Exception {
		MockMultipartFile file = new MockMultipartFile(
				"file",
				"validCSV.csv",
				"text/csv",
				validCSV.getBytes(StandardCharsets.UTF_8));
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		mockMvc.perform(multipart(url+"/csv-upload").file(file)).andExpect(status().isCreated());
	}

	@Test
	public void testPost_returns_417_withInvalidCSV_hasInvalidHeaders() throws Exception {
		MockMultipartFile file = new MockMultipartFile(
				"file",
				"invalidCSV.csv",
				"text/csv",
				invalidHeadersCSV.getBytes(StandardCharsets.UTF_8));
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		mockMvc.perform(multipart(url+"/csv-upload").file(file)).andExpect(status().isExpectationFailed());
	}

	@Test
	public void testPost_returns_400_withInvalidFile_notCSV() throws Exception {
		MockMultipartFile file = new MockMultipartFile(
				"file",
				"invalidCSV.txt",
				"text/txt",
				invalidHeadersCSV.getBytes(StandardCharsets.UTF_8));
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		mockMvc.perform(multipart(url+"/csv-upload").file(file)).andExpect(status().isBadRequest());
	}

	@Test
	public void testPost_returns_201_withValidStock() throws Exception {
		given()
				.contentType("application/json")
				.body(validStockCreate)
				.when()
				.post(url + "/stock")
				.then()
				.statusCode(201)
				.body(
						"stock.stockName", is("GME")
				);
	}

	@Test
	public void testPost_returns_400_withInvalidStock() {
		given()
				.contentType("application/json")
				.body(invalidStockCreate)
				.when()
				.post(url + "/stock")
				.then()
				.statusCode(400);
	}

	@Test
	public void testPost_returns_415_withMediaType() {
		given()
				.body(invalidStockCreate)
				.when()
				.post(url + "/stock")
				.then()
				.statusCode(415);
	}

	static class Initializer
			implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
			TestPropertyValues.of(
					"spring.datasource.url=" + postgres.getJdbcUrl(),
					"spring.datasource.username=" + postgres.getUsername(),
					"spring.datasource.password=" + postgres.getPassword()
			).applyTo(configurableApplicationContext.getEnvironment());
		}
	}

}
