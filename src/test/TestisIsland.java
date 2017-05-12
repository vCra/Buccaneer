import buccaneer.helpers.Position;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Tests Islands, including if the positions are correct, some more stuff etc
 * Island 1 - Flat Island
 * Island 2 - Pirate Island
 * Island 3 - Treasure Island
 **/
public class TestisIsland {

    @Test
    public void testIsland1SW() {
        Position position1 = new Position(2, 16);
        Position position2 = new Position(2, 15);
        Position position3 = new Position(1, 16);
        Position position4 = new Position(1, 15);
        assertTrue(position1.isIsland() && !position2.isIsland() && !position3.isIsland() && !position4.isIsland());
    }

    @Test
    public void testIsland1NW() {
        Position position1 = new Position(2, 19);
        Position position2 = new Position(2, 20);
        Position position3 = new Position(1, 19);
        Position position4 = new Position(1, 20);
        assertTrue(position1.isIsland() && !position2.isIsland() && !position3.isIsland() && !position4.isIsland());
    }

    @Test
    public void testIsland1NE() {
        Position position1 = new Position(4, 19);
        Position position2 = new Position(4, 20);
        Position position3 = new Position(5, 19);
        Position position4 = new Position(5, 20);
        assertTrue(position1.isIsland() && !position2.isIsland() && !position3.isIsland() && !position4.isIsland());
    }

    @Test
    public void testIsland1SE() {
        Position position1 = new Position(4, 16);
        Position position2 = new Position(4, 15);
        Position position3 = new Position(5, 16);
        Position position4 = new Position(5, 15);
        assertTrue(position1.isIsland() && !position2.isIsland() && !position3.isIsland() && !position4.isIsland());
    }


    //Pirate Island Tests
    @Test
    public void testIsland2NE() {
        Position position1 = new Position(19, 5);
        Position position2 = new Position(19, 6);
        Position position3 = new Position(20, 5);
        Position position4 = new Position(20, 6);
        assertTrue(position1.isIsland() && !position2.isIsland() && !position3.isIsland() && !position4.isIsland());
    }

    @Test
    public void testIsland2NW() {
        Position position1 = new Position(17, 5);
        Position position2 = new Position(17, 6);
        Position position3 = new Position(16, 5);
        Position position4 = new Position(16, 6);
        assertTrue(position1.isIsland() && !position2.isIsland() && !position3.isIsland() && !position4.isIsland());
    }

    @Test
    public void testIsland2SE() {
        Position position1 = new Position(19, 2);
        Position position2 = new Position(19, 1);
        Position position3 = new Position(20, 2);
        Position position4 = new Position(20, 1);
        assertTrue(position1.isIsland() && !position2.isIsland() && !position3.isIsland() && !position4.isIsland());
    }

    @Test
    public void testIsland2SW() {
        Position position1 = new Position(17, 2);
        Position position2 = new Position(17, 1);
        Position position3 = new Position(16, 2);
        Position position4 = new Position(16, 1);
        assertTrue(position1.isIsland() && !position2.isIsland() && !position3.isIsland() && !position4.isIsland());
    }

    //Treasure Island
    @Test
    public void testIsland3NE() {
        Position position1 = new Position(12, 12);
        Position position2 = new Position(12, 13);
        Position position3 = new Position(13, 12);
        Position position4 = new Position(13, 13);
        assertTrue(position1.isIsland() && !position2.isIsland() && !position3.isIsland() && !position4.isIsland());
    }

    @Test
    public void testIsland3NW() {
        Position position1 = new Position(9, 12);
        Position position2 = new Position(9, 13);
        Position position3 = new Position(8, 12);
        Position position4 = new Position(8, 13);
        assertTrue(position1.isIsland() && !position2.isIsland() && !position3.isIsland() && !position4.isIsland());
    }

    @Test
    public void testIsland3SE() {
        Position position1 = new Position(12, 9);
        Position position2 = new Position(12, 8);
        Position position3 = new Position(13, 9);
        Position position4 = new Position(13, 8);
        assertTrue(position1.isIsland() && !position2.isIsland() && !position3.isIsland() && !position4.isIsland());
    }

    @Test
    public void testIsland3SW() {
        Position position1 = new Position(9, 9);
        Position position2 = new Position(9, 8);
        Position position3 = new Position(8, 9);
        Position position4 = new Position(8, 8);
        assertTrue(position1.isIsland() && !position2.isIsland() && !position3.isIsland() && !position4.isIsland());
    }

    @Test
    public void testIsIsland() {
        assertTrue(true);
    }

}