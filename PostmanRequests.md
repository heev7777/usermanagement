## Customers

### Create a Customer

**POST /customers**

*   **Description:** Adds a new customer.
*   **Request Body:**
    ```json
    {
        "name": "Random Person"
    }
    ```
*   **Response:**
    ```json
    {
        "id": 1,
        "name": "Random Person",
        "reservations": []
    }
    ```
    (Returns the created customer object with an ID.)
*   **Status Code:** 201 Created

### Get All Customers

**GET /customers**

*   **Description:** Retrieves a list of all customers.
*   **Request Body:** None
  *   **Response:**
      ```json
      {
          "id": 1,
          "name": "Random Person",
          "reservations": [
              {
                  "id": 1,
                  "room": {
                      "roomId": "101",
                      "roomNumber": null,
                      "roomType": null,
                      "pricePerNight": 0.0
                  },
                  "checkInDate": "2025-12-01",
                  "checkOutDate": "2025-12-10",
                  "status": "PAID"
              }
          ]
      }
      ```
* **Status Code:** 200 OK

### Get Customer by ID

**GET /customers/{id}**

*   **Description:** Retrieves a specific customer by their ID.
*   **Request Body:** None
*   **URL Parameters:**
    *   `id`:  The ID of the customer (e.g., `1`).
*   **Example:**  `GET /customers/1`
*   **Response:**
    ```json
    {
        "id": 1,
        "name": "Random Person"
    }
    ```
*   **Status Codes:**
    *   200 OK (if the customer is found)
    *   404 Not Found (if no customer with the given ID exists)

## Rooms

### Add a Room

**POST /rooms**

*   **Description:** Adds a new room.
*   **Request Body:**
    ```json
    {
        "roomNumber": "101",
        "roomType": "Single",
        "pricePerNight": 100.00
    }
    ```
*   **Response:**
    ```json
    {
        "roomId": "a1b2c3d4-e5f6-7890-1234-567890abcdef",
        "roomNumber": "101",
        "roomType": "Single",
        "pricePerNight": 100.00
    }
    ```
*   **Status Code:** 201 Created

### Get All Rooms

**GET /rooms**

*   **Description:** Retrieves a list of all rooms.
*   **Request Body:** None
*   **Response:**
    ```json
    [
        {
            "roomId": "a1b2c3d4-e5f6-7890-1234-567890abcdef",
            "roomNumber": "101",
            "roomType": "Single",
            "pricePerNight": 100.00
        },
        {
            "roomId": "f1e2d3c4-b5a6-0987-6543-210987fedcba",
            "roomNumber": "202",
            "roomType": "Double",
            "pricePerNight": 150.00
        }
    ]
    ```
* **Status Code:** 200 OK

### Get Room by ID

**GET /rooms/{roomId}**

*   **Description:** Retrieves a specific room by its ID.
*   **Request Body:** None
* **URL Parameters:**
    *  `roomId`: The ID of the room.
*   **Example:** `GET /rooms/a1b2c3d4-e5f6-7890-1234-567890abcdef`
*   **Response:**
    ```json
    {
        "roomId": "a1b2c3d4-e5f6-7890-1234-567890abcdef",
        "roomNumber": "101",
        "roomType": "Single",
        "pricePerNight": 100.00
    }
    ```
*   **Status Codes:**
    *   200 OK
    *   404 Not Found

### Check Room Availability

**GET /rooms/availability**

*   **Description:** Checks if a room is available for a given date range.
*   **Request Body:** None
*   **Query Parameters:**
    *   `roomId`: The ID of the room.
    *   `startDate`: The start date (ISO-8601 format, e.g., `2024-03-15`).
    *   `endDate`: The end date (ISO-8601 format, e.g., `2024-03-20`).
*   **Example:** `GET /rooms/availability?roomId=a1b2c3d4-e5f6-7890-1234-567890abcdef&startDate=2024-03-15&endDate=2024-03-20`
*   **Response:**
    ```json
    true
    ```
    or
    ```json
    false
    ```
*   **Status Code:** 200 OK

## Reservations

### Create a Reservation

**POST /reservations**

*   **Description:** Creates a new reservation.
*   **Request Body:**
    ```json
    {
        "customer": {
            "id": 1
        },
        "room": {
            "roomId": "a1b2c3d4-e5f6-7890-1234-567890abcdef"
        },
        "checkInDate": "2024-03-15",
        "checkOutDate": "2024-03-20",
        "status": "PENDING"
    }
    ```
*   **Response:**
    ```json
    {
        "id": 1,
        "customer": {
            "id": 1,
            "name": "Random Person"
        },
        "room": {
            "roomId": "a1b2c3d4-e5f6-7890-1234-567890abcdef",
            "roomNumber": "101",
            "roomType": "Single",
            "pricePerNight": 100.0
        },
        "checkInDate": "2024-03-15",
        "checkOutDate": "2024-03-20",
        "status": "PENDING"
    }
    ```
*   **Status Code:** 200 OK

### List All Reservations

**GET /reservations**

*   **Description:** Retrieves a list of all reservations.
*   **Request Body:** None
*   **Response:**
    ```json
    [
        {
            "id": 1,
             "customer": {
                "id": 1,
                "name": "Random Person"
            },
            "room": {
                "roomId": "a1b2c3d4-e5f6-7890-1234-567890abcdef",
                "roomNumber": "101",
                "roomType": "Single",
                "pricePerNight": 100.0
            },
            "checkInDate": "2024-03-15",
            "checkOutDate": "2024-03-20",
            "status": "PENDING"
        },
        {
            "id": 2,
            "customer": {
                "id": 2,
                "name": "Jane Smith"
            },
            "room": {
                "roomId": "f1e2d3c4-b5a6-0987-6543-210987fedcba",
                "roomNumber": "202",
                "roomType": "Double",
                "pricePerNight": 150.0
            },
            "checkInDate": "2024-03-22",
            "checkOutDate": "2024-03-25",
            "status": "CONFIRMED"
        }
    ]
    ```
* **Status Code:** 200 OK

### Cancel Reservation

**DELETE /reservations/{id}**

*   **Description:** Cancels a reservation.
*   **Request Body:** None
*  **URL Parameters:**
    * `id`: The ID of the reservation to cancel.
*   **Example:** `DELETE /reservations/1`
*   **Response:**
    ```
    Reservation canceled
    ```
*   **Status Code:** 200 OK

### Check Reservation Availability

**GET /reservations/availability**

*   **Description:** Checks for reservations within a given date range (useful for finding conflicting reservations).
*   **Request Body:** None
*   **Query Parameters:**
    *   `start`: The start date (ISO-8601 format).
    *   `end`: The end date (ISO-8601 format).
*   **Example:** `GET /reservations/availability?start=2024-03-15&end=2024-03-20`
*   **Response:** (Returns a list of reservations that overlap with the given date range)
    ```json
    [
       {
            "id": 1,
             "customer": {
                "id": 1,
                "name": "Random Person"
            },
            "room": {
                "roomId": "a1b2c3d4-e5f6-7890-1234-567890abcdef",
                "roomNumber": "101",
                "roomType": "Single",
                "pricePerNight": 100.0
            },
            "checkInDate": "2024-03-15",
            "checkOutDate": "2024-03-20",
            "status": "PENDING"
        }
    ]
    ```
* **Status Code:** 200 OK

### Verify Reservation

**GET /reservations/verify/{id}**

*   **Description:** Checks if a reservation with the given ID exists.
*   **Request Body:** None
*   **URL Parameters:**
    *  `id`: The ID of the reservation.
*   **Example:** `GET /reservations/verify/1`
*   **Response:**
    ```json
    true
    ```
    or
    ```json
    false
    ```
*   **Status Code:** 200 OK

### Update Reservation Status

**PATCH /reservations/{id}/status**

*   **Description:** Updates the status of a reservation.
* **Request Body:**
    ```json
    {
        "status": "CONFIRMED"
    }
    ```
*   **URL Parameters:**
    *  `id`: The ID of the reservation.
* **Example**  `PATCH /reservations/1/status`
*   **Response:**
    ```
    Reservation status updated
    ```
*   **Status Code:** 200 OK

## Payments

### Register Payment

**POST /payments**

*   **Description:** Registers a payment for a reservation.
*   **Request Body:** None
*   **Query Parameters:**
    *   `reservationId`: The ID of the reservation.
    *   `amount`: The amount paid.
*   **Example:** `POST /payments?reservationId=1&amount=100.00`
*   **Response:** 
    ```json
    {
        "id": 1,
        "reservationId": 1,
        "amount": 100.00,
        "paid": true
    }
    ```
*   **Status Code:** 200 OK

### List All Payments

**GET /payments**

*   **Description:** Retrieves a list of all payments.
*   **Request Body:** None
*   **Response:**
    ```json
    [
        {
            "id": 1,
            "reservationId": 1,
            "amount": 100.00,
            "paid": true
        },
        {
            "id": 2,
            "reservationId": 2,
            "amount": 150.00,
            "paid": true
        }
    ]
    ```
* **Status Code:** 200 OK

### Check Payment Status

**GET /payments/status/{reservationId}**

*   **Description:** Checks if a reservation has been paid.
*   **Request Body:** None
*   **URL Parameters:**
    *    `reservationId`: The ID of the reservation.
* **Example:** `GET /payments/status/1`
*   **Response:**
    ```json
    true
    ```
    or
    ```json
    false
    ```
*   **Status Code:** 200 OK

### Calculate Total Amount

**GET /payments/total/{reservationId}**

*  **Description:** Calculates the total amount paid for a reservation.
*   **Request Body:** None
*  **URL Parameters:**
   * `reservationId`: The ID of the reservation.
* **Example**: `GET /payments/total/1`
*   **Response:**
    ```json
    100.00
    ```
*   **Status Code:** 200 OK