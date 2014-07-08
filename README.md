TicTacToe
=============

This is my fork of hiragana's original program.

It's mostly to show how I would do this using JLabels.  Let me know if you find poorly designed code.

One of the key things is to separate the data and the graphical representation of the data.  
To wit, the JLabels reflect the contents of the board[] array. I never get information from the JLabels.
The values for X and O are set up to be +1 and -1 so that I can easily and quickly check the board array to see if someone has won. (The row/column/diagonal must add up to +3 or -3).

**Update**

Tictactoe3.java has now been uploaded.  It is graphics based and uses the paintComponent() method of JPanel to make the board instead of GUI components.

This is how I do any sort of board or grid based game.  It's a lot easier and more flexible than making arrays of labels or buttons.
