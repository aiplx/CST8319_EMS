# Employee Management System

## Overview

This project is an Employee Management System that allows for the management of employees. 

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup](#setup)
- [License](#license)

## Features

- Employee registration
- Employee login and logout
- Role management
- Logging with Log4j2
- JDBC connection to MySQL database

## Technologies Used

- Java
- Servlet API
- JSP
- JDBC
- MySQL
- Log4j2

## Setup

### Prerequisites

- Java 8 or higher
- Apache Tomcat 7 or higher
- MySQL 5.7 or higher
- Maven

### Installation

1. **Clone the repository:**
   ```sh
   git clone https://github.com/aiplx/CST8319_EMS.git
   cd CST8319_EMS

2. **Set up the MySQL database:**
   - Create a new MySQL database named 'ems_schema'.
   - Run the provided SQL script to set up the database schema and initial data.
3. **Configure the database connection:**
   Update the 'DatabaseUtil.java' file with your MySQL database connection details.
4. **Build the project:**
   ```sh
   mvn clean install
5. **Deploy to Tomcat:**
   ```sh
   mvn tomcat7:run

### License
This project is licensed under the MIT License. 
