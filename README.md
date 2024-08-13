# Library-RestAPI-SpringBoot

This project is a Library Management System REST API built using Spring Boot. It demonstrates a simple library system with user management, book handling, and borrowing operations.

## Features

- User registration and management
- Book management
- Borrowing and returning books
- Fine calculation for late returns

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- MySQL Database
- Maven

## Project Structure

The project follows a standard Spring Boot application structure:

- `src/main/java/org/jsp/libraryrestapi`: Contains the Java source code
  - `controller`: REST API endpoints
  - `dao`: Data Access Objects for database operations
  - `dto`: Data Transfer Objects (User, Book, BookBorrow)
  - `exception`: Custom exception handling
  - `repository`: JPA repository interfaces
  - `service`: Business logic layer

## Setup and Installation

1. Clone the repository:

2. Navigate to the project directory:

3. Update the `application.properties` file in `src/main/resources` with your MySQL database configuration.

4. Build the project:

5. Run the application:

The application will start running at `http://localhost:8080`.

## API Endpoints

### User Management
- POST `/users`: Register a new user
- POST `/users/verify-otp`: Verify user OTP
- POST `/users/login`: User login
- GET `/users`: Get all users (admin only)
- GET `/users/{id}`: Get user by ID
- PUT `/users/{id}`: Update user
- DELETE `/users/{id}`: Delete user

### Book Management
- POST `/books`: Add a new book
- GET `/books`: Get all books
- GET `/books/{id}`: Get book by ID
- PUT `/books/{id}`: Update book
- DELETE `/books/{id}`: Delete book

### Borrowing Operations
- POST `/borrows/{userId}/{bookId}`: Borrow a book
- PUT `/borrows/return/{userId}/{bookId}`: Return a book
- GET `/borrows/{userId}`: Get borrowed books by user ID

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is open source and available under the [MIT License](LICENSE).
