# Facebook App

A Java application that allows users to manage profiles, posts, groups and login/register.

#### `I should mention that I've cleared the entry point of the application(Application.java) so the team members can work on the feature they prefer to.`

## Features need to develop

- Login and registration functionality(done).
- Friends add/remove(done).
- Messages module(done).
- Post creation and management.
- Profile creation and management.

## Structure

- `facebook_app`: Main project directory
- `controller`: Contains controllers for user interactions
    - `UserCtr`: User controller
- `dao`: Contains data access objects for database interactions
    - `UserDAO`: User data access object
- `entities`: Contains entity classes representing the application's data model
    - `User`: User entity
- `ui`: Contains classes for user interface components
    - `Posts`: Application post component(to be added)++
    - `Profile`: Application profile component(to be added)++
- `exceptions`: Contains custom exception classes(to be added)++
- `model`: Contains model classes representing the application's data model
    - `User`: User model
- `services`: Contains service classes for business logic
    - `UserService`: User service
- `resources`: Contains resources such as message and user data files
    - `messages.txt`: Text file containing application messages
    - `users.txt`: Text file containing user data

## Prerequisites

- Java 8 or later
- A Java IDE such as IntelliJ IDEA or Eclipse

## Installation

1. Clone the repository using `git clone https://github.com/flavicoman/facebook-project`
2. Open the project in your Java IDE
3. Configure the project's build path and ensure all required libraries are included


## Contributing

1. Fork the repository
2. Create a new branch for your feature or bug fix
3. Commit your changes and push them to your fork
4. Submit a pull request


