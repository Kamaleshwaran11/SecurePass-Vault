
# 🔐 SecurePass Vault - Java CLI Password Manager

**SecurePass Vault** is a feature-rich, secure Command-Line Interface (CLI) Password Manager built using **Core Java**, **JDBC**, and **MySQL**. It provides robust password generation, storage, and management with advanced features like phishing detection, MFA simulation, and compliance logging.

---

## 🚀 Key Features

- ✅ **Add, View & Delete Passwords** securely
- 🔐 **Password Strength Checker** (Very Weak to Very Strong)
- 🧠 **Smart Password Generator** (randomized secure password with symbols)
- ⚠️ **Phishing Detection** (warns if labels include risky terms like 'bank', 'login', etc.)
- ♻️ **Duplicate Password Detection** (prevents reuse of previous session passwords)
- 📋 **Password History Tracker**
- 📜 **Compliance Logging** for password operations
- ☁️ **Simulated Cloud Backup** (mock export)
- 🔐 **Simulated Multi-Factor Authentication (MFA)**
- 🛟 **Simulated Password Recovery using OTP**
- 📚 **Password Security Guidelines**

---

## 🧱 Tech Stack

- **Java (Core + JDBC)**
- **MySQL** – Secure backend database
- **File I/O** – Used for compliance logging
- **CLI Terminal** – Lightweight and interactive

---

## 📁 Project Structure
```
SecurePass-Vault/ 
├── src/
│ ├── org/example/
│ │ ├── PasswordManager.java
│ │ ├── DatabaseConnection.java
│ │ └── Main.java
│ ├── compliance_log.txt # Compliance activity logs
├── README.md # Project documentation
└── pom.xml / build.gradle (optional if using Maven/Gradle)
```

---

## ⚙️ Setup & Usage

### 1. Clone the Repository
```
git clone https://github.com/kamaleshwaran11/securepass-vault.git
cd securepass-vault
```

### 2. MySQL Database Setup
Run these SQL commands:
```
CREATE DATABASE securevault;

USE securevault;

CREATE TABLE passwords (
    id INT AUTO_INCREMENT PRIMARY KEY,
    label VARCHAR(255),
    password_save TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
### 3. Configure DatabaseConnection.java
Update credentials in:
```
private static final String DB_URL = "jdbc:mysql://localhost:3306/securevault";
private static final String DB_USER = "your_username";
private static final String DB_PASSWORD = "your_password";
```

### 4. Compile & Run the Application
```
javac -cp ".:mysql-connector-java.jar" org/example/*.java
java -cp ".:mysql-connector-java.jar" org.example.Main
```

## 📱 CLI Options
```
====== SecurePass Vault ======
1. Generate Password
2. Check Password Strength
3. Save Password
4. Retrieve Passwords
5. Delete Password
6. Simulate MFA
7. Simulate Password Recovery
8. Simulate Cloud Backup
9. Show Compliance Logs
10. Password Guidelines
11. View Password History
0. Exit
```

## ℹ️ What Does “Simulated” Mean?
- Simulated MFA: Uses a fixed OTP (123456) to represent MFA.

- Simulated Recovery: Emulates email OTP recovery – no real email sent.

- Simulated Cloud Backup: Mimics cloud export with a local file log.

🔧 These features can be upgraded using real services like JavaMail API, AWS S3 SDK, or Twilio for OTP.

## 💡 Future Enhancements
- GUI Interface using JavaFX/Swing

- AES Encrypted Password Storage

- Login/Auth System

- Cloud Integration (Google Drive, AWS S3)

- Export to CSV/JSON

- Real Email OTP Support

## 👨‍💻 Developed By

 Kamaleshwaran.S

 📍 Chennai, India

 🌐 Portfolio <link href="https://kamaleshwaran11.github.io/"> | 💻 GitHub

## 🪪 License
This project is licensed under the MIT License. </br>
Feel free to use and contribute!
