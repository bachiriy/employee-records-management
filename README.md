# Employee Records Management System

A Java-based desktop application for managing employee records with REST API support.

## Features

- Employee management (CRUD operations)
- Audit logging
- Role-based access control
- Search and filtering capabilities
- REST API with Swagger documentation

## Technologies

- Java 17
- Spring Boot
- Spring Security
- Oracle Database
- Swing UI
- Docker

## Getting Started

1. Clone the repository
2. Run `mvn clean install` to build the project
3. Start the database: `docker-compose up db`
4. Run the application: `docker-compose up app`

## API Documentation

Access Swagger UI at: http://localhost:8080/swagger-ui/

## Default Users

- Admin: admin/admin123
- HR: hr/hr123

## License

MIT

