# JDBC User Management System

A console-based Java JDBC application for managing users using MySQL database.

This project demonstrates CRUD operations, JDBC connectivity, PreparedStatement usage, and custom exception handling in Java.

---

# Features

- Add User
- Delete User
- Update User
- Find User By ID
- Dynamic Database Connection
- Custom Exception Handling
- PreparedStatement Usage
- MySQL Database Connectivity
- Console-Based Menu System

---

# Technologies Used

- Java
- JDBC
- MySQL
- IntelliJ IDEA
- Git & GitHub

---

# Concepts Used

- JDBC Architecture
- CRUD Operations
- PreparedStatement
- Exception Handling
- Custom Exceptions
- SQL Queries
- ResultSet Handling
- Database Connectivity

---

# Project Structure

```text
JDBC User Management System/
│
├── src/
│   └── ExtendedProject/
│       ├── ExtendedTask.java
│       ├── UserAlreadyExistsException.java
│       └── UserNotFoundException.java
│
├── lib/
│   └── mysql-connector-j-8.3.0.jar
│
├── database.sql
├── README.md
└── .gitignore
```

---

# Database Setup

## Step 1: Create Database

```sql
CREATE DATABASE Test;
```

---

## Step 2: Use Database

```sql
USE Test;
```

---

## Step 3: Create Users Table

```sql
CREATE TABLE users(
    id INT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100)
);
```

---

# How to Run Project

## 1. Clone Repository

```bash
git clone https://github.com/irishavv/jdbc-user-management-system.git
```

---

## 2. Open Project in IntelliJ IDEA

Import the project into IntelliJ IDEA.

---

## 3. Add MySQL JDBC Driver

Add:

```text
mysql-connector-j-8.3.0.jar
```

to project libraries.

---

## 4. Start MySQL Server

Ensure MySQL server is running.

---

## 5. Run SQL Script

Execute:

```text
database.sql
```

inside MySQL.

---

## 6. Run Java File

Run:

```text
ExtendedTask.java
```

---

# Sample Menu

```text
===== Choose Operation =====

1. Add User
2. Remove User By ID
3. Update User
4. Find User By ID
```

---

# Sample Output

```text
Connection Established

===== Choose Operation =====
1. Add User
2. Remove User By ID
3. Update User
4. Find User By ID

Enter Choice: 1

Enter ID: 101
Enter Name: Rishav
Enter Email: rishav@gmail.com
Enter Password: 1234

User Added Successfully
```

---

# Future Improvements

- Password Encryption
- Login Authentication
- Maven Integration
- Layered Architecture
- GUI Version
- Connection Pooling
- Logging Framework

---

# Advantages

- Beginner-Friendly JDBC Project
- Demonstrates Real Database Operations
- Uses PreparedStatement to Prevent SQL Injection
- Includes Custom Exception Handling
- Resume Ready Project

---

# Limitations

- Console-Based Application
- No Password Encryption
- No GUI
- Single Table Project
- No Framework Used

---

# Author

Rishav Kumar

GitHub:
https://github.com/irishavv
