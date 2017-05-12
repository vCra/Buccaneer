import buccaneer.cards.CrewCard;
import buccaneer.helpers.PositionHelper;
import buccaneer.main.GameBoard;
import buccaneer.main.GameSquare;
import buccaneer.main.Player;
import buccaneer.main.Ship;
import org.junit.Test;

import static buccaneer.enumData.CardColor.Red;
import static buccaneer.enumData.Direction.N;
import static org.junit.Assert.assertEquals;

/**
 * Created by Josh on 02/05/2017.
 */
public class MoveIsValidTest {



    @Test
    public void islandStopsMoveIsValid()
    {
        int size;
        GameBoard gb = new GameBoard();
        CrewCard cd = new CrewCard(1, Red,3);
        Player p1 = new Player(0,"1");
        Ship s1= new Ship(p1);
        // placing ship near island
        s1.setLocation(new GameSquare(2,16,gb));
        s1.setDirection(N);
        p1.addCrewCard(cd);
        //checking if it can move up through island
        PositionHelper.getAvailableMoves(s1);
        size = PositionHelper.getAvailableMoves(s1).size();
        // if passes it cant move through flat islands
        assertEquals(size, 0);

    }



    @Test
    public void crewCardMovementLimit()
    {
        int size;
        GameBoard gb = new GameBoard();
        //assigning crew card with a move value of 3
        CrewCard cd = new CrewCard(1, Red,3);
        Player p1 = new Player(0,"1");
        Ship s1= new Ship(p1);
        // placing ship near island
        s1.setLocation(new GameSquare(2,2,gb));
        s1.setDirection(N);
        p1.addCrewCard(cd);
        //checking if it can move up through island
        PositionHelper.getAvailableMoves(s1);
        size = PositionHelper.getAvailableMoves(s1).size();
        // if passes it can move to the maximum amount meaning it cant move over
        assertEquals(size, 3);

    }

    @Test
    public void islandDetection()
    {
        int size;
        GameBoard gb = new GameBoard();
        CrewCard cd = new CrewCard(1, Red,3);
        Player p1 = new Player(0,"1");
        Ship s1= new Ship(p1);
        // placing ship near island
        s1.setLocation(new GameSquare(2,16,gb));
        s1.setDirection(N);
        p1.addCrewCard(cd);
        //checking if it can move up through island
        PositionHelper.getAvailableMoves(s1);
        size = PositionHelper.getAvailableMoves(s1).size();
        // if passes it cant move through flat islands
        assertEquals(size, 0);

    }

}
