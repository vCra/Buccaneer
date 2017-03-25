#Tests for JUnit


Test CardDeck Chance cards can be imported from file
Test CardDeck can Add cards,
Test CardDeck can remove cards,
Test CardDeck can Shuffle
Test CardDeck is FiFO

Test DirectionHelper positionToDirection returns the correct Direction
Test DirectionHelper isSameDirection checks the direction correctly
Test DirectionHelper getNextPos returns the adjacent value to the position in the correct direction

Test Position getters and setters for X and Y values work
Test Position IsIsland checks if the position is an island correctly
Test Position isPort checks if the position is a port correctly
Test Position isEdge checks if the position is an edge correctly
Test Position equals returns true if two positions are the same and false if they are not
Test Position toString correctly prints the Position as a String

Test PositionHelper getAvailableMoves for a ship moves the correct distance based on players move strength
Test PositionHelper getAvailableMoves for a ship gets the moves for the correct player
Test PositionHelper getAvailableMoves checks for Islands and Edges
Test PositionHelper getAvailablePortMoves does all the above
Test PositionHelper getAvailablePortMoves highlights in the correct 3 directions
Test PositionHelper getAvailablePortMoves handles a ship that is not in a port
Test PositionHelper isPort returns true for positions that contain a port
Test PositionHelper isPort returns false for positions that do not contain a port
Test PositionHelper isEdge returns true for values that are not on the gameboard
Test PositionHelper isEdge returns false for values that are on the gameBoard
Test PositionHelper isIsland returns true for Positions that are islands
~~Test PositionHelper IsIsland returns false for Positions that are not islands~~ @vcra
Test PositionHelper isEdge for Positions rather than a pair of coordinates
Test PositionHelper shouldTurn returns true if a position is next to a ship
Test PositionHelper shouldTurn returns true if the position is in the same direction
Test PositionHelper shouldTurn returns false if any of these are false
Test PositionHelper moveIsValid returns false if a ship moves through an island
Test PositionHelper moveIsValid if the distance is higher than what is allowed by crew cards
Test PositionHelper moveIsValid detects islands
Test PositionHelper positionGridToID returns the correct grid ID based on the position
Test PositionHelper gridChange correctly converts from a normal array to an array 1,1 is the lower left
Test PositionHelper isNextTo returns true if pos1 is adjacent to pos 2
Test PositionHelper isNextTo returns false if it isn't
Test PositionHelper isPlusOrMinus returns true if a number is +1, -1 or equal to num2,
Test PositionHelper isPlusOrMinus returns false if not
Test PositionHelper moveThroughPlayer returns true if a ship has moved through a player.

Test Score can be incremented
~~Test Score checks if a player has won correctly~~ @vcra
Test Score getter and setter for the score works
Test Score toString works

Test TurnTracker correctly gets the current player
Test TurnTracker makes the player who has London as a port go first
Test TurnTracker correctly increments the current turn
Test TurnTracker sets the first turn as turn 1
Test TurnTracker getCurrentTurn correctly gets the current Turn number
Test TurnTracker can get and set GameStates

Test FlatIsland crewCards can be Added and Removed
Test FlatIsland Treasures can be Added and Removed
Test FlatIsland Treasures can be accessed without removal

Test PirateIsland crewCards can be Added and Removed
Test PirateIsland crewCards can be got from the top of the deck

Test TreasureIsland chanceCards can be added and removed
Test TreasureIsland Treasures can be added and removed

Test Port Getter and Setter for Player
Test Port Getter and Setter for Name
Test Port Getter and Setter for Position
Test Port Getting direction of waterFace
Test Port ToString

Test Treasure Getter and Setter for Type
Test Treasure Getter and Setter for Value
Test Treasure Getter and Setter for friendlyName

~~Test that the testing system can test correctly~~ @vcra