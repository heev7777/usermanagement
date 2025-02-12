# Reservation Management System

## Description
The Reservation Management System is a Spring Boot application designed to manage room reservations, customer information, payments, and notifications. It provides RESTful APIs for creating, updating, and retrieving reservations, rooms, customers, and payments.

## Features
- **Room Management**: Add, update, and check room availability.
- **Reservation Management**: Create, update, and cancel reservations.
- **Customer Management**: Add and retrieve customer information.
- **Payment Management**: Register payments and check payment status.
- **Notification System**: Send notifications for various events such as reservation confirmations and payment registrations.

## Technologies Used
- **Java**
- **Spring Boot**
- **Spring Data JPA**
- **H2 Database**
- **Maven**

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.4.3 or higher

### Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/heev7777/usermanagement.git
    ```

2. Build the project:
   ```sh
    mvn clean install
   ```
   
3. Run the application:
   ```sh
   mvn spring-boot:run
    ```
   
### Configuration

The application uses an in-memory H2 database by default. You can configure the database settings in the `src/main/resources/application.properties` file.

## API Endpoints

### Room Management
- **Check Room Availability**: `GET /rooms/availability`
- **Add Room**: `POST /rooms`
- **Get All Rooms**: `GET /rooms`
- **Get Room by ID**: `GET /rooms/{roomId}`

### Reservation Management
- **List Reservations**: `GET /reservations`
- **Add Reservation**: `POST /reservations`
- **Cancel Reservation**: `DELETE /reservations/{id}`
- **Check Availability**: `GET /reservations/availability`
- **Verify Reservation**: `GET /reservations/verify/{id}`
- **Update Reservation Status**: `PATCH /reservations/{id}/status`

### Customer Management
- **Add Customer**: `POST /customers`
- **Get All Customers**: `GET /customers`
- **Get Customer by ID**: `GET /customers/{id}`

### Payment Management
- **Register Payment**: `POST /payments`
- **List All Payments**: `GET /payments`
- **Check Payment Status**: `GET /payments/status/{reservationId}`
- **Calculate Total Amount**: `GET /payments/total/{reservationId}`

### Notification Management
- **List All Notifications**: `GET /notifications`
- **Get Notification by ID**: `GET /notifications/{id}`
- **Get Notifications by Type**: `GET /notifications/type/{type}`
- **Save Notification**: `POST /notifications`

## License
This project is licensed under the MIT License.