# Navigator Data Exporter

This project is a Spring Boot application that exports candidate data from a PostgreSQL database to a CSV file using Apache Camel. The application provides a REST endpoint to trigger the export process, which retrieves data from the database and converts it into CSV format, saving the file to a specified location.

## Features
- Export candidate data from a PostgreSQL database.
- Conversion of data into CSV format using Apache Camel.
- The exported file is saved to a location defined in the application configuration.
- REST endpoint to trigger the export process.

## Prerequisites
- Java 17 (or higher)
- Maven or Gradle (for building the project)
- PostgreSQL (configured with a `candidates` table)
- Apache Camel (included in dependencies)
- Docker (optional, if running PostgreSQL in a container)

## Technologies Used
- **Spring Boot**: Backend framework for building the application.
- **Apache Camel**: For data routing and transformation.
- **PostgreSQL**: Relational database to store candidate data.
- **Lombok**: For reducing boilerplate code.
- **Liquibase**: For database migrations.
- **Docker**: For containerizing the database (optional).

## Setup and Configuration

### Step 1: Clone the Repository
```bash
git clone https://github.com/aleksandar-pejkovic/navigator-data-exporter.git
cd navigator-data-exporter
