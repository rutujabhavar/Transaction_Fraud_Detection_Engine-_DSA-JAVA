# Transaction_Fraud_Detection_Engine-_DSA-JAVA

A simple Java-based fraud detection system that analyses transactions and flags suspicious activity based on predefined rules. This project demonstrates object-oriented design, basic data validation, and fraud-rule logic.



🚀 Features

✔ Detects potentially fraudulent transactions

✔ Rule-based detection logic

✔ Clean, modular Java classes

✔ Easy to extend with new rules

✔ Command-line based execution


🧠 How It Works

The system processes Transaction objects and evaluates them using the FraudDetector class.
If a transaction matches fraud-rule conditions, it is flagged as suspicious and reported.

Typical rules might include (example):

High-value transaction detection

Suspicious account activity

Pattern-based detection

You can easily update or add rules in FraudDetector.java.



📁 Project Structure
.
├── Main.java
├── Transaction.java
├── FraudDetector.java
└── README.md



🛠 Tech Stack

Java (JDK 8+)

DSA



🔧 Setup & Installation

1️⃣ Clone the Repository

git clone https://github.com/<your-username>/<repo-name>.git
cd <repo-name>

2️⃣ Compile the Project

javac Main.java

This will compile all dependent classes automatically.

3️⃣ Run the Program

java Main



📌 Example Usage

When you run the program, it will evaluate transactions and print results to the console, such as:
Transaction ID: 101 — Status: SAFE
Transaction ID: 102 — Status: FRAUD DETECTED 🚨


🧩 Core Classes

Transaction.java

Represents a transaction object containing details such as:

ID

Amount

Sender

Receiver

Timestamp

FraudDetector.java

Contains the fraud-checking logic and rules.

Main.java

Entry point of the program — runs detection and prints results.




