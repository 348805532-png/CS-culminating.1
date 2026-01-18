- Tic Tac Toe - Class Overview

Classes

- Main.java
This file contains the main game loop. It has many variables, the important ones being sc, the Scanner used for the game input and r being a Random object for sourcing random numbers from the Java standard library. We also call upon different classes to manage different parts of game logic.

- Board.java
This file contains a class for the board. The class is heavily commented so a readme section is not required.

- AI.java
This file contains logic for the AI. It is coded purely functionally and requires no internal state. The game state is passed as an argument and it is not modified. It returns the next move.

- Main_Menu.java
This file contains code to show the main menu. It shows a simple greeting, dialog and returns a Boolean on whether the game is being played via AI or with another player.
