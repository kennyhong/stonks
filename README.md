# Stonks Application

An application developed as part of a challenge

## Project Structure

```shell
.
├── README.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── src
│   ├── main
│   │   ├── docker
│   │   │   ├── Dockerfile
│   │   │   ├── docker-compose.yml
│   │   │   └── stonks-0.0.1-SNAPSHOT.jar
│   │   ├── java
│   │   │   └── com
│   │   │       └── kennyhong
│   │   │           └── stonks
│   │   │               ├── StonksApplication.java
│   │   │               ├── controller
│   │   │               │   └── StocksController.java
│   │   │               ├── helper
│   │   │               │   ├── CSVHelper.java
│   │   │               │   ├── HeaderConstants.java
│   │   │               │   └── StockBean.java
│   │   │               ├── model
│   │   │               │   └── Stock.java
│   │   │               ├── repository
│   │   │               │   └── StocksRepository.java
│   │   │               ├── response
│   │   │               │   ├── GenericResponse.java
│   │   │               │   └── StockResponse.java
│   │   │               └── service
│   │   │                   └── StocksService.java
│   │   └── resources
│   │       └── application.properties
│   └── test
│       └── java
│           └── com
│               └── kennyhong
│                   └── stonks
│                       ├── StonksApplicationTests.java
│                       ├── integration
│                       │   └── StockRepositoryIntegrationTests.java
│                       └── mocks
│                           └── MockStocks.java
```

## Tech Used
* Docker
* Postgres
* Spring Boot
    * Lombok
    * Jpa Repository
    * Postgres Driver
    * OpenCSV
* Testing
  * Rest-Assured
  * Regular Testing Suites
  
## Journey

As someone who is relatively new to Spring Boot, but not new to many of the concepts used in this challenge I will give a brief overview on how I tackled this challenge. This project took about a total of ~6 hours over two days to complete. Here's a breakdown:

* 0.5 hours SpringBoot documentation
* 0.5 hours testing documentation
* 4 hours development and testing
* 0.5 hours food break (Coffee and Bún bò Huế)
* 0.5 hours walk on a sunny day
  
## Design Decisions / Future Implementations

### API Design
To display different ways of creating and displaying, as well as to keep the scope of the project a little smaller, 
I opted to implement an upload via CSV rather than an array of objects which gets serialized into a List of Stock objects and saved. 
You can see the calls implemented, and their respective examples below. Some possibly fun implementations is to attempt to implement the paper associated with the data set and return the forecast of the stocks.

### Future Testing Implementation
To keep the scope of this project relatively small, I've opted to test different cases within the repository and the service, however due to time constraints I wasn't able to finish unit tests for my CSVHelper class. 
Future iterations of this project would include those tests as well as other test cases that may come up.

## Fresh Installation

**A `jar` file is already included in this project, so you can just navigate to the docker folder and run 
`docker-compose up --build`. See the final step of this section to get started!**

Otherwise, to get a fresh `jar` file you can do the following.

From the `stonks` folder use the following commands:

    ./mvnw clean package

This will build the .jar file and run the tests as well. Alternatively, you can use your IDE to run the tests or 
run `./mvnw test`

    cp target/stonks-0.0.1-SNAPSHOT.jar src/main/docker/

This will move the `jar` file into the docker folder needed to run the compose.
    
    cd src/main/docker
    docker-compose up --build

With the `jar` file in the folder you can navigate to the docker folder and run the `docker-compose up --build` command


## Usage
For easy usage, navigate to src/main/docker and use `docker-compose up` to start the containers for both the application and the Postgres database.

### `GET` `/stocks`

Description: Gets All Stocks

Params: `form-data`

    stockName = GME

Example Response:
```json
[
  {
    "id": 1506,
    "stockName": "GME",
    "quarter": 1,
    "date": "2011-06-24",
    "open": 14.67,
    "high": 15.60,
    "low": 14.56,
    "close": 15.23,
    "volume": 99423717,
    "percentChangePrice": 3.81731,
    "prevWkPercentChangeVolume": -10.6493,
    "prevWkVolume": 111273576,
    "nextWkOpen": 15.22,
    "nextWkClose": 16.31,
    "nextWkPercentChangePrice": 7.16163,
    "daysToNextDividend": 40,
    "percentDividendReturn": 0.19698
  },
  {
    "id": 1507,
    "stockName": "GME",
    "quarter": 1,
    "date": "2011-06-24",
    "open": 14.67,
    "high": 15.60,
    "low": 14.56,
    "close": 15.23,
    "volume": 99423717,
    "percentChangePrice": 3.81731,
    "prevWkPercentChangeVolume": null,
    "prevWkVolume": null,
    "nextWkOpen": 15.22,
    "nextWkClose": 16.31,
    "nextWkPercentChangePrice": 7.16163,
    "daysToNextDividend": 40,
    "percentDividendReturn": 0.19698
  }
]
```


### `POST` `/csv-upload`

Description: Takes an uploaded CSV file and imports all the records

Params: `form-data`

    file="file.csv"

Example Response

    {
      "responseMessage": "dow_jones_index.csv has been successfully saved."
    }

### `POST` `/stock` 

Description: Creates a single stock record

Params: `application/json` / json body

Example Request:

```json
{
        "stockName": "GME",
        "quarter": 1,
        "date": "2011-06-24",
        "open": 14.67,
        "high": 15.60,
        "low": 14.56,
        "close": 15.23,
        "volume": 99423717,
        "percentChangePrice": 0.22222,
        "prevWkPercentChangeVolume": null,
        "prevWkVolume": null,
        "nextWkOpen": 15.22,
        "nextWkClose": 16.31,
        "nextWkPercentChangePrice": 7.16163,
        "daysToNextDividend": 40,
        "percentDividendReturn": 0.19698
}
```

Example Response:

```json
{
    "stock": {
        "id": 3011,
        "stockName": "GME",
        "quarter": 1,
        "date": "2011-06-24",
        "open": 14.67,
        "high": 15.60,
        "low": 14.56,
        "close": 15.23,
        "volume": 99423717,
        "percentChangePrice": 0.22222,
        "prevWkPercentChangeVolume": null,
        "prevWkVolume": null,
        "nextWkOpen": 15.22,
        "nextWkClose": 16.31,
        "nextWkPercentChangePrice": 7.16163,
        "daysToNextDividend": 40,
        "percentDividendReturn": 0.19698
    },
    "error": ""
}
```

### `GET` `/all-stocks` 

Description: Gets all the records in the db.

Params: None

## Final Words

Thank you!
