# Helsinki City Bike Application

![Tram and bikeshare station](frontend-react/src/images/Tram_and_bikeshare_station_(42309761411).jpg)

Photo by Eric Fischer, CC BY 2.0 <https://creativecommons.org/licenses/by/2.0>, via Wikimedia Commons

## Table of Contents
***
1. [What does this application do?](#general-info)
2. [Technologies](#technologies)
3. [Installation](#installation)
4. [Database](#database)
5. [Structure of the Application](#Structure)
6. [Backend](#Backend)

### What does this application do?
***
This Helsinki City Bike application is based on the data of the Helsinki City Bike trips and stations on summer 2021. Users and list, sort and search for the trips and stations as well as find detailed information about the stations. For example it's possible to find out, which was the longest trip made or the most popular station in the summer 2021 :)

### Technologies
***
A list of technologies used within the project:

* [JDK](https://openjdk.org/): Version 17
* [Spring Boot](https://spring.io/): Version 3.0.6
* [MySQL](https://mysql.com): Version 8.0.32
* [React](https://react.dev/): Version 18.0.2
* [React Router](https://reactrouter.com/): Version 6.11.1
* [Node.js](https://nodejs.org/): Version 18.14.1
* [TablePlus](https://tableplus.com/): For importing big csv.files and mapping them correctly
* [Insomnia](https://insomnia.rest/): For testing and interacting with backend endpoints and validating API functionality.

This application was made on Windows 11.
The backend of the application is built using Spring Boot, a Java-based framework, with JDK 17. It utilizes MySQL as the database for data storage.
The frontend of the application is built using React and utilizes React Router for routing functionality. Node.js is required as a prerequisite for development and building the React application.

### Installation and running the application
***
1. Clone the repository to your local machine.
2. Navigate to the project directory.
3. Install the necessary dependencies for the backend using the package manager of your choice (for example Maven).
4. Configure the database connection in the application properties file located in the backend directory.
5. Run the project using the provided build command.
6. Install the necessary dependencies for the frontend using the package manager of your choice (for example NPM).
7. Run the frontend development server.
8. The backend will be running on http://localhost:8080, and the frontend will be running on http://localhost:3000.
