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
    - If you have IntelliJ or other types of Java IDEs installed. Click Maven install to install backend dependencies. 
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
One of the next steps for this project is to implement an external database (see [Next Steps](#next-steps)). Currently, this application is storing data in two Maps. One for Game Data, one for Result Data. These two entities are separated because frontend can access the Game object during gameplay. But the user should not be able to see the result until the game is finished. Both Maps use gameID as the key so that they can easily be found.

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
Take in a user's input and return the Guess object with feedback. 
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
A GET request that returns the current Game object which includes game history, total attempts remaining, etc. 
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

2. Form is disabled until user input their name:

3. After a user submits their username, the page display a start  button that will direct the user to the game play page. 

4. User will be able to input a four number array to guess

5. As user submit their guess, they can see feedback, game history as well as attempts remaining 

6. If the user guesses the correct answer, they will see a winner screen

7. If their attempts run out, they will see a failed screen

## Next Steps
There are some of the potential future steps this application can take:

1. Sort and rank user's performance based on attempts made:
    - Since GameData stores each Game's information, which includes `username` and `attemptsRemaining`. We can sort Games based on `attemptsRemaining`. 
    - First of all, we need to select games that successfully guessed all the numbers. This can be done by selecting Games with its winner variable set to `true` (we will call these games **Winner Games**).
    - Then We can create an ArrayList to include all the **Winner Games** and sort them using `Collections.sort` and JAVA Comparator on attempsRemaining.
    If there are  winner Games that have the same number of `attemptsRemaining`, we can sort them alphabetically.
    - After that, we will print GameId or Player's username.
    
2. Multi-player mode:
    - This can potentially be achieved by using **Websocket**. 
    - [Spring](https://spring.io/guides/gs/messaging-stomp-websocket/) has a Websocket module we can potentially use. 
    - Users can join the same session using a unique gameId. Users can take turns guessing the game. Whoever finished the game first is the winner. 

3. Include difficulty level option:
    - this can be achieved by giving the user an option to select different difficulty levels before the game starts. There can be multiple ways to set difficulty levels. For example:
        - level 1: need to guess 4 numbers, each ranging from 0 - 7, within 10 attempts.
        - level 2: need to guess 5 numbers, each ranging from 0 - 8, within 9 attempts.
        - level 3: need to guess 6 numbers, each ranging from 0 - 9, within 8 attempts.
    - We can easily achieve this by updating the size of answer array/set, the range for API answer generation, and attempts allowed in the current code.

4. Give hint to users:
    - Users can select to receive hints such as the following:
        - hint 1: how many numbers the user has guessed correctly in this attempt. these numbers' location does not need to be correct. 
        - hint 2: how many numbers the user has guessed correctly. these numbers' locations need to be correct.
        - hint 3: remove a wrong number from number range, for example tell the user number 3 is not part of final answer, to reduce difficuity.  
    - how to achieve hint functionalities:
        - hint 1: return an int from `checkNumberContains` method from GameService class.
        - hint 2: simply return `re` variable from `checkWinner` method in GameService class.
        - hint 3: write a method in GameService class

5. Create a timer to record how much time the user has spent on guessing.
6. Create a score algorithm based on game difficulty level, time spent, and guess result to calculate a score for each user and publish the ranking in real-time.