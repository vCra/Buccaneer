package buccaneer.helpers;

import buccaneer.cards.CrewCard;
import buccaneer.enumData.Direction;
import buccaneer.main.GameBoard;
import buccaneer.main.GameSquare;
import buccaneer.main.Player;
import buccaneer.main.Ship;
import buccaneer.ports.Port;
import org.junit.Test;

import java.util.ArrayList;

import static buccaneer.enumData.CardColor.Black;
import static buccaneer.enumData.CardColor.Red;
import static buccaneer.enumData.Direction.*;
import static buccaneer.helpers.DirectionHelper.isSameDirection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @PositionHelper.java
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 * A collection of static methods that can help with positions, such as checking if a position is
 * an island, getting grid IDs from Positions etc...
 * @author aaw13
 * @version 1.0
 */

//TODO: Javadoc
public class PositionHelper {
    /**
     *  Given the ship returns an ArrayList of possible moves
     *  that a ship can take.
     * @param s - Current ship
     * @return the ArrayList of possible moves
     */
    public static ArrayList<Position> getAvailableMoves(Ship s) {
        ArrayList<Position> list = new ArrayList<>();
        int moves = 0;
        Position currentPos = s.getLocation();

        while (s.getOwner().getMoveStrength() > moves) {
            currentPos = DirectionHelper.getNextPos(currentPos, s.getDirection());
            if (currentPos.isIsland() || currentPos.isEdge()) {
                break;
            } else {
                list.add(currentPos);
                moves++;
            }
        }
        return list;
    }
    /**
     * Returns the available moves from a port
     * @param s - The current ship
     */
    public static ArrayList<Position> getAvailablePortMoves(Ship s) {
        ArrayList<Position> list = new ArrayList<>();
        for (Direction d : Direction.values()) {
            int moves = 0;
            Position currentPos = s.getLocation();
            while (s.getOwner().getMoveStrength() > moves) {
                currentPos = DirectionHelper.getNextPos(currentPos, d);
                if (currentPos.isIsland() || currentPos.isEdge()) {
                    break;
                } else {
                    list.add(currentPos);
                    moves++;
                }
            }
        }

        return list;
    }

    /**
     * Returns if the location contains a port
     * @param pos - Current position
     * @param board - The game board
     * @return true if the location is a port
     */
     static boolean isPort(Position pos, GameBoard board){
        for (Port p : board.getPorts()){
            if (p.getLocation().equals(pos)) {
                return true;
            }
        }
        return false;
    }
    /**
     *  Given the coordinates x and y checks if the position is on the edge of the board
     *  and returns true or false accordingly.
     * @param x the x value to check
     * @param y the y value to check
     * @return true if it is an edge
     */
    private static boolean isEdge(int x, int y) {
        return (x < 1 || x > 20) || (y < 1 || y > 20);
    }

    /**
     *  Given coordinates x and y checks if the position is an island and
     *  returns true or false accordingly.
     *
     * @param x the x value to check
     * @param y the y value to check
     * @return true if it is an island
     */
    private static boolean isIsland(int x, int y) {
        return (isFlatIsland(x,y)||isPirateIsland(x,y)||isTreasureIsland(x,y));
    }

    public static boolean isFlatIsland(int x, int y){
        if (x >= 2 && x <= 4) {
            if (y >= 16 && y <= 19) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPirateIsland(int x, int y){
        if (x >= 17 && x <= 19) {
            if (y >= 2 && y <= 5) {
                return true;
            }
        }
        return false;
    }

    public static boolean isTreasureIsland(int x, int y) {
        if (x >= 9 && x <= 12) {
            if (y >= 9 && y <= 12) {
                return true;
            }
        }
        return false;
    }

    /**
     *  Given the position and a board to check against,
     *  checks if the position contains another ship
     *
     */
    public static boolean isShip(Position position, GameBoard board){
        return board.getSquareAt(position.getX(), position.getY()).containsShip();
    }

    /**
     *  Given position objects checks if it's an island and
     *  returns true or false accordingly.
     * @param position
     * @return
     */
    static boolean isIsland(Position position) {
        return isIsland(position.getX(), position.getY());
    }

    /**
     *  Given the position checks if it's on the edge of the board
     *  and returns true or false accordingly.
     * @param position
     * @return
     */
    static boolean isEdge(Position position) {
        return isEdge(position.getX(), position.getY());
    }

    /**
     * check if the clicked position is next to the ship, and if it is, is it facing in the
     * same direction
     * @return true if the ship should turn, else false
     *
     */
    public static boolean shouldTurn(Ship ship, Position pos){
        boolean a = isSameDirection(ship.getLocation(), pos, ship.getDirection());
        boolean b = isNextTo(ship.getLocation(), pos);
        return !a&&b;
    }

    /**
     * Is the move from pos1 to pos2 valid (e.g. it doesn't pass through islands
     * @param ship the ship to move
     * @param pos the end pos
     * @return true if the move is valid
     */
    public static boolean moveIsValid(Ship ship, Position pos) {
        return PositionHelper.getAvailableMoves(ship).contains(pos);
    }

    /**
     * Checks if the move from the port is valid
     * @param ship - The current ship
     * @param pos2 - The position the ship is moving to
     * @return
     */
    public static boolean moveFromPortIsValid(Ship ship, Position pos2){
        return PositionHelper.getAvailablePortMoves(ship).contains(pos2);
    }

    public static boolean moveIsValid(Position pos, GameBoard gameBoard) {
        return !isIsland(pos) && !isEdge(pos) && !isShip(pos, gameBoard);
    }
    /**
     *  Given position returns a GridID of the GameSquare in the Grid.
     * @param pos - The position that is
     * @return
     */
    public static int positionToGridID(Position pos){
        return ((pos.getX()-1)+((20-pos.getY())*20));
    }

    /**
     * Given coordinates x and y creates a Position object.
     * @param x - x coordinate
     * @param y - y coordinate
     * @return new Position
     */
    public static Position gridChange(int x, int y) {
        return new Position(x + 1, 20 - (y));
    }

    /**
     * Checks if given positions are next to each other.
     * @param pos1 - 1st position
     * @param pos2 - 2nd position
     * @return isPlusOrMinus method
     */
    public static boolean isNextTo(Position pos1, Position pos2) {
        return isPlusOrMinus(pos1.getX(), pos2.getX()) && isPlusOrMinus(pos1.getY(),pos2.getY());
    }

    /**
     * Checks if the inputted integers are plus or minus each other
     * @param a - 1st integer
     * @param b - 2nd integer
     * @return a==b || a==(b-1) || a==(b+1)
     */
    private static boolean isPlusOrMinus(int a, int b){
        return a == b || a == (b - 1) || a == (b + 1);
    }

    /**
     * If the ship has passed though another player then returns the position of that other player
     * @param s The Ship moving
     * @param endPos The ships intended location
     * @param board The game board
     * @return Returns the position of the other ship that has been moved through
     */
    public static ArrayList<Position> moveThroughPlayer(Ship s, Position endPos, GameBoard board) {
        Position currentPos = s.getLocation();
        ArrayList<Position> postions = new ArrayList<>();

        while (!currentPos.equals(endPos)) {
            currentPos = DirectionHelper.getNextPos(currentPos, s.getDirection());
            if (currentPos.equals(endPos)) {

            } else {
                if (currentPos.isIsland() || currentPos.isEdge()) {
                    break;
                } else if (currentPos.containsShip(board)) {
                    postions.add(currentPos);
                }
            }
        }
        return postions;
    }

    /**
     * The total distance traveled from one position to another
     * @param pos1 - First position
     * @param pos2 - Second position
     * @return the distance of pos1 to pos2
     * @see Math
     */
    public static int distanceTraveled(Position pos1, Position pos2){
        return Math.max(Math.abs(pos1.getX() - pos2.getX()), Math.abs(pos1.getY() - pos2.getY()));
    }

    @Test
    public void shipstrengthTest()          //????  Change Port
    {

        int size1 = 0;
        int size2 = 0;

        GameBoard gb = new GameBoard();
        CrewCard cd = new CrewCard(1, Red,3);
        CrewCard cd1 = new CrewCard(2,Red,3);
        CrewCard cd2 = new CrewCard(3,Red,3);
        CrewCard cd3 = new CrewCard(4,Red,1);
        Player p1 = new Player(0,"1");
        Player p2 = new Player(1,"2");

        Ship s1= new Ship(p1);
        Ship s2= new Ship(p2);

        s1.setLocation(new GameSquare(7,10,gb));
        s2.setLocation(new GameSquare(5,5,gb));


        s1.setDirection(N);
        s2.setDirection(E);

        p1.addCrewCard(cd);
        p1.addCrewCard(cd1);
        p1.addCrewCard(cd2);
        p1.addCrewCard(cd3);

        p2.addCrewCard(cd2);

        PositionHelper.getAvailableMoves(s1);
        PositionHelper.getAvailableMoves(s2);

        size1 = PositionHelper.getAvailableMoves(s1).size();
        size2 = PositionHelper.getAvailableMoves(s2).size();

        assertTrue(size1 > 0);                                      //?????????????????//
        assertTrue(size2 > 0);                                      //?????????????????//
        // assertEquals(size1, 9);
        //assertEquals(size2,3);




    }
    @Test
    public void shipstrengthinportTest()          //????  Change Port
    {

        int size1 = 0;
        int size2 = 0;

        GameBoard gb = new GameBoard();
        CrewCard cd = new CrewCard(1, Red,3);
        CrewCard cd1 = new CrewCard(2,Red,3);
        CrewCard cd2 = new CrewCard(3,Red,3);
        CrewCard cd3 = new CrewCard(4,Red,1);
        Player p1 = new Player(0,"1");
        Player p2 = new Player(1,"2");

        Ship s1= new Ship(p1);
        Ship s2= new Ship(p2);

        s1.setLocation(new GameSquare(1,14,gb));
        s2.setLocation(new GameSquare(1,7,gb));


        s1.setDirection(E);
        s2.setDirection(E);

        p1.addCrewCard(cd);
        p1.addCrewCard(cd1);
        p1.addCrewCard(cd2);
        p1.addCrewCard(cd3);

        p2.addCrewCard(cd2);

        PositionHelper.getAvailableMoves(s1);
        PositionHelper.getAvailableMoves(s2);

        size1 = PositionHelper.getAvailablePortMoves(s1).size();
        size2 = PositionHelper.getAvailablePortMoves(s2).size();

        assertEquals(size1, 37);
        assertEquals(size2, 15);




    }

    @Test
    public void checkeachplayersmoves()
    {
        int p1size = 0;
        int p2size = 0;
        int p3size = 0;
        int p4size = 0;

        GameBoard gb = new GameBoard();
        CrewCard cd1 = new CrewCard(1, Red,3);
        CrewCard cd2 = new CrewCard(2, Red,2);
        CrewCard cd3 = new CrewCard(3, Red,1);
        CrewCard cd4 = new CrewCard(4, Red,3);
        CrewCard cd5 = new CrewCard(5, Red,2);

        CrewCard cd6 = new CrewCard(5, Black,2);
        CrewCard cd7 = new CrewCard(5, Black,3);

        Player p1 = new Player(0,"1");
        Player p2 = new Player(1,"2");
        Player p3 = new Player(2,"3");
        Player p4 = new Player(3,"4");
        Ship s1= new Ship(p1);
        Ship s2= new Ship(p2);
        Ship s3= new Ship(p3);
        Ship s4= new Ship(p4);
        //ports.add(new Port("London", getSquareAt(1, 14)));


        s1.setLocation(new GameSquare(2,14,gb));
        s2.setLocation(new GameSquare(20,8,gb));
        s3.setLocation(new GameSquare(6,2,gb));
        s4.setLocation(new GameSquare(14,1,gb));

        s1.setDirection(E);
        s2.setDirection(N);
        s3.setDirection(NE);
        s4.setDirection(S);

        p1.addCrewCard(cd1);
        p2.addCrewCard(cd2);
        p3.addCrewCard(cd1);
        p4.addCrewCard(cd3);
        p1.addCrewCard(cd1);
        p2.addCrewCard(cd5);
        p3.addCrewCard(cd1);
        p4.addCrewCard(cd4);
        p1.addCrewCard(cd7);
        p2.addCrewCard(cd3);
        p3.addCrewCard(cd7);
        p4.addCrewCard(cd2);
        p1.addCrewCard(cd3);
        p2.addCrewCard(cd6);
        p3.addCrewCard(cd1);
        p4.addCrewCard(cd2);

        //PositionHelper.getAvailableMoves(s1);

        p1size = PositionHelper.getAvailableMoves(s1).size();
        p2size = PositionHelper.getAvailableMoves(s2).size();
        p3size = PositionHelper.getAvailableMoves(s3).size();
        p4size = PositionHelper.getAvailableMoves(s4).size();

        assertEquals(p1size, 10);
        assertEquals(p2size, 7);
        assertEquals(p3size, 12);
        assertEquals(p4size, 0);


    }
}
