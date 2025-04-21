# Carpooling Application Project

<!-- ## Team Members: -->
Team Members:
- Natalia Acuña
- Carlos Castillo
- Felipe Benavides
  
<!-- Course -->
Course: Data Bases 

## Description

This project is a desktop Carpooling (ride-sharing) application primarily designed for institutional communities like universities or companies. It aims to provide a secure, efficient, and cost-effective transportation alternative by connecting drivers and passengers within the same institution for shared commutes. The system is built with a focus on parametrizability to be adaptable to different institutions.

Key features include user registration/profiling, institutional verification via API, ride publishing and searching, booking management, activity history, administrator statistics, event logging (audit trail), and an optional map interface using GMapsFX.

## Table of Contents

- [Motivation](#motivation)
- [Features](#features)
- [Architecture](#architecture)
- [Technology Stack](#technology-stack)
- [Prerequisites](#prerequisites)
- [Database Setup (Oracle 11gR2)](#database-setup-oracle-11gr2)
- [Project Setup & Installation](#project-setup--installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [Running Tests](#running-tests)
- [Project Structure](#project-structure)
- [License](#license)

## Motivation

The project addresses the growing need for sustainable mobility solutions and aims to mitigate the environmental impact of transportation. Given the limitations and perceived safety issues of existing public transport systems, particularly affecting university students and staff in Costa Rica (as per UCR studies referenced in the project specification), this Carpooling system offers a reliable and community-focused alternative. It facilitates coordination, potentially reduces costs, and enhances user safety within a trusted institutional network.

## Features

*   **User Management:** Registration (students/staff), profile creation & management (personal info, contact, ID, role - driver/passenger, institution), institutional email verification.
*   **Institutional Verification:** Integration with external APIs provided by each institution to verify user affiliation during registration/login.
*   **Ride Publishing (Drivers):** Define routes (origin, destination, optional stops), schedule (date/time), vehicle selection, passenger capacity, optional cost per passenger. Driver control over route and stops.
*   **Ride Searching & Booking (Passengers):** Search available rides based on schedule and destination. View ride details (driver info, vehicle, route, cost). Book a seat specifying pickup point. Direct communication assumption outside the system for pickup details.
*   **Activity History:** Users can view their past rides (as driver or passenger).
*   **Security:** Ensure users only interact with members of the same institution. Secure password handling.
*   **Payment Methods:** Supports optional payment via cash or Sinpe Móvil (as decided by the driver).
*   **General Queries (Admin/Reports):**
    *   Top 5 active drivers.
    *   Top 5 concurrent locations by date range.
    *   Top 5 users with most registered trips.
    *   Detailed list of all trips (driver, passenger, route info).
    *   Average amount charged by drivers.
    *   Count of new users in the last 3 months.
    *   Total record counts displayed for queries.
*   **Statistics Module (Admin):** Visual dashboard with charts/graphs for:
    *   Total drivers by institution/gender/date range.
    *   Total passengers by institution/gender/date range.
    *   Total users by age range/gender.
    *   Additional custom statistics (professor-approved).
*   **Audit Log (Bitácora):** Automatic logging (via DB Triggers) of sensitive data changes (e.g., ride cancellation, price modification). Queryable interface for admins.
*   **Daily Batch Job:** Parametrizable daily process (DB Job) calculating trip data per institution and generating a report for administrators.
*   **Map Integration (Optional):** Uses GMapsFX to visually display routes during publishing, searching, and viewing ride details.
*   **Parametrization:** System configurable for different institutions without hardcoded data. Admin users manage parametrizable sections.

## Architecture

The application follows an **N-Layer Architecture** to promote separation of concerns, modularity, scalability, and maintainability:

1.  **Presentation Layer (UI):** Built with JavaFX (FXML for layout, CSS for styling, Controllers for logic). Handles user interaction and displays data. Uses GMapsFX for map components.
2.  **Business Logic Layer (BL):** Contains services implementing business rules, validation, calculations (e.g., route optimization - basic, or calling external services), DTOs for data transfer.
3.  **Data Access Layer (DA):** Uses JPA (Hibernate) for ORM. Contains Entities mapped to DB tables and Repositories for data CRUD operations. Interacts directly with the database via JDBC.
4.  **Database (DB):** Oracle 11gR2 (Mandatory). Stores all application data, executes triggers for the audit log, and runs the scheduled daily job.

Code quality follows principles outlined in "Clean Code" by Robert C. Martin.

## Technology Stack

This project utilizes a combination of established and modern Java technologies:

*   **Language:** **Java (JDK 22)**
    *   The core programming language for the application. Version 22 provides access to recent language features.

*   **Framework (UI):** **JavaFX**
    *   The primary framework for building the desktop Graphical User Interface (GUI).
    *   Key Dependencies:
        *   `` `org.openjfx:javafx-controls` ``: Provides standard UI elements like buttons, labels, text fields, tables.
        *   `` `org.openjfx:javafx-fxml` ``: Enables defining UI layouts declaratively using FXML files, separating UI design from application logic.
        *   `` `org.openjfx:javafx-graphics` ``: Core graphics engine, CSS support, and scene graph management.
        *   `` `org.openjfx:javafx-web` ``: Includes the WebView component, essential for embedding web content, notably used by GMapsFX.

*   **Build Tool & Dependency Management:** **Apache Maven**
    *   Manages the project's build lifecycle (compiling, testing, packaging) and automatically downloads/manages external libraries (dependencies) defined in the `pom.xml`.
    *   Utilizes plugins like `maven-compiler-plugin` (compilation), `javafx-maven-plugin` (running/packaging JavaFX apps), `maven-surefire-plugin` (running tests), and potentially `maven-enforcer-plugin` (ensuring environment rules like JDK version).

*   **Data Persistence:** **JPA (Jakarta Persistence API) / Hibernate**
    *   Standard Java API for Object-Relational Mapping (ORM), simplifying database interactions by mapping Java objects (Entities) to database tables.
    *   Key Dependencies:
        *   `` `jakarta.persistence:jakarta.persistence-api` ``: Defines the core JPA interfaces and annotations.
        *   `` `org.hibernate.orm:hibernate-core` ``: The chosen JPA *implementation*. Handles the actual translation between Java objects and SQL queries.

*   **Database:** **Oracle Database 11gR2 (Mandatory)**
    *   The relational database system required by the project specifications to store all application data, execute triggers (audit log), and scheduled jobs.
    *   Key Dependency:
        *   **Oracle JDBC Driver** (e.g., `` `com.oracle.database.jdbc:ojdbc11` ``): The specific driver JAR needed for Java applications to connect and communicate with the Oracle database. *Must be included in the `pom.xml`.*

*   **Map Library:** **GMapsFX**
    *   A JavaFX library for integrating Google Maps components directly into the application.
    *   Key Dependency: `` `com.dlsc.gmapsfx:gmapsfx` ``: Provides the necessary classes and components to display maps, markers, routes, etc., within the JavaFX UI.

*   **Logging:** **SLF4J & Logback**
    *   Used for recording application events, informational messages, warnings, and errors, which is crucial for debugging and monitoring.
    *   Key Dependencies:
        *   `` `org.slf4j:slf4j-api` ``: A facade or abstraction layer for logging frameworks. Allows using a consistent logging API regardless of the underlying implementation.
        *   `` `ch.qos.logback:logback-classic` ``: A powerful and flexible logging *implementation* chosen as the backend for SLF4J. Configured via `src/main/resources/logback.xml`.

*   **Testing:** **JUnit 5 & Mockito**
    *   Frameworks used for writing and running automated tests to ensure code correctness and stability.
    *   Key Dependencies:
        *   `` `org.junit.jupiter:*` ``: The latest generation of the popular JUnit framework for writing unit and integration tests.
        *   `` `org.mockito:*` ``: A mocking framework used to create "fake" versions of dependencies, allowing tests to isolate the specific code unit being tested.
        *   (Potentially) `` `com.h2database:h2` `` (scope `test`): An in-memory database often used for running Data Access Layer tests quickly without needing a full external database setup during the test phase.

*   **IDE (Recommended):** IntelliJ IDEA Ultimate (Excellent support for JavaFX, Maven, JPA, Database Tools, etc.)
*   **Version Control:** Git
*   **Collaboration Platform:** GitHub

## Prerequisites

Before you begin, ensure you have the following installed:

1.  **Git:** [Download & Install Git](https://git-scm.com/downloads)
2.  **Java Development Kit (JDK):** Version 22. Ensure `JAVA_HOME` is set and `java --version` works. Install it from: https://download.oracle.com/java/22/archive/jdk-22.0.2_windows-x64_bin.exe
3.  **Apache Maven:** Version 3.6+ recommended. Ensure `mvn --version` works. ([Download & Install Maven](https://maven.apache.org/download.cgi))
4.  **Oracle 11gR2 Database:** Access to a running instance (local or remote). You'll need connection details (host, port, SID/Service Name, username, password).
5.  **(Recommended) Oracle SQL Developer or other DB Client:** To run setup scripts and inspect the database. ([SQL Developer Download](https://www.oracle.com/database/sqldeveloper/technologies/download/))
6.  **(Recommended) IntelliJ IDEA:** Provides a wonderful experience for JavaFX, Maven, JPA, and Database tools.

## Database Setup (Oracle 11gR2)

1.  **Connect:** Using SQL*Plus, SQL Developer, or IntelliJ's Database Tool, connect to your Oracle instance with a user account that has privileges to create users, tables, sequences, triggers, and schedule jobs (e.g., `SYS as SYSDBA` or a dedicated admin user).

2.  **Run Scripts:** Execute the SQL scripts located in the `/database/scripts/` directory **in the specified order** (V1, V2, V3...). These scripts will:
    *   `V1__create_schema.sql`: Create tables, sequences, primary keys, foreign keys.
    *   `V2__create_triggers.sql`: Create the database triggers for the audit log (bitácora).
    *   `V3__create_db_jobs.sql`: Create the scheduled daily job.
    *   `R__populate_parameters.sql`: (Optional but recommended) Populate initial parameter data (e.g., base institution data if modelled). Run this script after the schema is created.
    *   `R__seed_test_data.sql`: (Optional) Populate the database with sample data for testing.

3.  **Verify:** Ensure tables, triggers, and the job have been created successfully.

## Project Setup & Installation

1.  **Clone Repository:**
    ```bash
    git clone https://github.com/BUDA124/TEC_CarpoolingApp
    cd CarpoolingApp
    ```

2.  **Open in IDE:** Open the cloned `CarpoolingApp` directory as a project in IntelliJ IDEA. It should automatically recognize it as a Maven project.

3.  **Download Dependencies:** Allow Maven to resolve and download all project dependencies defined in the `pom.xml` file. This might take a few minutes the first time. If it doesn't start automatically, use the Maven tool window (`View > Tool Windows > Maven`) and click the "Reload All Maven Projects" button.

4.  **Configure Application:** Proceed to the [Configuration](#configuration) section below.

## Configuration

Sensitive configuration details (like database credentials or API keys) should **not** be committed directly to Git.

1.  **Locate Example File:** Find the example configuration file at `src/main/resources/config/application.example.properties`.

2.  **Create Local Config:** Copy this file and rename the copy to `application.properties` in the *same* directory (`src/main/resources/config/`).
    ```bash
    cp src/main/resources/config/application.example.properties src/main/resources/config/application.properties
    ```
    **Important:** The `application.properties` file is already listed in the `.gitignore` file, so your local configuration with real credentials will not be accidentally committed.

3.  **Edit `application.properties`:** Open the newly created `application.properties` file and fill in the required values, especially the database connection details:
    ```properties
    # Database Configuration
    db.url=jdbc:oracle:thin:@YOUR_ORACLE_HOST:1521:YOUR_SID
    db.username=DB_USERNAME_PLACEHOLDER
    db.password=DB_PASSWORD_PLACEHOLDER
    
    # Institution Verification APIs (Add one block per supported institution)
    # Example for TEC:
    institution.api.tec.url=https://api-dev.tec.ac.cr/verify # Use DEVELOPMENT endpoint URL
    institution.api.tec.key=YOUR_TEC_API_KEY_GOES_HERE
    
    # Example for Another Institution (UCR):
    institution.api.ucr.url=https://api-sandbox.ucr.ac.cr/validation # Use SANDBOX endpoint URL
    institution.api.ucr.key=YOUR_UCR_API_KEY_GOES_HERE
    
    # Google Maps API Key (If required and obtained)
    maps.google.api.key=YOUR_GOOGLE_MAPS_API_KEY_IF_USED
    ```

## Running the Application

1.  **Ensure Database is Running and Configured.**
2.  **Ensure `application.properties` is Correctly Configured.**
3.  **Run from IntelliJ IDEA (Recommended):**
    *   Locate the main class: `org.tec.carpooling.app.CarpoolingApplication`.
    *   Right-click on the file or inside the editor and select "Run 'CarpoolingApplication.main()'".
    *   IntelliJ will build and launch the JavaFX application.
4.  **Run using Maven (Terminal):**
    *   Navigate to the project root directory in your terminal.
    *   Use the JavaFX Maven Plugin (if configured in `pom.xml`):
        ```bash
        mvn javafx:run
        ```
    *   Or package and run the JAR (ensure the `pom.xml` configures the main class and bundles dependencies, e.g., using `maven-shade-plugin`):
        ```bash
        mvn clean package
        java -jar target/CarpoolingApp-1.0-SNAPSHOT.jar # Adjust JAR name as needed
        ```

## Running Tests

Tests are located in the `src/test/java` directory.

1.  **Run All Tests (Maven):**
    ```bash
    mvn test
    ```
2.  **Run Tests (IntelliJ IDEA):**
    *   Right-click on the `src/test/java` directory or a specific test class/method.
    *   Select "Run 'All Tests'" or "Run 'YourTestClass'".

*Note: Integration tests (e.g., repository tests) might require a running database configured in `src/test/resources/test-application.properties` or `src/test/resources/test-persistence.xml`.*

## Project Structure

The project follows the standard Maven directory layout:

CarpoolingApp/

├── database/ ---------> # SQL scripts

├── docs/ --------------> # Documentation (ERD, Manual, Data Dictionary)

├── src/

│ ├── main/

│ │ ├── java/ ----------> # Application source code (org.tec.carpooling)

│ │ │ ├── app/ --------> # Main application entry point

│ │ │ ├── ui/ ----------> # Presentation Layer (JavaFX Controllers, FXML Loader, Map Utils)

│ │ │ ├── bl/ ----------> # Business Logic Layer (Services, DTOs, Validators)

│ │ │ ├── da/ ---------> # Data Access Layer (JPA Entities, Repositories)

│ │ │ └── common/ --> # Shared code (Exceptions, Utils, Constants)

│ │ └── resources/ ---> # Non-code resources

│ │ ├── META-INF/ ---> # JPA persistence.xml

│ │ ├── config/ -------> # application.properties

│ │ ├── views/ --------> # FXML files

│ │ ├── css/ ----------> # Stylesheets

│ │ └── images/ ------> # Icons and images

│ └── test/ ------------> # Test code and resources

├── .gitignore --------> # Git ignore rules

├── pom.xml ---------> # Maven configuration

└── README.md -----> # This file

## License

This project is licensed under the [MIT License](LICENSE) - see the LICENSE file for details.


