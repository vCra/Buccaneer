import buccaneer.cards.CrewCard;
import buccaneer.enumData.Direction;
import buccaneer.helpers.DirectionHelper;
import buccaneer.helpers.Position;
import buccaneer.main.GameBoard;
import buccaneer.main.GameSquare;
import buccaneer.main.Player;
import buccaneer.main.Ship;
import org.junit.Test;

import static buccaneer.enumData.CardColor.Red;
import static buccaneer.enumData.Direction.N;
import static org.junit.Assert.assertEquals;

/**
 * Created by L on 05/05/2017.
 */
public class DirectionHelperTest {


    @Test
    public void positionToDirectionTest() {


        String direction = "";
        GameBoard gb = new GameBoard();
        CrewCard cd = new CrewCard(1, Red,3);
        Player p1 = new Player(0,"1");
        Ship s1= new Ship(p1);
        Position pos1 = new Position(3,2);
        Position pos2 = new Position(4,4);

        s1.setLocation(new GameSquare(7,19,gb));
        // s1.setDirection(N);

        //DirectionHelper.positionToDirection(s1);
        direction = DirectionHelper.positionToDirection(pos1,pos2).toString();
        assertEquals(direction, "NE");


    }


    @Test
    public void getnextpostest()
    {
        String direction = "";
        GameBoard gb = new GameBoard();
        CrewCard cd = new CrewCard(1, Red,3);
        Player p1 = new Player(0,"1");
        Ship s1= new Ship(p1);

        Position pos1 = new Position(3,2);

        s1.setLocation(new GameSquare(7,19,gb));

        direction = DirectionHelper.getNextPos(pos1,N).toString();

        assertEquals(direction, "Position{x=3, y=3}");
    }


    @Test
    public void isSameDirectionTest() {
        assertEquals(DirectionHelper.positionToDirection(new Position(5, 5), new Position(5, 6)), Direction.N);
        assertEquals(DirectionHelper.positionToDirection(new Position(5, 5), new Position(5, 4)), Direction.S);
        assertEquals(DirectionHelper.positionToDirection(new Position(5, 5), new Position(4, 5)), Direction.W);
        assertEquals(DirectionHelper.positionToDirection(new Position(5, 5), new Position(6, 5)), Direction.E);
    }

    @Test
    public void testPositionGetters() {
        assertEquals(new Position(5, 6).getX(), 5);
        assertEquals(new Position(5, 6).getY(), 6);


    }

}
