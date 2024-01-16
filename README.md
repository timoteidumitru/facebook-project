# Facebook App

This Java application simulates a social networking service, allowing users to manage profiles, friends, messages, and more, while also providing login and registration functionality. The project is structured for collaborative development, allowing team members to focus on specific features.

## Features in Development

- **Login and Registration**: (Complete) Enables users to register for an account and login.
- **Friends**: (Complete) Allows users to add and manage friends.
- **Messages**: (Complete) Users can manage messages.
- **Profile**: (In Progress) Allows users to create and manage their profiles.
- **Posts**: (Complete) Allows users to create posts.
- **Groups**: (Complete) Allows users to manage groups.

## Project Structure

- **controller**: Houses controllers for handling user interactions.
  - `UserController`: Manages user-related actions. (Complete)
  - `FriendsController`: Manages friend-related interactions. (Complete)
  - `MessagesController`: Handles message-related operations. (Complete)
  - `ProfileController`: Handles profile-related operations. (In Progress)
  - `PostsController`: Handles posts-related operations. (Complete)
  - `GroupsController`: Handles groups-related operations. (Complete)
- **dao**: Contains Data Access Objects for database interactions.
  - `UserDAO`: Manages user data transactions. (Complete)
  - `FriendsDAO`: Handles friend data transactions. (Complete)
  - `MessagesDAO`: Manages message data transactions. (Complete)
  - `ProfileDAO`: Manages profile data transactions. (In Progress)
  - `PostsDAO`: Manages posts data transactions. (Complete)
  - `GroupsDAO`: Manages groups data transactions. (Complete)
- **ui**: For user interface components.
  - `FriendsUI`: Main application page. (Complete)
  - `MessagesUI`: Handles messages UI components. (Complete)
  - `LoginUI`: Handles login UI components. (Complete)
  - `RegisterUI`: Handles register UI components. (Complete)
  - `ProfileUI`: Handles profile UI components. (In Progress)
  - `PostsUI`: Handles posts UI components. (Complete)
  - `GroupsUI`: Handles groups UI components. (Complete)
- **exceptions**: Custom exception classes.
  - `InvalidEmailFromat`: Handles format of email standard. (Complete)
  - `MessagesValidation`: Handles messages validation. (Complete)
  - `UserIO`: Handles user inputs. (Complete)
  - `UserNotFound`: Handles missing of the user seeking. (Complete)
- **model**: Model classes representing the data structure.
  - `User`: User model. (Complete)
  - `Friends`: Friends model. (Complete)
  - `Messages`: Messages model. (Complete)
  - `Profile`: Profile model. (In Progress)
  - `Posts`: Posts model. (Complete)
  - `Groups`: Groups model. (Complete)
- **services**: Service classes for business logic.
  - `UserService`: Business logic for user operations. (Complete)
  - `FriendService`: Manages friend-related business logic. (Complete)
  - `MessageService`: Handles message-related business logic. (Complete)
  - `Profile`: Handles profile-related business logic. (In Progress)
  - `Posts`: Handles posts-related business logic. (Complete)
  - `Groups`: Handles groups-related business logic. (Complete)
- **resources**: Resources like message and user data files.
  - `friends.txt`: Contains application friends. (Added)
  - `messages.txt`: Contains application messages. (Added)
  - `users.txt`: Stores user data. (Added)
  - `profile.txt`: Stores user data. (Added)
  - `posts.txt`: Stores posts data. (Added)
  - `groups.txt`: Stores groups data. (Added)

## Prerequisites

- Java 8 or later.
- A Java Integrated Development Environment (IDE) like IntelliJ IDEA or Eclipse.

## Installation

1. Clone the repository: git clone https://github.com/timoteidumitru/facebook-project
2. Open the project in your Java IDE.
3. Configure the project by setting resources folder as resources root.

## Contributions to project: 
#### Simida Rodila
#### Cristina Lerint
#### Flavi Coman
#### Timotei Dumitru

## Module Status

- **Login/Register**: Complete
- **Messages**: Complete
- **Friends**: Complete
- **Profile**: In Progress
- **Posts**: Complete
- **Groups**: Complete
