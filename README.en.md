# PetServices

This is a pet service management system. The backend is developed using the Spring Boot framework and integrates MyBatis-Plus to simplify database operations. The system implements various functions including user management, service management, order management, product management, and appointment management.

## Features

- **User Management**: Supports user registration, login, profile modification, password reset, and more.
- **Service Management**: Provides functions to add, delete, update, and query service information.
- **Order Management**: Supports creating, querying, and updating order statuses.
- **Product Management**: Implements product information maintenance and querying.
- **Appointment Management**: Supports creating appointments, updating statuses, and adding reviews.
- **Admin Features**: Includes admin login, admin information maintenance, and system data statistics.
- **File Upload**: Supports image uploads for scenarios such as updating user avatars.
- **Data Statistics**: Provides visual statistical information on system operation data.

## Module Structure

- **Controller Layer**: Handles HTTP requests and provides RESTful API endpoints.
- **Service Layer**: Implements business logic and calls the Mapper layer for data operations.
- **Mapper Layer**: Uses MyBatis-Plus for database operations.
- **Entity Layer**: Defines database entity classes that correspond to database table structures.
- **DTO Layer**: Data Transfer Objects used to encapsulate data transmitted through interfaces.
- **Configuration Classes**: Includes configurations for MyBatis-Plus, Web MVC, and global exception handling.

## Technology Stack

- **Backend Framework**: Spring Boot
- **Database**: MyBatis-Plus
- **Database Table Mapping**: MyBatis Plus annotation approach
- **Exception Handling**: Global exception handler `CustomException`
- **Response Wrapping**: Uses `JsonResult` for a unified return format
- **Interceptors**: Implements uniform wrapping of response results
- **Utility Classes**: Includes HTTP utilities, file upload utilities, and more

## Installation and Running

1. Ensure JDK 1.8 or later is installed.
2. Install and configure MySQL database.
3. Import the project into an IDE (e.g., IntelliJ IDEA).
4. Modify the database configuration in `application.yml`.
5. Run the `main` method in the `PetServicesApplication` class to start the project.

## Usage Instructions

- **User Module**: Implements user login, registration, and profile updates via `UserController`.
- **Service Module**: Maintains service information via `ServeController`.
- **Order Module**: Manages the order lifecycle via `OrderController`.
- **Product Module**: Manages product information via `GoodsController`.
- **Appointment Module**: Handles appointment-related business via `BookingController`.
- **Admin Module**: Performs system management operations via `AdminController`.

## API Documentation

All APIs return JSON data in a unified format structured as follows:

```json
{
  "success": true,
  "result": {},
  "msg": "",
  "resultCode": 200
}
```

- `success`: Indicates whether the request was successful.
- `result`: The returned data object.
- `msg`: Error message if the request fails.
- `resultCode`: Result code of the request.

## Contribution Guide

Code contributions are welcome. Please follow these steps:

1. Fork the project.
2. Create a new branch (`git checkout -b feature/new-feature`).
3. Commit your changes (`git commit -am 'Add some feature'`).
4. Push to the branch (`git push origin feature/new-feature`).
5. Create a Pull Request.

## License

This project uses the MIT License. For details, please refer to the LICENSE file in the project.