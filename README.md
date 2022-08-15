# Maze Game in Java
This project is a terminal based maze game written in Java ☕. The maze  consists of a rectangular  grid, with at  least one  row and  at  least  one  column. There  are  horizontal  and vertical  “walls” and  “doors”  between  some  grid  squares,  and nothing  between other  grid  squares. Each  door  has one  of six  colours  (1–6),  and  can  be  “opened” by  a key  of  a  corresponding  colour.  Keys  can be  found in  certain  grid  squares  around  the  maze. Grid  squares may  also  contain  messages. The  player’s  icon  also occupies  one grid  square  at  any  given  point  in time. The program uses strategy pattern and decorator pattern for the  implementation of the game mechanics and logic. The program can be executed by running`./gradlew run`

#### Example 1
![World One Example](https://i.imgur.com/1GgzoEc.png)

#### Example 2
![World Two Example](https://i.imgur.com/i8pdjih.png)

## Input File
Once the program start, it will ask for an input file which contains the map of the maze (You can place the input file in the root directory, this is already done, check the `world.txt` file). The program will warn if the file is invalid. The input file  is  a  text file containing a  maze structure. The first line contains the maze size:  first the number  of  rows,  then  a  space, then the number  of  columns.  Each  other  line  starts  with  a  code, indicating  what kind  of  information  it  contains,  then  a sequence  of several  integers.  Here  are  the  different  kinds of  codes and  what  they  do,  with  examples  of the  integers that  follow:
| `<object_code>` | Description |
|:--:|:--:|
| S | *Player Starting Position* |
| E | *Player Ending Position* |
| K | *Key Location* |
| WH | *Wall Horizontal* |
| WV | *Wall Vertical*|
| DH | *Door Horizontal*|
|DV | *Door Vertical* |
| M | *Message* |

-------------

| `<object_code>` | X | Y | Colour/Message |
|:--:|:--:|:--:|:--:|
| S | *0* | *3* |
| E | *6* | *1* |
| K | *3* | *5* | *4* |
| WH | *2* | *3* |
| WV | *2* | *3* |
| DH | *2* | *3* | *4* |
| DH | *2* | *3* | *4* |
| M | *3* | *4* | *You step into the light* |

#### Example 1 map file
![World One Map](https://i.imgur.com/kkNNqW6.png)

## Display
The maze game will  be  terminal-based,  making use  of box-drawing characters and ANSI escape codes.

![Box Drawing Character Example](https://i.imgur.com/Dw6FcBO.png)
#### Detailed Representation of the above map
![Map File In-depth ](https://i.imgur.com/uBZdfu8.png)

The above example is a 4x4 maze but in reality it is a 17x9 grid of characters. Each square occupies 3 adjacent characters. The player `P` can only move between the yellow characters which represent a whole square. See the movement example below

![Demonstration](https://i.imgur.com/PA78V5K.gif)


## Game Mechanics
The player can move the position of the player with default keys `N`, `S`, `E`, `W`. These inputs can be changed to whatever you prefer by modifying the `Control.java` file `lines 34, 43, 52, 61 ` To `Move Up Key`, `Move Left Key`, `Move Down Key`, `Move Right Key` respectively.

N - *Move cursor up (North)*<br>
W - *Move cursor left (West)*<br>
S - *Move cursor down (South)*<br>
E - *Move cursor right (East)*<br>

An error message will be shown if the move is invalid. If the player's current position contains a key, it will be immediately picked up and shown to the screen. If the player is currently holding a key to the corresponding door, the player can pass through the door, otherwise an error message will be shown since the player doesn't have the valid key to the door.

When the player reaches the `E`, they win and the game ends.

## How To Run The Game
Open the game directory in the terminal and type `./gradlew run` to run the game. **You need a Linux environment to run the program correctly!** The program can also can work with Windows but please note that ANSI escape characters may not show properly in Windows. Note : Gradle should be installed first. [Click here](https://gradle.org/install/) to see how to install gradle

### Indexed in Turn-It In Global Referencing Scheme

***This project should not be used for any coursework related activity and all codes have been submitted to `Turn-It In global referencing platform`, where usage of this code may be caught for Plagiarism.***
