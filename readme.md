# GooseGame Kata in Kotlin

Solution of GooseGame Kata in Kotlin, you can find description here -> https://github.com/xpeppers/goose-game-kata


### 8. Not found command (Optional Step)
When I type a different command the system will respond with an error.

**Scenarios:**
1. Not Found
   ```js
   When the user writes: "unexisting command"
   the system responds: "Error: command not found"
   ```
   

### 9. Add Players with an http route (Optional Step)
As a player, I want to invoke an http API in order to add a new player

**Scenarios:**
1. Add Player
   ```
   When the user runs: "POST /players/add" with body '{"name":"pippo"}'
   the system responds with "200" status code and body '{"players":["pippo"]}'
   ```

2. Add Player duplicated player
   ```
   When the user runs: "POST /players/add" with body '{"name":"pippo"}'
   the system responds with "409" status code and body '{"error": "Pippo: Already existing player"}'
   ```

### 10. Move Players with an http route (Optional Step)
As a player, I want to invoke an http API in order to move a player

**Scenarios:**
1. Move a Player from starting point
    ```
    Assuming there is a player "Pippo"
    Assuming that the dice get 1 and 2
    When the user runs: "GET /players/Pippo/rolls"
    the system responds with "200" status code and body:
    
    {
       "Pippo": {
         "rolls": [1, 2],
         "moves": {"from": "Start", "to": "3"},
         "status": ""
       }
    }
    ```
    
2. Move a Player to Win space
    ```
    Assuming there is a player "Pippo"
    Assuming the player is in space 60
    Assuming that the dice get 1 and 2
    When the user runs: "GET /players/Pippo/rolls"
    the system responds with "200" status code and body:
    
    {
       "Pippo": {
         "rolls": [1, 2],
         "moves": {"from": "60", "to": "63"},
         "status": "Wins!"
       }
    }
    ```
3. Move a Player to Bounce
    ```
    Assuming there is a player "Pippo"
    Assuming the player is in space 60
    Assuming that the dice get 3 and 2
    When the user runs: "GET /players/Pippo/rolls"
    the system responds with "200" status code and body:
    
    {
       "Pippo": {
         "rolls": [3, 2],
         "moves": {"from": "60", "to": "61"},
         "status": "Bounces!"
       }
    }
    ```
3. Move a Player to Bridge
    ```
    Assuming there is a player "Pippo"
    Assuming that the dice get 4 and 2
    When the user runs: "GET /players/Pippo/rolls"
    the system responds with "200" status code and body:
    
    {
       "Pippo": {
         "rolls": [4, 2],
         "moves": {"from": "Start", "to": "12"},
         "status": "Jumps!"
       }
    }
    ```
3. Move a Player to The Goose
    ```
    Assuming there is a player "Pippo"
    Assuming that the dice get 3 and 2
    When the user runs: "GET /players/Pippo/rolls"
    the system responds with "200" status code and body:
    
    {
       "Pippo": {
         "rolls": [3, 2],
         "moves": {"from": "Start", "to": "10"},
         "status": "moves again!"
       }
    }
    ```

## 11. Play the game from both Cli and Http at the same time

1. Add Player
```
If there is no participant
the user writes to cli: "add player Pippo"
the cli system responds: "players: Pippo"
the user sends an http request "POST /players/add" with body '{"name":"Pluto"}'
the http system responds with "200" status code and body '{"players":["Pippo","Pluto"]}'
```

2. Move Player
```
If there is one participant Pippo
assuming that the dice get 2 and 1
the user writes to cli: "move Pippo"
the cli system responds: "Pippo rolls 2, 1. Pippo moves from Start to 3"
assuming that the dice get 1 and 3
the user sends an http request "GET /players/Pippo/rolls"
the http system responds with "200" status code and body:

{
  "Pippo": {
    "rolls": [1, 3],
    "moves": {"from": "3", "to": "7"},
    "status": ""
  }
}
```