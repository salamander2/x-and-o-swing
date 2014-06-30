TicTacToe
=============

This is my fork of hiragana's original program.

It's mostly to show how I would do this using JLabels.  Let me know if you find poorly designed code.

One of the key things is to separate the data and the graphical representation of the data.  
To wit, the JLabels reflect the contents of the board[] array. I never get information from the JLabels.
The values for X and O are set up to be +1 and -1 so that I can easily and quickly check the board array to see if someone has won. (The row/column/diagonal must add up to +3 or -3).

I've a more modern one that uses the paintComponent() method of JPanel to make the board. I'll upload it later.
