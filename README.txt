=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
PennKey: 14003485
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Array: This is pretty obvious. The game contains a 2D array of cells. This naturally represents the game board.

  2. File I/O: The game can export the state of the board to a file. The player can then import the game.
  The player can play a continuous game over the time.Also, to reset the board, the game imports a default file.

  3. Recursion: In accordance with Minesweeper's rules, when the player presses on a cell with a value of 0,
  the program has to uncover any surrounding non-bomb cells nearby. Therefore, the program uses recursion to uncover
  non-bomb cells up until it reaches a bomb cell. Recursion is adaptive, meaning it will call on itself until it reaches
  a bomb no matter where the recursive file is called.. Sometime like a for-loop would not be able to achieve this.

  4. JUnit: I test how the board works to using JUnit. To achieve this, we separate the model from the view of the game.

===============================
=: File Structure Screenshot :=
===============================
- Include a screenshot of your project's file structure. This should include
  all of the files in your project, and the folders they are in. You can
  upload this screenshot in your homework submission to gradescope, named 
  "file_structure.png".

  ok

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
 1. Cell: This represents the individual cell within the board of the game.
 2. GameBoard: This represents the board for MineSweeper. It contains a 2d array of cells.
 3. GameLogic: This is the controller of the game. It handles user interaction like imports and user clicks.
 4. Run: This is the runnable part of the game. Also, it handles the ESG>

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  I thought it was pretty straight forward. I had some issues with implementing importing/exporting, but
  the fundamental design made sense.


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  I think the separation is good. There is some mix between the controller and model in the GameLogic, but otherwise
  it works. I think encapsulation is good, there are some issues with getters but those getters are only for the
  JUnit test.



========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.
  https://colorcodes.io/red/light-red-color-codes/ - RGB colours
  https://www.w3schools.com/java/java_files_create.asp - info on reading and writing
  https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html - info on message popup
