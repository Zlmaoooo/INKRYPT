<div align="center">

# 🔐 INKRYPT

### *Your Thoughts, Encrypted & Secured*

[![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg?style=for-the-badge)](LICENSE)
[![Security](https://img.shields.io/badge/AES-Encrypted-green?style=for-the-badge&logo=lock&logoColor=white)]()
[![SHA-256](https://img.shields.io/badge/SHA--256-Hashed-orange?style=for-the-badge&logo=security&logoColor=white)]()

*A powerful, terminal-based digital diary application with military-grade encryption to keep your thoughts private and secure.*

[Features](#-features) • [Installation](#-installation) • [Usage](#-usage) • [Security](#-security) • [Screenshots](#-screenshots) • [Contributing](#-contributing)

</div>

---

## 📋 Table of Contents

- [About](#-about)
- [Features](#-features)
- [Technology Stack](#-technology-stack)
- [Installation](#-installation)
- [Usage](#-usage)
- [Security](#-security)
- [Project Structure](#-project-structure)
- [Screenshots](#-screenshots)
- [Contributing](#-contributing)
- [License](#-license)
- [Contact](#-contact)

---

## 🎯 About

**INKRYPT** is a secure, offline digital diary application that prioritizes your privacy. Built with Java, it provides a clean terminal-based interface for users to write, store, and manage personal diary entries with enterprise-level encryption. Perfect for anyone who values their privacy and wants a simple, distraction-free journaling experience.

### Why INKRYPT?

- 🔒 **Privacy First**: All entries are encrypted using AES-128 encryption
- 🚀 **Fast & Lightweight**: Terminal-based interface with no bloat
- 💾 **Offline Storage**: Your data stays on your machine, always
- 👥 **Multi-User Support**: Multiple users can maintain separate diaries
- 🎨 **Clean Interface**: Color-coded, intuitive terminal UI
- 🔑 **Secure Authentication**: SHA-256 password hashing

---

## ✨ Features

### 🔐 Security Features
- **AES-128 Encryption** for all diary entries
- **SHA-256 Password Hashing** for user authentication
- **Unique Encryption Keys** per user
- **No Cloud Storage** - complete offline functionality

### 📝 Core Functionality
- ✅ User Registration & Login System
- ✅ Write & Save Encrypted Diary Entries
- ✅ View All Past Entries with Timestamps
- ✅ Beautiful Card-Style Entry Display
- ✅ Multi-User Support
- ✅ User Management Dashboard

### 🎨 User Experience
- 🌈 Color-coded terminal interface
- 📊 Organized card-style entry layouts
- ⚡ Loading animations for better UX
- 🔄 Persistent menu navigation
- 📋 Clean and intuitive UI design

---

## 🛠️ Technology Stack

| Component | Technology |
|-----------|------------|
| **Language** | Java 17+ |
| **Encryption** | AES (Advanced Encryption Standard) |
| **Hashing** | SHA-256 |
| **Encoding** | Base64 |
| **I/O** | Java NIO & FileWriter |
| **Security** | javax.crypto |

---

## 📦 Installation

### Prerequisites

- **Java Development Kit (JDK) 17 or higher**
- Windows/Linux/macOS with terminal support

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/Zlmaoooo/INKRYPT.git
   cd INKRYPT
   ```

2. **Compile the program**
   ```bash
   javac SecureDigitalDiary.java
   ```

3. **Run the application**
   ```bash
   java SecureDigitalDiary
   ```
   
   Or simply double-click `run.bat` on Windows!

---

## 🚀 Usage

### Starting the Application

Run the program using:
```bash
java SecureDigitalDiary
```

### Main Menu Options

```
========================
  WELCOME TO SECURE     
    DIGITAL DIARY       
========================
1. Register
2. Login
3. Users
4. Exit
========================
```

### Registration
1. Select **"1. Register"**
2. Enter a unique username (case-insensitive)
3. Create a secure password
4. You'll be automatically logged in!

### Writing Entries
1. Login with your credentials
2. Select **"1. Write New Diary Entry"**
3. Enter a title for your entry
4. Write your content
5. Entry is automatically encrypted and saved!

### Viewing Entries
1. From the main menu, select **"2. View Diary Entries"**
2. All your past entries will be displayed in a beautiful card format
3. Entries show title, timestamp, and content

---

## 🔒 Security

### Encryption Details

**INKRYPT** uses industry-standard encryption to protect your data:

- **AES-128 Encryption**: All diary entries are encrypted before storage
- **SHA-256 Password Hashing**: Passwords are never stored in plain text
- **Unique Encryption Keys**: Each user has their own encryption key
- **Base64 Encoding**: Encrypted data is encoded for safe file storage

### Data Storage

```
📁 INKRYPT/
├── 📄 SecureDigitalDiary.java    # Main application
├── 📄 users.txt                   # Hashed user credentials
├── 🔑 username.key                # User-specific encryption keys
└── 📔 username_entries.txt        # Encrypted diary entries
```

### Security Best Practices

- ⚠️ Keep your `.key` files secure
- 🔐 Use strong, unique passwords
- 💾 Backup your encrypted entries regularly
- 🚫 Never share your encryption keys

---

## 📂 Project Structure

```
INKRYPT/
├── SecureDigitalDiary.java    # Main application class
│   ├── User                    # User authentication class
│   ├── DiaryEntry             # Diary entry model
│   ├── SecurityUtils          # Encryption & hashing utilities
│   └── TerminalUtils          # UI formatting & colors
├── run.bat                     # Quick launch script (Windows)
├── README.md                   # Documentation
└── LICENSE                     # MIT License
```

### Class Overview

| Class | Purpose |
|-------|---------|
| `SecureDigitalDiary` | Main application logic and menu system |
| `User` | User credentials management |
| `DiaryEntry` | Diary entry data model with timestamps |
| `SecurityUtils` | AES encryption, decryption & SHA-256 hashing |
| `TerminalUtils` | Terminal UI enhancements and ANSI colors |

---

## 📸 Screenshots

### Main Menu
```
========================
  WELCOME TO SECURE     
    DIGITAL DIARY       
========================
1. Register
2. Login
3. Users
4. Exit
========================
Choose an option: _
```

### User Dashboard
```
========================
  MAIN MENU - USERNAME
========================
1. Write New Diary Entry
2. View Diary Entries
3. Logout
========================
Choose an option: _
```

### Entry Display
```
+------------------------+
| Title: My First Entry
| Timestamp: Fri Nov 29 10:30:45 IST 2025
| Content: Today was amazing!
+------------------------+
```

---

## 🤝 Contributing

Contributions are welcome! Here's how you can help:

1. **Fork the repository**
2. **Create a feature branch**
   ```bash
   git checkout -b feature/AmazingFeature
   ```
3. **Commit your changes**
   ```bash
   git commit -m 'Add some AmazingFeature'
   ```
4. **Push to the branch**
   ```bash
   git push origin feature/AmazingFeature
   ```
5. **Open a Pull Request**

### Ideas for Contribution
- 🌍 Export entries to PDF/TXT
- 🔍 Search functionality within entries
- 🏷️ Tags and categories for entries
- 📊 Entry statistics and analytics
- 🎨 Customizable themes
- 🔐 Password strength meter
- ☁️ Optional cloud backup feature

---

## 📄 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

---

## 📧 Contact

**Developer**: Zlmaoooo

**Repository**: [github.com/Zlmaoooo/INKRYPT](https://github.com/Zlmaoooo/INKRYPT)

**Issues**: [Report a Bug](https://github.com/Zlmaoooo/INKRYPT/issues)

---

<div align="center">

### ⭐ Star this repo if you find it useful!

**Made with ❤️ and Java**

*Keep your thoughts encrypted, keep your privacy protected.*

</div>
