# FX Data Warehouse

FX Data Warehouse is a Spring Boot application for analyzing FX deals. It provides endpoints to accept deal details and persist them into a database.

## Setup

Clone the repository:

```sh
   git clone https://github.com/HAmer-Banna/fx-data-warehouse.git
```

Build the application:

```sh
    cd fx-data-warehouse
    mvn clean install
```

```sh
mvn spring-boot:run
The application will be available at http://localhost:8080.
```

## Docker Compose
You can also run the application using Docker Compose. This will start the application and a PostgreSQL database container.

Build the Docker image:

``` sh
docker-compose build
```

Start the application:
```
docker-compose up
```

## Testing
To run the unit tests, use the following command:
```
mvn test
```