# restaurant-picker-app

Introduction:

There is frequently a need for teams to collectively decide on a location to head to for lunch.
While each team member has an idea in mind, not everyone gets heard in the commotion and much
time is spent to arrive at what may as well be a random choice.


Solution:

This API will be called by the frontend, where the user will individually provide their restaurant preferences.
Frontend will provide a list of restaurants to the Backend separated by comma


The project consist of two parts:
1. restaurant-picker-app-fe - The frontend UI, created on React
2. restaurant-picker-app-be - The backend, created in Spring Boot

Further information can be found on the Wiki page: https://github.com/fherrera-ph/restaurant-picker-app/wiki


Running the frontend project locally - in the restaurant-picker-app-fe folder, run the command:
>npm start

Building the frontend Dockerfile - in the restaurant-picker-app-fe folder, run the command:
>docker build -t restaurant-picker-app-fe .

Running the frontend project's Docker image - in the restaurant-picker-app-fe folder, run the command:
>docker build -t my-react-app --build-arg RESTAURANT_BE_API_URL=http://localhost:8080 .

Running the backend project locally - in the restaurant-picker-app-be folder, run the command:
>gradle bootRun

Building the backend Dockerfile - in the restaurant-picker-app-be folder, run the command:
>docker build -t restaurant-picker-be:0.0.1-SNAPSHOT .

Running the backend project's Docker image - in the restaurant-picker-app-be folder, run the command:
>docker run -p 8080:8080 restaurant-picker-be:0.0.1-SNAPSHOT





