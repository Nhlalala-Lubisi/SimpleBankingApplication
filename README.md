# Simple Banking Application

A console-based Java banking system built Java (Maven).

## Overview

Users can create accounts, deposit funds, withdraw funds, and check balances through an interactive console menu. All monetary values use `BigDecimal` for financial precision.

## Project Structure

```
SimpleBankingApplication/
├── src/
│   ├── main/java/com/nhlaks/banking/
│   │   ├── BankAccount.java          # Domain model
│   │   ├── BankingService.java       # Service layer (multi-account management)
│   │   ├── InsufficientFundsException.java
│   │   └── Main.java                 # Console UI entry point
│   └── test/java/com/nhlaks/banking/
│       ├── BankAccountTest.java      # 13 unit tests
│       └── BankingServiceTest.java   # 10 unit tests
└── pom.xml
```

## Requirements

| Tool | Version |
|------|---------|
| JDK  | 21      |
| Maven| 3.9+    |

## Build & Run

```bash
# Compile and run all tests
mvn clean test

# Run the application
mvn compile exec:java -Dexec.mainClass="com.nhlaks.banking.Main"

# Or build a JAR first
mvn package
java -jar target/SimpleBankingApplication-1.0-SNAPSHOT.jar
```

## Features

- Create accounts with optional opening balance
- Deposit and withdraw with input validation
- `InsufficientFundsException` on overdraft attempts
- Two-decimal-place balance precision via `BigDecimal`
- 23 JUnit 5 tests covering happy paths and edge cases

## Author

Nhlalala — Advanced Diploma in IT, VUT 2026
