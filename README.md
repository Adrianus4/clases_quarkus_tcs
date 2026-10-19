# Clases Quarkus TCS

Este repositorio contiene un proyecto backend desarrollado con Java y Quarkus, orientado a la creación de servicios REST con persistencia y endpoints CRUD.

## Descripción del proyecto

El objetivo de este proyecto es demostrar el uso de Quarkus para crear una API REST con Java, incluyendo:

- Endpoints REST
- Serialización JSON con Jackson
- Arquitectura simple por capas
- CRUD de productos
- Ejecución rápida con Maven y Quarkus

## Tecnologías

- Java 21
- Quarkus
- Maven
- Jakarta REST
- Jackson

## Estructura del proyecto

```text
clases_quarkus_tcs/
├── backend-service/
│   └── backend-service/
│       ├── src/
│       ├── pom.xml
│       ├── mvnw
│       ├── README.md
│       └── target/


## Peticiones
- GET /api/products
- GET /api/products/{id}
- POST /api/products
- PUT /api/products/{id}
- DELETE /api/products/{id}
