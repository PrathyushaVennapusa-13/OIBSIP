# ATM-Management-System
# ATM Management System

## Overview

The ATM Management System is a console-based Java application developed using Object-Oriented Programming (OOP) concepts and JDBC with PostgreSQL. The system allows authenticated users to perform common ATM operations such as depositing money, withdrawing money, checking account balance, transferring funds, and viewing transaction history.

This project was developed as part of the **Oasis Infobyte Java Development Internship (Task 3 – ATM Interface)**.

---

## Features

* Secure login using Account Number and PIN
* Three login attempts for authentication
* Deposit money into an account
* Withdraw money with balance validation
* Check account balance
* Transfer money between accounts
* View transaction history
* Input validation for invalid amounts
* Database connectivity using JDBC
* Transaction management using Commit and Rollback

---

## Technologies Used

* Java
* JDBC
* PostgreSQL
* Maven
* Eclipse IDE

---

## Project Structure

```
Atm_management
│
├── src
│   └── com.atm
│       ├── AtmMain.java
│       ├── AtmImplementation.java
│       ├── AtmHiding.java
│       ├── DatabaseConnection.java
│       ├── InvalidDataException.java
│       └── ...
│
├── pom.xml
└── README.md
```

---

## Database

### Account Table

Stores account information including:

* Account Number
* Account Holder Name
* PIN
* Phone Number
* Balance

### Transaction History Table

Stores transfer transaction details including:

* Transaction ID
* Sender Account
* Receiver Account
* Amount
* Transaction Time
* Transaction Status

---

## Functionalities

### Login

* User enters Account Number and PIN.
* Maximum of three login attempts.
* Successful login redirects to the ATM menu.

### Deposit

* Deposits money into the authenticated user's account.
* Rejects invalid deposit amounts.

### Withdraw

* Validates available balance before withdrawal.
* Displays an insufficient balance message if funds are unavailable.

### Check Balance

* Retrieves and displays the current account balance from the database.

### Transfer Amount

* Transfers money between accounts.
* Updates both sender and receiver balances.
* Stores successful transfers in the transaction history.
* Uses database transactions to maintain consistency.

### Transaction History

* Displays all transactions associated with the logged-in account.
* Shows sender account, receiver account, amount, transaction time, and transaction status.

---

## How to Run

1. Clone the repository.

```
git clone https://github.com/PrathyushaVennapusa-13/OIBSIP.git
```

2. Open the project in Eclipse.

3. Create the PostgreSQL database.

4. Update database credentials in `DatabaseConnection.java`.

5. Run `AtmMain.java`.

---

## Learning Outcomes

This project helped me gain practical experience in:

* Object-Oriented Programming
* JDBC
* PostgreSQL
* SQL Queries
* Transaction Management
* Exception Handling
* Java Collections
* Prepared Statements
* Console-Based Application Development

---

## Future Enhancements

* Admin module for account creation
* Password encryption
* Mini statement generation
* Change PIN functionality
* GUI implementation using Java Swing
* Email and SMS notifications

---

## Author

**Prathyusha Vennapusa**

Java Developer | Bioinformatics Student

GitHub:
https://github.com/PrathyushaVennapusa-13
