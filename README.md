
# Zomind Test Case Management System

## Overview

This project is a Test Case Management System built using **Spring Boot** and **Java**, designed to handle CRUD operations for test cases. It allows users to create, retrieve, update, delete, and filter test cases based on their `Status` and `Priority`. The application adheres to clean code principles, robust design patterns, and follows RESTful API standards.

---

## Features

1. **CRUD Operations**:
   - Create, Read, Update, and Delete test cases.

2. **Filtering**:
   - Filter test cases based on `Status` (e.g., PENDING, PASSED, FAILED) and `Priority` (e.g., LOW, MEDIUM, HIGH).

3. **Validation**:
   - Input validation for test case attributes using `@Valid` annotations.
   - Enum validation using custom annotation `@EnumConstraint`.

4. **Pagination**:
   - Support for paginated results using `Spring Data Pageable`.

5. **Exception Handling**:
   - Robust exception handling with meaningful error messages.

6. **Clean Code and Design Patterns**:
   - Incorporates patterns like Service Layer, Builder.

7. **Extensibility**:
   - Highly modular and scalable architecture.
     
8. **Model Mapping**: Using `ModelMapper` for efficient DTO and entity mapping.


---

## Technologies Used

- **Spring Boot**: Backend framework for building the application.
- **Spring Data JPA**: For database interaction.
- **ModelMapper**: For mapping DTOs and entities.
- **Jakarta Validation**: For input validation.
- **Lombok**: To reduce boilerplate code.
- **MongoDB Database**: database for Storing purposes.
- **Maven**: Dependency management.

---


## Installation and Setup

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Aditya-Tanawade/Zomind-Assignment.git
   ```

2. **Navigate to Project Directory**:
   ```bash
   cd ZomindTestCaseSystem
   ```

3. **Build the Project**:
   ```bash
   mvn clean install
   ```

4. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```

5. **Access the Application**:
   - Open your Postman and navigate to: `http://localhost:8080/api/testcases`

---

## API Endpoints

### 1. Test Case Endpoints

#### a) Retrieve All Test Cases
- **Endpoint**: `/api/testcases/all`
- **Method**: GET
- **Response**: List of all test cases.
  
  **Response Example**:
   ```json
   [
       {
           "id": "1",
           "title": "Test Case 1",
           "description": "Description for test case 1",
           "status": "PENDING",
           "priority": "HIGH",
           "createdAt": "2025-03-20T10:00:00",
           "updatedAt": "2025-03-20T12:00:00"
       }
   ]
   ```

#### b) Retrieve Filtered Test Cases
- **Endpoint**: `/api/testcases`
- **Method**: GET
- **Query Parameters**:
  - `status` (optional): PENDING, IN_PROGRESS, PASSED, FAILED
  - `priority` (optional): LOW, MEDIUM, HIGH
  - Pagination params: `page`, `size`, `sort`.
- **Response**: Paginated list of test cases.
  
  **Response Example**:
   ```json
   {
       "content": [
           {
               "id": "1",
               "title": "Test Case 1",
               "description": "Description for test case 1",
               "status": "PENDING",
               "priority": "HIGH",
               "createdAt": "2025-03-20T10:00:00",
               "updatedAt": "2025-03-20T12:00:00"
           }
       ],
       "pageable": { ... },
       "totalElements": 1,
       "totalPages": 1
   }
   ```

#### c) Retrieve Test Case by ID
- **Endpoint**: `/api/testcases/{id}`
- **Method**: GET
- **Response**: Details of the test case with the given ID.
  
  **Response Example**:
   ```json
   {
       "id": "1",
       "title": "Test Case 1",
       "description": "Description for test case 1",
       "status": "PENDING",
       "priority": "HIGH",
       "createdAt": "2025-03-20T10:00:00",
       "updatedAt": "2025-03-20T12:00:00"
   }
   ```

#### d) Create a New Test Case
- **Endpoint**: `/api/testcases`
- **Method**: POST
**Request Body**:
   ```json
   {
       "title": "New Test Case",
       "description": "Description for new test case",
       "status": "IN_PROGRESS",
       "priority": "MEDIUM"
   }
   ```

   **Response Example**:
   ```json
   {
       "id": "2",
       "title": "New Test Case",
       "description": "Description for new test case",
       "status": "IN_PROGRESS",
       "priority": "MEDIUM",
       "createdAt": "2025-03-24T10:00:00",
       "updatedAt": "2025-03-24T10:00:00"
   }
   ```

#### e) Update an Existing Test Case
- **Endpoint**: `/api/testcases/{id}`
- **Method**: PUT
- **Request Body**: Same as Create request body.
- **Response**: The updated test case.

 **Request Body**:
   ```json
   {
       "title": "Updated Test Case",
       "description": "Updated description",
       "status": "PASSED",
       "priority": "HIGH"
   }
   ```

**Response Example**:
   ```json
   {
       "id": "2",
       "title": "Updated Test Case",
       "description": "Updated description",
       "status": "PASSED",
       "priority": "HIGH",
       "createdAt": "2025-03-24T10:00:00",
       "updatedAt": "2025-03-24T12:00:00"
   }
   ```

#### f) Delete a Test Case
- **Endpoint**: `/api/testcases/{id}`
- **Method**: DELETE
- **Response**: Success message.

**Response Example**:
   ```json
   {
       "message": "TestCase Deleted Successfully"
   }
   ```
---



## Database Schema

### TestCaseEntity

| Field       | Type         | Description              |
|-------------|--------------|--------------------------|
| `id`        | String         | Primary key.             |
| `title`     | String       | Title of the test case.  |
| `description` | String     | Test case description.   |
| `status`    | Enum (Status)| Test case status.        |
| `priority`  | Enum (Priority) | Test case priority.  |
| `createdAt` | LocalDateTime | Timestamp when created. |
| `updatedAt` | LocalDateTime | Timestamp when updated. |

---

## Validation Rules

1. **Title**: 
   - Mandatory, 3-20 characters.
2. **Status**: 
   - Enum values: `PENDING`, `IN_PROGRESS`, `PASSED`, `FAILED`.
3. **Priority**:
   - Enum values: `LOW`, `MEDIUM`, `HIGH`.

---

## Testing

- Unit tests and integration tests are implemented using **JUnit 5** and **Mockito**.
- To run tests:
  ```bash
  mvn test
  ```

---

## Design Patterns Used

1. **Service Layer**:
   - Separates business logic from the controller and repository.

3. **Builder Pattern**:
   - Simplifies object creation for entities and DTOs.

4. **Strategy Pattern**:
   - Flexible filtering logic for test cases.

---

## Error Handling

- Proper error responses are provided for the following scenarios:
  - Resource not found (`404`).
  - Validation errors (`400`).
  - Invalid payloads (`400`).

Example error response for invalid enum:
```json
{
  "error": "Invalid value for enum",
  "invalidValue": "INVALID",
  "validValues": "PENDING, IN_PROGRESS, PASSED, FAILED"
}
```

---

## Contribution

Feel free to fork the repository and create pull requests for enhancements or bug fixes. Contributions are welcome!

---

## License

This project is licensed under the MIT License. See the LICENSE file for details.

---

