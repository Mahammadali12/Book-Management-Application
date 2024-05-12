# Book Management System

## Overview
The Book Management System is a Java-based application that facilitates the management of book information, providing functionalities for both users and administrators. It offers features such as book management (adding, editing, and listing books), user registration and login, and administrative access.

## Features
- **User Registration and Login:** 
  - Users can register with their credentials, which are stored securely.
  - Login functionality verifies user credentials and grants access based on roles.

- **Book Management:**
  - Add new books with details like title, author, rating, and reviews.
  - Edit existing book information via a user-friendly dialog.
  - List all books and search by various criteria.

- **Administrative Tools:**
  - Special tools for administrative access to manage users and book details.
  - Add new administrative entries for fine-grained control.

- **File Management Utilities:**
  - Utilities for reading and writing book/user data to files.
  - Organized data storage for efficient file management.

## Installation

### Prerequisites
- Java Development Kit (JDK) 8 or higher.
- An IDE (Integrated Development Environment) like Eclipse or IntelliJ IDEA is recommended.

### Steps
1. Clone or download the repository containing the Java files.
2. Import the files into your IDE as a Java Project.
3. Ensure that the JDK is correctly configured in your IDE.

## Running the Application
1. Compile the Java files in the project to generate the necessary `.class` files.
2. Execute the main file (e.g., `Login.java` or `Page.java`) to start the application.
3. Navigate through the application using the graphical interface.

## Development Details

### Structure
- **AdminAdd.java:** Implements an administrative interface for adding new entries.
- **Book.java:** Represents a book entity with relevant attributes and provides methods for book manipulation.
- **EditBookDialog.java:** Contains a dialog window for editing book information.
- **FileMethods.java:** Provides utility methods for reading and writing to files.
- **Login.java:** Implements a login system and verifies user credentials.
- **Person.java:** Represents a `Person` entity, used for user management.
- **Register.java:** Manages the registration process for new users.
- **Page.java:** Serves as a primary graphical window to start the application.
- **MyBooks.java:** Displays and manages the user's collection of books.

### Development Process
- **Version Control:** Utilize Git for version control and collaborative development.
- **Testing:** Perform thorough testing using JUnit or manual QA for each feature.
- **UML Diagrams:** Design and document with UML to outline relationships and improve understanding.
