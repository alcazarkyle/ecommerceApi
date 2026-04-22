# Ecommerce API Project

## Overview
This is a simple REST API built using Spring Boot for managing products. It supports basic CRUD operations, input validation, and error handling. Data is stored temporarily in memory.

---

## Setup Instructions
1.  Make sure you have Java 17 installed.
2.  Open the project in VS Code.
3.  Run the application using ./gradlew bootRun or via the Run button.
4.  The server will start on http://localhost:8080.

---

## API Endpoints

### 1. Create Product
- *Method:* POST
- *Path:* /api/v1/products
- *Description:* Adds a new product to the database.
- *Request Body:*
```json
{
  "name": "Sample Product",
  "description": "Product Description",
  "price": 100.00,
  "category": "Electronics",
  "stockQuantity": 50,
  "imageUrl": "url.jpg"
}
• Response: 201 Created
2. Get All Products

• Method: GET

• Path: /api/v1/products

• Description: Retrieves all products.
3. Get Product by ID

• Method: GET

• Path: /api/v1/products/{id}

• Description: Retrieves a single product by its ID.
4. Update Product

• Method: PUT

• Path: /api/v1/products/{id}

• Description: Updates an existing product.

• Request Body:
{
  "name": "Updated Product",
  "description": "Updated Description",
  "price": 150.00,
  "category": "Updated Category",
  "stockQuantity": 100,
  "imageUrl": "updated.jpg"
}
• Response: 200 OK
5. Delete Product

• Method: DELETE

• Path: /api/v1/products/{id}

• Description: Deletes a product.

• Response: 204 No Content
Validation Rules

• Name: Required, at least 2 characters.

• Description: Required.

• Price: Must be greater than 0.

• Category: Required.

• Stock Quantity: Cannot be negative.
Known Limitations

• Uses in-memory storage (data resets when server stops).

• No database connection yet.