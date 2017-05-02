import buccaneer.cards.CrewCard;
import buccaneer.enumData.CardColor;
import buccaneer.helpers.DirectionHelper;
import buccaneer.helpers.Position;
import buccaneer.helpers.PositionHelper;
import buccaneer.main.GameBoard;
import buccaneer.main.GameSquare;
import buccaneer.main.Player;
import buccaneer.main.Ship;
import org.junit.Test;

import java.util.ArrayList;

import static buccaneer.enumData.CardColor.Red;
import static buccaneer.enumData.Direction.E;
import static buccaneer.enumData.Direction.N;
import static java.awt.Color.red;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by L on 01/05/2017.
 */
public class PositionHelperTest {

    @Test
    public void checkislandsandedgesTest()
    {
        int size = 0;
     //   Position position1 = new Position(2, 16);nb
        GameBoard gb = new GameBoard();
        CrewCard cd = new CrewCard(1, Red,3);
        Player p1 = new Player(0,"1");
        Ship s1= new Ship(p1);

        s1.setLocation(new GameSquare(7,19,gb));
        s1.setDirection(N);
        p1.addCrewCard(cd);

        PositionHelper.getAvailableMoves(s1);
        size = PositionHelper.getAvailableMoves(s1).size();
        assertEquals(size, 1);







    }

}
