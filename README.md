TicTacToe
=============

This is my fork of hiragana's original program (which I've completely redone in TicTacToe3)

It's mostly to show how I would do this using JLabels.  Let me know if you find poorly designed code.

One of the key things is to separate the data and the graphical representation of the data.  
To wit, the JLabels reflect the contents of the board[] array. I never get information from the JLabels.
The values for X and O are set up to be +1 and -1 so that I can easily and quickly check the board array to see if someone has won. (The row/column/diagonal must add up to +3 or -3).

# Tictactoe3

**Tictactoe3.java** is the final program.  It is graphics based and uses the paintComponent() method of JPanel to make the board instead of GUI components.

This is how I do any sort of board or grid based game.  It's a lot easier and more flexible than making arrays of labels or buttons.

It allows grids of any size to be made by changing the grid variable. It's not too hard to add something to stop each square from being too small (e.g. less than 20 pixels).

This also allows you to resize the JFrame by dragging it -- everything still works! Cool!  In most games, you would set resizable to false.
