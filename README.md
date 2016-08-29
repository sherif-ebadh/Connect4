<b>Implementation of a Connect4 Web Service</b><br />
* implement a RESTful web service that can create a 2 players connect4 game   
* Validate successive moves on a Connect4 board. 
* It should also be able to recognise and indicate if the connect4 is finished with the current move. 

<h3>DB configuration</h3> 
Default Datasource in the config file : <pre>jdbc:mysql://127.0.0.1:3306/gamedb</pre>

To change the Datasource "Edit the src/mian/resources/applicatio.properties" file with the DB configuration section
DB script also exist in the project directory


<h3>Runing the application</h3>
Use the system cmd for windows or Terminal for Linux, got to the project folder. 
Using Maven you can run the application using mvn spring-boot:run. Or you can build an executable JAR file with mvn clean package and run the JAR by typing: 

<pre>java -jar target/connect4-0.0.1-SNAPSHOT.jar</pre>



<h3>How to use the application  </h3>

register a new players for the game Use the following URL< br/>
    "http://localhost:8080//connect4/createPlayers" Method : POST  < br/>
the request body Format
{"player1Name":"bassem","player2Name":"Ahmed"}
< br/>
<h3>the expected response</h3>
{
    "player2Id": 110,
    "player1Id": 109,
    "gameId": 54
}


To start playing the game use the following URL
< br/>  /connect4/game/{gameID}/player/{playerId}/col/{col}   Method : PUT  < br/>
<h3>where</h3>
<ul>
<li>gameID : is game ID provided in the previous call
<li>playerId  : player Id of the current move - first player is PLayer1 -
<li>col : is the selected Column from the player - The Game board is 6 Rows and & column "Column start from 0 to 6" -
</ul>

<h3>The expected response</h3> 

{
    "isWinner": false,
    "isValidEntry": true,
    "currentBoard": [[0,0,
            0,
            0,
            0,
            0,
            0
        ],
        [
            0,
            0,
            0,
            0,
            0,
            0,
            0
        ],
        [
            0,
            0,
            0,
            0,
            0,
            0,
            0
        ],
        [
            0,
            0,
            0,
            0,
            0,
            0,
            0
        ],
        [
            0,
            0,
            0,
            0,
            0,
            0,
            0
        ],
        [
            0,
            0,
            0,
            0,
            0,
            0,
            109
        ]
    ]
}

The response in Json Format will include the following : 
<ul>
<li>isWinner: True if the the current player move is the winner move</li>
<li>isValidEntry: True if the candidate selected column is valid</li>
<li>currentBoard: current connect4 board after move</li>
</ul>
if the move is valid the player ID will be placed in the first available cell of the selected column

Note : I used simple Objects as the application is a POC 
