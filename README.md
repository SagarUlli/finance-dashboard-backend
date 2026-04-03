# 💰 Finance Dashboard Backend

## 📌 Overview

This project is a backend system for a finance dashboard that allows users to manage financial transactions and view aggregated insights based on their role.

The system demonstrates clean backend architecture, role-based access control, data processing, and API design.

---

## 🧰 Tech Stack

* Java 17
* Spring Boot
* Spring Data JPA
* H2 Database (in-memory)
* Maven
* Lombok
* Jakarta Validation

---

## 🏗️ Architecture

The project follows a layered architecture:

```
Controller → Service → Repository → Database
```

### Key Design Choices

* DTO pattern used to separate API layer from persistence
* Role-based access control implemented in service layer
* Clean separation of concerns for maintainability
* Query-based filtering for flexible data retrieval
* **Database Portability:** Replaced MySQL-specific `DATE_FORMAT` with database-agnostic logic using `YEAR()` and `MONTH()` to ensure compatibility with H2 and other SQL systems.

---

## 👥 Roles & Access Control

| Role    | Permissions                                |
| ------- | ------------------------------------------ |
| VIEWER  | View dashboard only                        |
| ANALYST | View transactions + dashboard               |
| ADMIN   | Full access (create, update, delete, view) |

---

## ⚙️ Setup Instructions

### 1. Clone Repository

```bash
git clone https://github.com/your-username/finance-dashboard-backend.git
cd finance-dashboard-backend
```

### 2. Run Application

```bash
mvn spring-boot:run
```

**Note:** On startup, a `DataInitializer` class automatically seeds default users (ADMIN and ANALYST) so the system is immediately usable.

### 3. Access H2 Console (Optional)

```
http://localhost:8080/h2-console
```

---

## 🔐 Authentication Approach

This project uses a simplified header-based approach for user context, configured via `SecurityConfig` for easier development and testing:

```
X-USER-ID: 1
X-ROLE: ADMIN
```

This was chosen to focus on backend logic instead of authentication complexity.

---

## 📡 API Endpoints

### 🔹 Transactions

#### Create Transaction (ADMIN)
`POST /transactions`

#### Get Transactions (ANALYST, ADMIN)
`GET /transactions`

#### Filter Transactions (Pagination Supported)
`GET /transactions/filter?page=0&size=10&type=EXPENSE`

#### Update Transaction (ADMIN)
`PUT /transactions/{id}`

#### Delete Transaction (ADMIN)
`DELETE /transactions/{id}`

---

### 🔹 Dashboard

#### Summary
`GET /dashboard/summary`

#### Category-wise Breakdown
`GET /dashboard/category-wise`

#### Monthly Trends
`GET /dashboard/trends`

#### Recent Transactions
`GET /dashboard/recent`

---

## 📊 Features Implemented

* ✅ Transaction CRUD operations
* ✅ Filtering & pagination
* ✅ Role-based access control
* ✅ Dashboard analytics (summary, trends, category-wise)
* ✅ Input validation
* ✅ Global exception handling
* ✅ Clean layered architecture
* ✅ **User Data Initialization:** Automated seeding of default roles.
* ✅ **H2 Compatibility:** Optimized SQL logic for monthly trends.

---

## 🧠 Assumptions

* Each transaction belongs to a single user
* Categories are stored as plain text
* Authentication is mocked via headers
* No multi-tenant or shared data model

---

## ⚖️ Trade-offs

* Used H2 database for simplicity instead of PostgreSQL
* Skipped JWT authentication to keep focus on core backend logic
* Used service-layer mapping instead of JPA projections for clarity

---

## 🚀 Future Improvements

* JWT-based authentication
* Pagination metadata enhancement
* Swagger/OpenAPI documentation
* Unit and integration tests
* Category normalization

---

## 📌 Conclusion

This project focuses on demonstrating backend engineering fundamentals: clean architecture, proper data handling, and role-based logic. Recent updates have streamlined the startup process and ensured the dashboard analytics are fully functional in an H2 environment.
