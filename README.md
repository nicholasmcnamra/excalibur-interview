## Prerequisites

Before running the application locally, make sure you have the following installed on your machine:

- [Node.js] https://nodejs.org/en/
- [npm] https://www.npmjs.com/ (Should come with Node.js)
- [Java-JDK] https://www.oracle.com/java/technologies/downloads/#java21 (Version 21)
- [Maven] https://maven.apache.org/install.html 
- [PostgreSQL] https://www.postgresql.org/download/


## Setup Steps

### 1. Clone this repository

### 2. Setup PostgreSQL Database
- Start postgres by logging in with your postgres credentials. (Replace postgres with your superuser if different.)
```psql -U postgres```

- Create the database for the app
```CREATE DATABASE excalibur_interview;```

### 3. Configure Spring Boot Application
- Update your credentials in the application.yml

Note: The database schema will be created when the app starts up and dropped when it stops. The database must exist before tunning. The database will also be hydrated with 1000 user records with user+index as the username and password+index as the password. For instance:
```user2```
```password2```
This process may take a few minutes on startup.

### 4. Run the Spring Boot App
- From the root backend folder, build and run the application:
```mvn clean install```
```mvn spring-boot:run```
The backend should now be running on ```http://localhost:8080```.

### 5. Run the React Frontend
- Open a new terminal and navigate to the frontend folder /frontend and run:
```npm install```
```npm start```

This will start the development server at ```http://localhost:3000```.

### 6. Optional - Build the app in Docker
- Follow steps 1-3, then add a .env file to the root directory with your postgres credentials:
```DB_USER=your-postgres-user```
```DB_PASSWORD=your-postgres-password```

- Start Docker (or Rancher)
- Run:
```docker compose up --build```
- The app should be running now on ```http://localhost:80```
