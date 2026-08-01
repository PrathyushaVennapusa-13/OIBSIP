# 📚 Digital Library Management System

A web-based **Digital Library Management System** developed using **Java Spring Boot, Spring Data JPA, PostgreSQL, Thymeleaf, HTML, and CSS**.

The application provides an online platform to manage books, users, borrow requests, reservations, returns, fines, and communication between users and administrators.

---

## 🚀 Features

## 👤 User Module

### User Authentication
- User registration and login.
- User dashboard.
- Session-based user management.

### Browse Books
Users can view available books with details:

- Book ID
- Title
- Author
- Category
- ISBN
- Total Quantity
- Available Quantity

### Borrow Request System
- Users can send borrow requests for available books.
- Duplicate pending requests are restricted.
- Users can track request status.

Reservation Status:
PENDING
APPROVED
REJECTED

### My Borrowed Books
Users can view borrowed book details:

- Borrow ID
- Book ID
- Issue Date
- Due Date
- Return Date
- Status
- Fine Amount

### Return Request
- Users can request book return.
- Admin verifies and approves the return.

### User Profile Management
Users can update:

- Name
- Email
- Phone Number
- Password

### Contact Library
Users can send queries/messages to the library administration.

---

# 👨‍💼 Admin Module

## Admin Dashboard
Admin can manage complete library operations.

## Book Management

Admin can:

- Add new books.
- View books.
- Maintain book details.
- Manage book quantity.

Book information includes:

- Title
- Author
- ISBN
- Category
- Total Quantity
- Available Quantity

---

## Reservation Management

Admin can:

- View user borrow requests.
- Approve requests.
- Reject requests.

### Borrow Approval Workflow

When admin approves a reservation:

1. Reservation status changes:
PENDING → APPROVED

2. A new borrow record is created automatically.

3. Available quantity of the book decreases.

Example:

Before approval:
Total Quantity: 5
Available Quantity: 5

After approval:
Total Quantity: 5
Available Quantity: 4


---

## Borrow Management

Admin can:

- View all borrow records.
- Monitor issued books.
- Handle return requests.

---

## Return Approval and Fine Calculation

Return workflow:
ISSUED
|
↓
RETURN_REQUESTED
|
↓
RETURNED


When admin approves return:
- Return date is updated.
- Fine is calculated.
- Book quantity is increased.

Fine calculation:
Fine = Number of Late Days × ₹5
---

## Contact Management

Admin can:
- View user messages.
- Read message details.
- Delete messages.

---

# 🛠️ Technologies Used
## Backend
- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
## Frontend
- HTML5
- CSS3
- Thymeleaf

## Database
- PostgreSQL
## Tools
- Spring Tool Suite
- PostgreSQL
- Git/GitHub

---

# 🏗️ Project Architecture

The project follows layered architecture:
com.lms

│
├── controller
│ Handles HTTP requests
│
├── service
│ Contains business logic
│
├── serviceImpl
│ Implements service interfaces
│
├── repository
│ Database operations using JPA
│
├── entity
│ Database entities
│
├── dto
│ Data Transfer Objects
│
├── exception
│ Custom exception handling
│
└── util
Enums and utility classes


---

# 🗄️ Database Entities

## User Entity

Stores user information.

Fields:

- userId
- userName
- email
- password
- phoneNumber

---

## Book Entity

Stores library book details.

Fields:

- bookId
- title
- author
- ISBN
- category
- totalQuantity
- availableQuantity

---

## Reservation Entity

Stores user borrow requests.

Fields:

- reservationId
- reservationDate
- status
- user
- book

Status:
PENDING
APPROVED
REJECTED

---

## Borrow Entity

Stores issued book records.

Fields:

- borrowId
- issueDate
- dueDate
- returnDate
- status
- fineAmount
- user
- book

Status:
ISSUED
RETURN_REQUESTED
RETURNED


---

## Contact Entity

Stores user communication.

Fields:

- contactId
- subject
- message
- user

---

# 🔄 Application Workflow

## Book Borrow Workflow
User Login
  ↓
Send Borrow Request
  ↓
Reservation Created
  ↓
Admin Reviews Request
  ↓
Approve / Reject
  ↓
If Approved:
Borrow Record Created
  ↓
Available Quantity Updated
---

## Return Workflow
User Requests Return
    ↓
Admin Reviews Return
    ↓
Fine Calculation
    ↓
Borrow Status Updated         
---

# 📌 Future Enhancements

- Search books by title, author, and category.
- Email notifications.
- Spring Security based authentication.
- Book recommendation system.
- Library analytics dashboard.
- Borrow history reports.

---

# 👩‍💻 Author

**Prathyusha**

Digital Library Management System

Built using Spring Boot + PostgreSQL
