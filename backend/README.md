## Features

- Calculate future value of mutual funds
- Fetch mutual fund data from a database
- RESTful API endpoints

## Technologies Used

- Java 8
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven

## Getting Started

### Prerequisites

- Java 17
- Maven
- PostgreSQL

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/your-repo/mutualfunds.git
    cd mutualfunds
    ```

2. Configure the database in :
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/mutualfunds
    spring.datasource.username=your-username
    spring.datasource.password=your-password
    ```

3. Build the project:
    ```sh
    ./mvnw clean install
    ```

4. Run the application:
    ```sh
    ./mvnw spring-boot:run
    ```

### API Endpoints

- **Calculate Future Value**
    - **URL:** `/mutualfunds/requests/calculate/futureValue`
    - **Method:** `POST`
    - **Request Body:**
        ```json
        {
            "ticker": "VFINX",
            "name": "Vanguard 500 Index Fund",
            "InitialInvestment": 1000,
            "time": 5
        }
        ```
    - **Response:**
        ```json
        {
            "futureValue": 1234.56
        }
        ```

- **Get All Mutual Funds**
    - **URL:** `/mutualfunds/requests/allFunds`
    - **Method:** `GET`
    - **Response:**
        ```json
        [
            {
                "id": 1,
                "ticker": "VFINX",
                "name": "Vanguard 500 Index Fund",
                "riskRate": 0.0427,
                "marketRate": 0.2484
            },
            ...
        ]
        ```

- **Get Mutual Fund By ID**
    - **URL:** `/mutualfunds/requests/byId`
    - **Method:** `GET`
    - **Query Parameter:** 
    - **Response:**
        ```json
        {
            "id": 1,
            "ticker": "VFINX",
            "name": "Vanguard 500 Index Fund",
            "riskRate": 0.0427,
            "marketRate": 0.2484
        }
        ```
