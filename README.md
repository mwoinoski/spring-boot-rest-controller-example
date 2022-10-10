# Spring Boot REST API Examples

## Projects Overview

This directory contains four projects related to Spring Boot REST APIs:
1. `rest-controller-basic` - `SpringRestController.java` defines a bare-bones CRUD REST controller for `User` and `UserRole`
2. `rest-controller-handle-errors` - adds exception handling
3. `rest-controller-http-statuses` - sets HTTP response statuses appropriately
4. `ex5.3-solution_rest-controller` - solution to the optional hands-on exercise below

## Optional Hands-On Exercise

Using the `rest-controller` examples, add a CRUD REST API for `VideoCategory`

You can keep working on your Exercise 3.2 project, or start with the `ex3.2-solution-boot-data-jpa` project

### Project set-up

1. Add Spring Boot Web starter and Dev Tools to `pom.xml` dependencies:
    ```
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>

2. Run Maven > Update project

3. Edit `schema.sql` and make `video_categories.id` an `identity` field: 
    ```
    CREATE TABLE video_categories (
      id identity primary key,  -- auto-generated key

4. Add a generation strategy to the `VideoCategory` `id` field:
    ```
    @Id
    @GeneratedValue(strategy = GenerationType.INDENTITY)
    private int id;

5. Create a new `SpringRestController` class and add REST API methods to it

6. Run the Spring Boot app and send HTTP requests using Postman or Insomnia

7. To view the database directly:
    * Browse to `http://localhost:8080/h2-console`
    * Change the JDBC URL to `jdbc:h2:mem:testdb`
    * Click `Connect`
    * Enter SQL queries in the SQL Statement text box:
        ```
        select * from users;
        select * from user_roles;

    * Click the Run button or press `Ctrl-Enter`
