# Employee Records Management System

## Overview
The Employee Records Management System is a Java-based application designed to centralize and streamline the management of employee data across departments. This project provides functionality for HR personnel, managers, and administrators to effectively manage employee records while ensuring security, auditability, and compliance with validation rules.

## Features

### Core Functionality
- **Employee Data Management**: 
  - Full Name, Employee ID, Job Title, Department, Hire Date, Employment Status, Contact Information, and Address.
- **User Roles and Permissions**:
  - **HR Personnel**: Full CRUD operations on all employee data.
  - **Managers**: Limited updates to employee data within their department.
  - **Administrators**: Full access to all system features and configurations.
- **Audit Trail**: Logs all changes to employee records with timestamps and user information.
- **Search and Filtering**:
  - Search by name, ID, department, or job title.
  - Filter by employment status, department, or hire date.
- **Validation Rules**:
  - Ensure valid email formats.
  - Enforce unique employee IDs.
- **Reporting**:
  - Generate basic reports on employee data.

## Technical Details

### Backend
- **Language**: Java 17
- **Frameworks**: Spring Boot
- **Database**: Oracle SQL
- **API Documentation**: Swagger (OpenAPI)
- **Containerization**: Docker

### Frontend
- **Desktop Application**: Swing-based UI
  - **Layout Managers**: MigLayout and GridBagLayout

### Testing
- **Unit Tests**: JUnit
- **Integration Tests**: Mockito
- **API Testing**: Postman Collection

### Additional Requirements
- **Documentation**:
  - Maintain detailed documentation in Markdown files.
  - Include a backlog of tasks.
- **Demo**:
  - Provide a short video demonstrating the UI and key functionalities.

## Setup Instructions

### Prerequisites
- Docker installed
- Oracle SQL setup
- Java 17 installed

### Running the Application
1. Clone the repository:
   ```bash
   git clone https://github.com/bachiriy/employee-records-management.git
   ```
2. Navigate to the project directory:
   ```bash
   cd app
   ```
3. Build and run the Docker image:
   ```bash
   docker build -t employee-records .
   docker run -p 8080:8080 employee-records
   ```
4. Access the Swagger API documentation:
   - Open your browser and navigate to `http://localhost:8080/swagger-ui.html`

### Testing
1. Run unit tests:
   ```bash
   ./gradlew test
   ```
2. Validate API endpoints using the provided Postman Collection.

## Task Backlog
- [ ] Implement CRUD operations for Employee Data.
- [ ] Develop role-based access control.
- [ ] Design the Swing-based UI with MigLayout and GridBagLayout.
- [ ] Add validation rules for employee records.
- [ ] Implement audit trail functionality.
- [ ] Develop search and filtering features.
- [ ] Generate basic reports.
- [ ] Write unit and integration tests.
- [ ] Create Docker container for the application.
- [ ] Record demo video showcasing functionality.

## Demo Video
[will be added]

## Author
- Name: Mohammed El Bachiri
- Email: [mebashiry@gmail.com](mail:mebashiry@gmail.com) 

## License
[Specify your license here]

## Repository
[employee-records-management](https://github.com/bachiriy/employee-records-management)

