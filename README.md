# ❓ Number Guessing Game
This is a full-stack web application with a [Java Spring Boot](https://spring.io) backend and [React](https://reactjs.org) frontend. 

## ❒ Table of Content 
- [How to Run This Application](#how-to-run-this-application)
- [Thought Process](#thought-process)
- [Backend Walkthrough](#backend-walkthrough)
    - [BE Folder Structure](#be-folder-structure)
    - [Endpoints and API Contract](#endpoints-and-api-contract)
- [Frontend Walkthrough](#frontend-walkthrough)
    - [UI and Backend Interaction](#ui-and-backend-interaction)
- [Next Steps](#next-steps)

## How to Run This Application
To run this application on your local machine please follow the steps below:

1. in your terminal enter: 
`git clone https://github.com/lbbdeveloper/guessing-game.git`
2. `cd guessing-game` to go into the project folder
3. `cd backend` to go into the backend folder. This is a Java and Maven Project. 
    - If you have IntelliJ or other type of Java IDEs installed. Click Maven install to install backend dependencies. 
    - Once installed, simply click run to run the backend server. 
4. In another terminal, `cd frontend` to go into the frontend folder. This is a React and Javascript Project.  
    - `npm i` to install required dependencies
    - `npm run start` to run frontend application. It should automatically open the UI in your web browser. If not, go to http://localhost:3000

## Thought Process
### ERD:

- Major Entities in this project:
    - Player
    - Game
    - Result
    - Guess
    - Feeback

### Data Storage: <br/>
One of the next steps for this project is to implement an external database (see [Next Steps](#next-steps)). Currently, this application is storing data in two Maps. One for Game Data, one for Result Data. These two entities are saperated because frontend can access Game object during gameplay. But user should not be able to see the result until game is finished. Both Maps uses gameID as the key so that the 

## Backend Walkthrough
Backend uses [Java version 11](https://www.java.com/en/), [Spring Boot](https://spring.io), [Maven](https://maven.apache.org/), and [Lombok](https://projectlombok.org/). 
### BE Folder Structure:
- entity: Stores major entity classes as indicated in the [ERD](#erd)
- data: Stores Game and Results in Maps as indicated in [Data Storage](#data-storage-br)
- services: Includes major functionalities
- controller: Exposes endpoints to frontend and calls related service function. For details see [Endpoints and API Contact](#endpoints-and-api-contract)

### Endpoints and API Contract
When running locally, all backend API endpoints start with http://localhost:8080
#### POST /game/start
Start the game and return gameID as String
- sample req.body: 
```json
{
    "username": "user"
}
```
- sample response:
```json
59432
```

#### POST /game/play/{id}
Take in an user's input and return Guess object with feeback. 
- sample req.body: 
```json
{
    "playerAnswer": [5,7,1,6]
}
```
- sample response:
```json
{
    "playerAnswer": [
        5,
        7,
        1,
        6
    ],
    "feedback": {
        "correctNum": true,
        "correctNumAndLocation": false,
        "winner": false
    }
}
```

#### GET /game/game-status/{id}
A GET request that returns current Game object which includes game history, total attemps remaining, ect. 
- sample response:
```json
{
    "id": "46058",
    "player": {
        "username": "user"
    },
    "gameStatus": "In Progress",
    "attemptsRemaining": 8,
    "winner": false,
    "history": [
        {
            "playerAnswer": [
                5,
                7,
                1,
                6
            ],
            "feedback": {
                "correctNum": true,
                "correctNumAndLocation": false,
                "winner": false
            }
        },
        {
            "playerAnswer": [
                3,
                7,
                1,
                6
            ],
            "feedback": {
                "correctNum": true,
                "correctNumAndLocation": false,
                "winner": false
            }
        }
    ]
}
```

#### GET /game/game-answer
This is a debugging endpoint. Frontend is not connected to this endpoint. This is used for debugging purposes only. Returns all the game results. 
- sample response:
```json
{
    "19870": {
        "answer": [
            6,
            2,
            0,
            5
        ],
        "answerSet": [
            0,
            2,
            5,
            6
        ]
    },
    "46058": {
        "answer": [
            1,
            6,
            3,
            4
        ],
        "answerSet": [
            1,
            3,
            4,
            6
        ]
    }
}
```

## Frontend Walkthrough
Frontend uses [React](https://reactjs.org/), [axios](https://axios-http.com/docs/intro), and [Material UI](https://mui.com/). 
### UI and Backend Interaction
1. Welcome Page shows a login form that ask for username:

2. form is disabled until user input their name:

3. After user submits their username, the page display a start  button that will direct the user to game play page. 

4. 

5. 

## Next Steps