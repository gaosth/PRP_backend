# Spring Boot, MySQL, JPA, React Rest API

Restful CRUD API for a simple user management application using Spring Boot, Mysql, JPA and React.

## Requirements

1. Java - 11

2. Maven - 4.0

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/gaosth/PRP_backend.git
```

**2. Create Mysql database**
```bash
create database PRP
```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.yaml`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Build and run the app using maven**

```bash
mvn package
java -jar target/Spring-boot-User-demo.jar
```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines following CRUD APIs.

    GET /users
    
    POST /users
    
    GET /users/{userId}
    
    PUT /users/{userId}
    
    DELETE /users/{userId}

You can test them using postman or from the UI.

## Embedded Front-end Pages

**1. Access localhost:8080**

The app has embedded front-end pages. Access `localhost:8080` to enter the top page.

**2. Register and Login**

Check the database for the user data, enter the `email` and `password` to log in. In register page, you could register through `email`, and the user name will generated automatically from `email`.

