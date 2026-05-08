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

Contributors:
Alcazar, Kyle D.
Aliposa, Francis Jetan V.


# E-Commerce API - Lab 8 Submission

## Database Schema
- *categories*: id (PK), name
- *products*: id (PK), name, description, price, stockQuantity, imageUrl, category_id (FK)
- *Relationship*: One-to-Many (1 Category → Many Products)
  - Uses @OneToMany, @ManyToOne, CascadeType.ALL, FetchType.LAZY

## API Endpoints
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/products | Get all products |
| GET | /api/products/{id} | Get single product by ID |
| POST | /api/products | Add new product |
| PUT | /api/products/{id} | Update product |
| DELETE | /api/products/{id} | Delete product |
| GET | /api/products/category/{name} | Filter by category |
| GET | /api/products/price?min=X&max=Y | Filter by price range |

## Screenshots
1. *MySQL Workbench* – Shows populated categories and products tables
2. *Browser Console* – Shows successful fetch response and no errors
3. *Postman* – Verifies all endpoints work and data persists after restart
(HI SIR, SORRY SIR WARY PO AK PAKA SS SA MGA  NEED IG SS DRI PO KASI NAGANA AK SCREENSHOT DIDI SA LAPTOP.)

## Tech Stack
- Spring Boot, Spring Data JPA / Hibernate
- MySQL
- HTML, CSS, JavaScript (Fetch API)
- Gradle

## Code Quality
- All JPA entities have Javadoc comments
- JavaScript functions include try/catch error handling with explanations

## Git Workflow
- Created branch: feat:db-integration
- Committed changes at each task checkpoint
- Merged feat:db-integration into main
- Feature branch kept (not deleted as required)