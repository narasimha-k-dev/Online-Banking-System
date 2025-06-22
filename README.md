# Online Banking System (Java + MySQL)

This is a console-based banking application developed in Java using MySQL as the backend. It allows users to register, log in, check balance, transfer funds, and view transaction history.

## Project Structure

com.bank
├── Main.java # Entry point: UI/menu logic
├── DBConnection.java # Manages MySQL DB connection
├── UserService.java # Handles registration and login
├── AccountService.java # Manages balance and fund transfer
├── TransactionService.java # Shows transaction history
├── User.java # POJO for user details

markdown
Copy code

## Technologies Used

- Java 8 or above
- MySQL
- JDBC
- Maven
- Eclipse IDE (optional)

## Setup Instructions

1. Clone the Repository

git clone https://github.com/narasimha-k-dev/Online-Banking-System-Final.git
cd Online-Banking-System-Final

sql
Copy code

2. Configure the Database

- Create a MySQL database named `bank`
- Run the following SQL:

```sql
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    password VARCHAR(50),
    balance DOUBLE DEFAULT 0
);

CREATE TABLE transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sender_id INT,
    receiver_id INT,
    amount DOUBLE,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
Update DB Credentials in DBConnection.java

java
Copy code
private static final String URL = "jdbc:mysql://localhost:3306/bank";
private static final String USER = "your_mysql_username";
private static final String PASS = "your_mysql_password";
Build and Run the Application

python
Copy code
mvn compile
mvn exec:java -Dexec.mainClass="com.bank.Main"
Features
User registration and login

Balance inquiry

Money transfer (with rollback on failure)

Transaction history

Clean service-based architecture

Future Enhancements
Password encryption using hashing

REST API with Spring Boot

Admin functionality

Web frontend using HTML/CSS/JavaScript

Author
Sunny Itachi
GitHub: https://github.com/narasimha-k-dev

License
This project currently does not include a license. If you plan to reuse or distribute the code, please add a license file (MIT, Apache 2.0, etc.) as appropriate.

Contributing
Contributions are welcome. Open issues or pull requests if you wish to improve or expand the project.

yaml
Copy code

---

Let me know if you'd like this converted into a downloadable file or want help adding a license next.
