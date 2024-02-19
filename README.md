This project implements a backend system for a train scheduling system using Java Spring Boot. It provides APIs to perform CRUD operations on Train objects and to retrieve trains between given source and destination stations.


Prerequisites
Java JDK installed on your system
Maven installed on your system
Any preferred database installed (MySQL, PostgreSQL, etc.)
Postman or any API testing tool for testing the APIs


Instructions to Run the Application

Clone the Repository:
  git clone <repository_url>

  Database Configuration:

  Set up your preferred database and configure the database connection details in application.properties file located at src/main/resources.

Run the Application:

mvn spring-boot:run

run the jar file
![runningjarfile](https://github.com/suman-kumar-sharma/local24Train_api/assets/36415497/6c8fd538-678e-424a-acd3-6b6bf5faf4dc)

Test end point with postman
![updatetrain](https://github.com/suman-kumar-sharma/local24Train_api/assets/36415497/aa3a1162-a4d7-4561-a607-d7e6a22dbf67)

![findtrain](https://github.com/suman-kumar-sharma/local24Train_api/assets/36415497/60a36aaa-b89e-4293-98e5-034c675e39a3)
![deletetrain](https://github.com/suman-kumar-sharma/local24Train_api/assets/36415497/028fdb4a-30c7-410c-98cc-04ed55a857d9)
![addtrain](https://github.com/suman-kumar-sharma/local24Train_api/assets/36415497/7ace9810-b354-4d09-af7d-3e84fd1f4335)


