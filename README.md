# Contact Management Demo Project

A CRUD demo project made with Java and Spring Boot, following the MVC and DTO design patterns.
The application allows you to perform create, read, update, and delete operations on a database.

## Technologies Used
+ Java 17
+ Spring Boot
+ Maven
+ Hibernate 
+ MySQL
+ Lombok

## Features
+ Full CRUD Operations: Supports Create, Read, Update, and Delete functionality.
+ MVC Architecture: Implements the Model-View-Controller pattern.
+ DTO Usage: Efficient data transfer between layers.
  
### Possible use in the real world
+ Personal agenda: Ideal for storing personal contact information in an organized way.
+ CRM (Customer Relationship Management) applications:  Suitable for small companies or sales representatives to manage customer information.
+ Organization of teams or work contacts: Allows easy storage and retrieval of work-related contacts.

## How to run the project

### 1. Clone the repository:
	git clone https://github.com/NicolasGabM/contacts.git
Open the project in your IDE to continue with the configuration.
 
### 2. Configure the Database in application.properties:
 ```
spring.datasource.url=jdbc:mysql://localhost:3306/{your_database}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.datasource.username= your_username
spring.datasource.password= your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
springdoc.api-docs.enabled=true
springdoc.swagger-ui.enabled=true
```
### 3. Running the Application

Install the dependencies and run ContactsApplication as a Java application in your IDE. 
Once the application running, you can access it at http://localhost:8080.
You can also access the API documentation at http://localhost:8080/swagger-ui/index.html#/.

## Api Endpoints
### Get

	GET localhost:8080/api/v1/contacts/
 
List all contacts.

![image](https://github.com/user-attachments/assets/e1a302ef-6011-40f3-9aa9-0fa28216ffca)


### Get by ID

	GET localhost:8080/api/v1/contacts/{id}
 
Get a contact by ID

![image](https://github.com/user-attachments/assets/926b4384-874b-4976-af60-980cb249efc3)

### Post

	POST localhost:8080/api/v1/contacts/
 
Create a new contact
**Parameters:** Data in json format.
 
 ![image](https://github.com/user-attachments/assets/556d3d5f-3cf3-41b1-9d50-408784bd2a64)

### Put

	PUT localhost:{8080}/api/v1/contacts/{id}
 
Update an existing contact
**Parameters:** Data in json format.

![image](https://github.com/user-attachments/assets/64af16b5-4adf-4c1a-96fe-0557f4b04b58)

### Delete

	DELETE localhost:8080/api/v1/contacts/{id}
 
Delete a contact by ID

 ![image](https://github.com/user-attachments/assets/a42a2450-fa94-4793-9b11-fcc1748bc3a0)

## To do 

+ Add a frontend to interact with the api.
+ Add unit tests with Junit.  
+ Authentication and authorization with Spring Security and JWT.
