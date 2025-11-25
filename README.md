🌙 Dream Journal Analyzer (Java + MySQL)

A personal tool created by Aarush Deshmukh for logging, organizing, and analyzing dreams.
This project uses Java, MySQL, and JDBC to store and retrieve dream entries with theme-based searching and automatic report generation.

📌 Features
✔ Add Dream Entries

Store dream descriptions, themes, and dates into a MySQL database.

✔ View All Entries

Retrieve and display all stored dreams.

✔ Search by Theme

Search dreams by themes such as adventure, nightmare, surreal, etc.

✔ Generate Analysis Report

Shows:

Total number of dreams

Count of dreams by theme

✔ Persistent Storage using MySQL

All data is stored in the database for long-term tracking.

🛠 Technologies Used
Technology	Purpose
Java (JDK 17+)	Core application logic
MySQL	Data storage
JDBC (MySQL Connector/J)	Connect Java ↔ MySQL
Terminal / CMD	Running the program
📦 Database Setup

Open MySQL Workbench or phpMyAdmin and run:

CREATE DATABASE dream_journal;

USE dream_journal;

CREATE TABLE dreams (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description TEXT,
    theme VARCHAR(50),
    date DATE
);


📌 MySQL Credentials Used

username: root
password: 1234

📁 Project Structure
/DreamJournalAnalyzer
│
├── DreamJournalAnalyzer.java
├── mysql-connector-j-8.3.0.jar
└── README.md

🚀 How to Run the Project
1️⃣ Install JDK

Ensure Java is installed:

java -version

2️⃣ Install MySQL

Set password to:

1234

3️⃣ Download JDBC Driver

Download MySQL Connector/J
Place the .jar file in the project folder.

4️⃣ Compile the Program
Windows:
javac -cp .;mysql-connector-j-8.3.0.jar DreamJournalAnalyzer.java

Mac/Linux:
javac -cp .:mysql-connector-j-8.3.0.jar DreamJournalAnalyzer.java

5️⃣ Run the Program
Windows:
java -cp .;mysql-connector-j-8.3.0.jar DreamJournalAnalyzer

Mac/Linux:
java -cp .:mysql-connector-j-8.3.0.jar DreamJournalAnalyzer

🖥 Program Menu
1. Add a new dream entry
2. View all entries
3. Search entries by theme
4. Generate analysis report
5. Exit

✨ Future Improvements

GUI using Java Swing / JavaFX

Web-based frontend (HTML + JS)

Export dreams to PDF

Add sentiment analysis

Mobile app version

👤 Author

Aarush Deshmukh
BCA Student — Passionate about Java development, databases, and creative projects.
