#  Microservices Architecture Project

This project demonstrates a **Microservices Architecture** using Spring Boot and MongoDB. It is built to be modular and scalable, with each microservice responsible for a specific business function. Services communicate using REST APIs, are registered with a **Service Registry**, and are accessible through a centralized **API Gateway**.

---

##  Overview

The system is composed of the following microservices:

- **User Service** – Handles user-related operations.
- **Hotel Service** – Manages hotel data.
- **Rating Service** – Handles user-submitted ratings for hotels.

Each microservice is **independent**, has its **own MongoDB database**, and communicates with others using **REST APIs**.

---

##  Key Features

###  User Microservice
- Register and manage user data.
- Retrieves user-specific ratings from the Rating Service.

###  Hotel Microservice
- Create, update, and retrieve hotel information.
- Aggregates ratings from the Rating Service for each hotel.

###  Rating Microservice
- Allows users to submit ratings for hotels.
- Fetches ratings based on user ID or hotel ID.

###  Service Registry (Eureka)
- Keeps track of all registered services.
- Enables dynamic discovery and communication between services.

###  API Gateway (Spring Cloud Gateway)
- Provides a single entry point to all services.
- Routes incoming requests to the appropriate microservice.

###  Config Server
- Centralized configuration for all services.
- Retrieves configuration from a remote Git repository.

---

##  Tech Stack

| Technology            | Purpose                                    |
|------------------------|---------------------------------------------|
| Java 17               | Main programming language                   |
| Spring Boot           | Microservice framework                      |
| MongoDB               | NoSQL database used in all services         |
| Eureka Server         | Service discovery and registration          |
| Spring Cloud Gateway  | API routing and centralized access          |
| Spring Config Server  | Centralized configuration management        |
| Feign Client          | Declarative REST client for service calls   |

---

##  Setup and Running the Project

###  Prerequisites
- Java 17
- Maven
- MongoDB

###  Build All Services
From the root directory, run:
```bash
mvn clean install
```

###  Run Services
Start each service from its folder using:

```bash
cd <ServiceFolder>/<ServiceFolder>
mvn spring-boot:run
```

Start `ConfigServer` and `ServiceRegistry` **first**, then others.

###  Access the Gateway
Visit:

```
http://localhost:<PORT>
```

Replace `<PORT>` with the API Gateway's configured port (e.g., `8084`).

###  Test the APIs
Use **Postman** or `curl` to interact with APIs for:

- Creating users
- Managing hotels
- Submitting and viewing ratings

---

##  Future Enhancements

- **Authentication & Authorization** – Secure endpoints using OAuth 2.0 and JWT.
- **Fault Tolerance** – Use Resilience4J for circuit breakers and retries.
- **Rate Limiting** – Prevent API abuse with request throttling.
- **Distributed Tracing** – Enable request tracing across services.
- **Monitoring** – Add Prometheus and Grafana for service observability.

---

##  Conclusion

This project showcases the fundamentals of microservices architecture with an emphasis on modularity, scalability, loose coupling, and independent deployment. Each service operates independently, ensuring easy management and potential for future expansion. The use of Spring Cloud technologies makes the services robust and ready for production environments.
