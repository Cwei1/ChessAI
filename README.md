pd1-ChessAI-Wei-Wang-Shivraj
============================

Group Members: Cardy Wei, Dionis Wang, and Devendra Shivraj.

Description: A fully functional Chess Game with an interactive Graphical User Interface that allows moves to be made by clicking directly on the Chess pieces, Two-Player Mode that allows two users to play each other, and a Single-Player Mode that allows a user to play against the computer.

Instructions: All of the java files are inside of the directory "Final-Version". Go into this directory and run the command "javac *.java" to compile all java files. After which you will be able to run the program by typing in "java Run".

What Works: The possible moves of all chess pieces follow the standard rules of Chess. The user can play against the computer in Single-Player Mode. Pawns can be upgraded to pieces of higher value once they reach the opponent's side.

What Doesn't Work: Everything works in our version of Chess, however Single-Player Mode is extremely slow due to the large number of moves the computer has to look through and calculate.

Project Highlights: Some of the data structures used in our project include arrays, ArrayLists, Priority Queues, and HashMaps. The ChessAI works by placing all possible moves into a priority queue that sorts them according to how good each move is. A HashMap is used to keep track of the points associated with each piece when making ChessAI calculations.

Full Explanation: After javac *.java and java Run, a pop-up menu will appear, giving the player two options. There is a two-player mode, where you can play another person. In this mode, every time a move is made, the board switches orientation to allow the other player to view the board from the same side, to allow ease of playing. Single-player mode involves a player playing against an AI. The AI is always white, and starts first. When the game begins, wait for the AI to make a move. Then board will then reorient correctly. Everytime you make a move, the AI will calculate the best move, and when it completes calculations, both the move the the player picked and the move the AI picked will then be executed. The AI is very slow, so depending on the computing power of the computer, it can take anywhere from 10 seconds to a minute to move. All chess functionalities are incorporated into the game, such as Castle, Check, Checkmate, and Pawn Promotion. In addition, our program can run prewritten games. In our Final-Version folder, there is a subfolder labeled Games. In this folder are games that we converted an can be displayed in the program. For example, if you want to run the game in g2.txt, type in java Run g2.txt. Adding the name of the file after java Run will play the game out on the GUI. These games are taken from grandmaster matches, and one side usually surrenders before checkmate, which is why these games end before checkmate and may seem incomplete.
