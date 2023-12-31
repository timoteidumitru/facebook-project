# Facebook App

This Java application simulates a social networking service, allowing users to manage profiles, friends, messages, and more, while also providing login and registration functionality. The project is structured for collaborative development, allowing team members to focus on specific features.

## Features in Development

- **Login and Registration**: (Complete) Enables users to register for an account and log in.
- **Friends**: (Complete) Allows users to add and manage friends.
- **Messages**: (Complete) Users can manage messages.
- **Profile**: (In Progress) Allows users to create and manage their profiles.
- **Posts**: (In Progress) Allows users to create posts.

## Project Structure

- **facebook_app**: The main project directory.
- **controller**: Houses controllers for handling user interactions.
  - `UserController`: Manages user-related actions. (Complete)
  - `FriendsController`: Manages friend-related interactions. (Complete)
  - `MessagesController`: Handles message-related operations. (Complete)
  - `ProfileController`: Handles profile-related operations. (To be added)
  - `PostsController`: Handles posts-related operations. (To be added)
- **dao**: Contains Data Access Objects for database interactions.
  - `UserDAO`: Manages user data transactions. (Complete)
  - `FriendsDAO`: Handles friend data transactions. (Complete)
  - `MessagesDAO`: Manages message data transactions. (Complete)
  - `ProfileDAO`: Manages profile data transactions. (To be added)
  - `PostsDAO`: Manages posts data transactions. (To be added)
- **entities**: Holds entity classes representing the data model.
  - `User`: Represents a user entity. (Complete)
  - `Friends`: Represents friend relationships. (Complete)
  - `Message`: Represents message entities. (Complete)
  - `Profile`: Represents profile entities. (To be added)
  - `Posts`: Represents posts entities. (To be added)
- **ui**: For user interface components.
  - `FriendsUI`: Main application page. (Complete)
  - `MessagesUI`: Handles messages UI components. (Complete)
  - `LoginUI`: Handles login UI components. (Complete)
  - `RegisterUI`: Handles register UI components. (Complete)
  - `ProfileUI`: Handles profile UI components. (To be added)
  - `PostsUI`: Handles posts UI components. (To be added)
- **exceptions**: Custom exception classes. (To be added)
- **model**: Model classes representing the data structure.
  - `User`: User model. (Complete)
  - `Friends`: Friends model. (Complete)
  - `Messages`: Messages model. (Complete)
  - `Profile`: Profile model. (To be added)
  - `Posts`: Posts model. (To be added)
- **services**: Service classes for business logic.
  - `UserService`: Business logic for user operations(Complete).
  - `FriendService`: Manages friend-related business logic(Complete).
  - `MessageService`: Handles message-related business logic(Complete).
  - `Profile`: Handles profile-related business logic. (To be added)
  - `Posts`: Handles posts-related business logic. (To be added)
- **resources**: Resources like message and user data files.
  - `friends.txt`: Contains application friends. (Complete)
  - `messages.txt`: Contains application messages. (Complete)
  - `users.txt`: Stores user data. (Complete)
  - `profile.txt`: Stores user data. (To be added)
  - `posts.txt`: Stores user data. (To be added)

## Prerequisites

- Java 8 or later.
- A Java Integrated Development Environment (IDE) like IntelliJ IDEA or Eclipse.

## Installation

1. Clone the repository: git clone https://github.com/flavicoman/facebook-project
2. Open the project in your Java IDE.
3. Configure the project build path to include all necessary libraries.

## Contributing

We warmly welcome contributions to the Facebook App project. To contribute:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Commit your changes and push them to your fork.
4. Submit a pull request detailing the changes made.

## Module Status

- **Login/Register**: Complete
- **Messages**: Complete
- **Friends**: Complete
- **Profile**: In Progress
- **Posts**: In Progress

## Note to Contributors

I've cleared the entry point of the application (`Application.java`) to enable team members to work on their preferred features independently. Please ensure that your contributions are well-documented and tested before submitting a pull request.

