# Spring Book Store

This project for demonstrate basic Spring Boot with simple CRUD Rest API connect to Postgres.

## Getting Started

### Prerequisites

- Java 21
- Maven 3.6.0 or higher
- Docker (for running PostgreSQL and pgAdmin)

### Installation

1. **Clone the repository:**
    ```sh
    git clone https://github.com/yourusername/spring-book-store.git
    cd spring-book-store
    ```

2. **Start the Docker containers:**
    ```sh
    docker-compose up -d
    ```

3. **Build the project:**
    ```sh
    ./mvnw clean install
    ```

4. **Run the application:**
    ```sh
    ./mvnw spring-boot:run
    ```

Remark: Update `jwt.secret` in `application.properties` to your own key. This can be done by run this command

```bash
openssl rand -base64 32
```

### Accessing the Application

- The application will be running at: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI Docs: `http://localhost:8080/api-docs`
- Prometheus: `http://localhost:9090`
- Grafana: `http://localhost:3000` (admin|admin)

Default database credential
Username: `bookstore_user`
Password: `bookstore_password`

### Running Tests

To run the tests, use the following command:
```sh
./mvnw test
```
