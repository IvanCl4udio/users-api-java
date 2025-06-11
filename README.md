# CRUD Users
Simple application that store data about users in a database.

## How to build this application ##
Run the following command from root of repository:
```bash
mvn clean package
```

## How to create a new image ##
Run the following command from root of repository:
```bash
docker build -t ivancl4udio/crud-users:1.0.0 .
```

## How to run this application with h2 database ##
Run the following command from root of repository:
```bash
mvn spring-boot:run
```

## How to run this application with a PostgreSQL database ##
Run the following command from root of repository:
```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://database-name:5432/postgres
export SPRING_DATASOURCE_USERNAME=database-user
export SPRING_DATASOURCE_PASSWORD=database-user-password
export SPRING_JPA_HIBERNATE_DDL_AUTO=update
mvn spring-boot:run
```

## How to run this application with a PostgreSQL database and docker-compose ##
Run the following command from root of repository:
```bash
docker-compose up
```
