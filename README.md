# Mediscreen

Service multi module managing patient and note and calculating chance of diabete.

## Getting Started

Once you clone the project you can move inside a microservice with your terminal and run this command to generate a docker image :

````
./mvnw spring-boot:build-image
````

For the diabete-calculator, you need first to run the other microservices because it needs them for integration test while creating the image.

### Prerequisites

- Java 11
- Maven 3

## Built With

* [Spring](https://spring.io/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Swagger](https://swagger.io/) - Used to generate the API documentation