## Movie Reviews System Backend Project

A backend system for managing movies, users, and reviews with Java Spring Boot and MongoDB. 

### Features

- **MVC Architecture**: utilized the model-view-controller architecture to build RESTful APIs that allow users to search for movies and conduct CRUD operations on the movies and reviews objects.
- **User Authorization & Authentication**: implemented complete registration, authorization and authentication process with email verification (Spring Security) and social media login (OAuth).
- **Continuous Integration & Deployment**: integrated with GitHub Actions to automatically run and deploy the app on AWS EC2.
- **Open API Documentation**: manage and test RestfulAPIs with Springfox Swagger and Postman.

### Technologies

- **Spring Boot**: For the core backend framework.
- **Spring Security**: Ensuring safe user authentication and authorization.
- **MongoDB**: NoSQL database for flexible data storage.
- **Swagger**: For API documentation.

### Getting Started

1. Clone the repository.
2. Install necessary dependencies using your preferred package manager.
3. Set up your MongoDB database and configure the connection string in the `.env` file application.
4. For the development environment, the email server is set up using MailDev. You can check it out [here](https://github.com/maildev/maildev).
5. After the server is up, navigate to [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) to see the complete API documentation.

