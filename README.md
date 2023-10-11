## Movie Reviews System Backend Project

A backend system for managing movies, users, and reviews with Java Spring Boot and MongoDB. 

### Features

- **CRUD Operations**: users can suggest edit for movies, and post and edit their movie rating and reviews. 
- **User Authorization & Authentication**: complete registration and authorization and authentication process with email verification links and social media login (OAuth).
- **Continuous Integration & Deployment**: integrated with GitHub Actions to automatically run and deploy on AWS EC2.
- **Open API Documentation**: manage and test RestfulAPIs with Springfox Swagger and Postman. 

### Technologies

- Spring Boot: For the core backend framework.
- Spring Security: Ensuring safe user authentication and authorization.
- MongoDB: NoSQL database for flexible data storage.
- Springfox Swagger: For API documentation.

### Getting Started

1. Clone the repository.
2. Install necessary dependencies using your preferred package manager.
3. Set up your MongoDB database and configure the connection string in the `.env` file application.
4. For the development environment, the email server is set up using MailDev. You can check it out [here](https://github.com/maildev/maildev).
5. After the server is up, navigate to [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) to see the complete API documentation.

