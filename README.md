# CRUD Works Application

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Technologies](#technologies)
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Configuration](#configuration)
- [Endpoints](#endpoints)
- [Usage](#usage)
- [Testing](#testing)
- [Contributing](#contributing)

## Introduction

- CRUD Works Application is a Spring Boot application designed for basic CRUD operations on entity records. It provides RESTful endpoints for Create, Read, Update, and Delete operations.

## Features

- Create, Read, Update, and Delete (CRUD) operations on entity records.
- Integration with an email notification service for CRUD operation notifications.

## Technologies

- Java
- Spring Boot
- Spring Data JPA
- Maven
- Javax Mail
- Logback
- Mockito
- JUnit
- H2 Database (for development)
- MySQL (for production)

## Prerequisites

- Java 11 or higher
- Maven 3.6.5 or higher
- MySQL database (for production)

## Setup

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-username/crud-works-application.git
   cd crud-works-application
##### Build the project:

* __`mvn clean install`__
##### Run the application:

* __`mvn spring-boot:run`__
## Configuration
##### Application Properties
Configure your email and database settings in `src/main/resources/application.properties`.

### properties
```java
# Email configuration
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-email-password

# Database configuration (H2 for development)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```
# MySQL Configuration
#### For production, configure MySQL settings:

#### properties

```java
spring.datasource.url=jdbc:mysql://localhost:3306/crud_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```
## Endpoints
##### CRUD Operations

* **GET all records:**` /v1/api`
* **GET record by ID:**` /v1/api/get/{id}`
* **POST create record:**` /v1/api`

****Request Body:****
~~~json
{
    "id":"001",
    "name": "Irfan",
    "email": "test@example.com",
    "mobile": "1234567890",
    "address": "Test Address",
    "opType": "POST"
}
~~~
JSON representation of CrudEntity
* **PUT update record:**` /v1/api/{id}`
****Request Body:****
~~~json
{
    "id":"002",
    "name": "Irfan",
    "email": "test@example.com",
    "mobile": "1234567890",
    "address": "Test Address",
    "opType": "PUT"
}
~~~
JSON representation of CrudEntity
* **DELETE record:**` /v1/api/{id}`
## Usage
- Use the provided endpoints to perform CRUD operations on entity records.
### Email Notification Sender

##### The application will send email notifications for the following cases:

- **POST Method:** An email will be sent to the recipient with the subject `"New Record Created"` and the body containing the Entity data.
- **PUT Method:** An email will be sent to the recipient with the subject `"Record Updated"` and the body containing the Entity data.
- **Exception Occurred:** An email will be sent to the recipient with the subject `"Exception Occurred"` and the body containing the exception message.

### Swagger Integration
- Swagger UI is integrated to provide API documentation. Access Swagger UI at `http://localhost:8080/swagger-ui/index.html`.

### Test Cases
- Unit test cases are implemented to verify the functionality of service methods, repository operations, and controller endpoints.

### Logging Configuration (Logback)
- Logback is configured to provide live logging and daily log rotation. Logs are stored in `logs/CRUD_works_application.log` and rotated daily.
## Contributing:
- Fork the repository.
- **Create a feature branch:** `git checkout -b` feature-name
- **Commit your changes:** `git commit -m` 'Add feature'
- **Push to the branch:** `git push origin` feature-name
- Open a pull request.

### Notes:
- Replace `your-username` and `your-repo-name` with your actual GitHub username and repository name.
- Ensure all configurations, such as the `application.properties`, match your actual setup.
- Update the sections to better reflect any additional features or specific instructions for your project.