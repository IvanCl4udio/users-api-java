# CRUD Users
Simple application that store data about users in a database.

[![Build](https://github.com/IvanCl4udio/crud-users/actions/workflows/build.yml/badge.svg)](https://github.com/IvanCl4udio/crud-users/actions/workflows/build.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=IvanCl4udio_crud-users&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=IvanCl4udio_crud-users)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=IvanCl4udio_crud-users&metric=coverage)](https://sonarcloud.io/summary/new_code?id=IvanCl4udio_crud-users)

## How build this application ##
Run the following command from root of repository:
```bash
mvn clean package
```

## How create a new image ##
Run the following command from root of repository:
```bash
docker build -t ivancl4udio/crud-users:1.0.0 .
```

## How run this application with h2 database ##
Run the following command from root of repository:
```bash
mvn spring-boot:run
```

## How run this application with a Postgresql database ##
Run the following command from root of repository:
```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://database-name:5432/postgres
export SPRING_DATASOURCE_USERNAME=database-user
export SPRING_DATASOURCE_PASSWORD=database-user-password
export SPRING_JPA_HIBERNATE_DDL_AUTO=update
mvn spring-boot:run
```

## How run this application with a postgresql database and docker-compose ##
Run the following command from root of repository:
```bash
docker-compose up
```
