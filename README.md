# Exam Planner

A university project aimed at simplifying exam management and planning for the university 🥳.

Important consideration: we do not recommend using the following software as is for production environment.

## Installation

Note: We recommend using IntelliJ IDEA for running the development or production environment.
In this guide, you'll find executable actions such as `Run App` which are clickable in IntelliJ IDEA.

### Environment

It's possible to use different versions of Java, but we recommend using at least version 18.
Note that some features may not be available in versions earlier than 19.

- Java 19.0.1 (openJDK)
- Maven 3.8.5
- IntelliJ IDEA 2022.3.1
- Git 2.33.0
- MySQL 8.0.31
- Operating Systems: MacOS ARM64 (13.1), Windows (10, 11)

### Database Configuration

Currently, it's not possible to modify the database configuration from a configuration file. If you cannot conform to the `root` user with no password on the `localhost` server (port `3306`), you will need to modify the file `src/main/java/fr/univtours/examplanner/utils/Database` and change the database connection parameters.

Run the SQL script `generation.sql` at the root of the project.

This will create the database. Make sure you don't already have a database named `exam_planner`.

### Adding Mock Data

Run the SQL script `insertion.sql` at the root of the project.

This will add mock data to the database.

You will then have 3 users:

- `schooling`
- `department`
- `manager`

They all have the same password as their username by default, and they all have an access type corresponding to their name.

### Installation and Execution with IntelliJ

Install the project using IntelliJ IDEA via the `Run App` configuration provided with the project.

### Or Installation with Maven

Install the project using Maven

```bash
  mvn resources:copy-resources
  mvn dependency:copy-dependencies
```

We were unable to get `maven package` to work. It is therefore necessary to go through IntelliJ to launch the application.

## Documentation

You can find the project documentation in the `docs` branch. You also have online documentation deployed via GitHub Pages at the following address: https://remib18.github.io/exam-planner

## Tests

### Running Tests

It is important that the database is defined and free of data to run the tests.

To run the tests, execute the `Run Tests` configuration provided with the project.

### Code Covered by Tests

- Database connection
- Various CRUD operations on users

## Progress

### Features

- [x] Authentication
- [x] User management
- [x] Department management
- [x] Room management
- [ ] Exam management
- [x] Group management
- [ ] Proctor management
- [x] Schedule management
- [x] Integrated translation system
- [ ] Configuration file
- [ ] Data export

Note: many features have been disabled in order to provide a bug-free application.

### Known Bugs & Features Not Developed or Disabled

- Time slot dates cannot be modified
- Management of exams, proctors, curricula, and subjects (normally available for "department" roles) is disabled due to bugs
- Deleting elements that have relationships with other elements will raise an error (the app won't crash) (fix: delete cascade in database)

## Authors

- Rémi Bernard • [@remib18](https://www.github.com/remib18)
- Gabriel Galli • [@KalityGabi](https://www.github.com/KalityGabi)
- Benoît Cheramy • [@beubeu28](https://www.github.com/beubeu28)
- Mya Dumas-Libre • [@MyaDL](https://www.github.com/MyaDL)
- Antonin Rathaux • [@Arokiel](https://www.github.com/Arokiel)
