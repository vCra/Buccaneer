import buccaneer.cards.CrewCard;
import buccaneer.helpers.PositionHelper;
import buccaneer.main.GameBoard;
import buccaneer.main.GameSquare;
import buccaneer.main.Player;
import buccaneer.main.Ship;
import buccaneer.ports.Port;
import org.junit.Test;

import static buccaneer.enumData.CardColor.Red;
import static buccaneer.enumData.Direction.E;
import static buccaneer.enumData.Direction.N;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by L on 01/05/2017.
 */
public class PositionHelperTest {

    @Test
    public void checkislandsandedgesTest()
    {
        int size1 = 0;
     //   Position position1 = new Position(2, 16);nb
        GameBoard gb = new GameBoard();
        CrewCard cd = new CrewCard(1, Red,3);
        Player p1 = new Player(0,"1");
        Ship s1= new Ship(p1);

        s1.setLocation(new GameSquare(7,19,gb));
        s1.setDirection(N);
        p1.addCrewCard(cd);

        PositionHelper.getAvailableMoves(s1);
        size1 = PositionHelper.getAvailableMoves(s1).size();
        assertEquals(size1, 1);

        int size2 = 0;
        GameBoard gb1 = new GameBoard();
        CrewCard cd1 = new CrewCard(2, Red,3);
        Player p2 = new Player(1,"2");
        Ship s2 = new Ship(p2);

        s2.setLocation(new GameSquare(18,20,gb));
        s2.setDirection(E);
        p2.addCrewCard(cd1);

        PositionHelper.getAvailableMoves(s2);
        size2 = PositionHelper.getAvailableMoves(s2).size();
        assertEquals(size2, 2);







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
    public void checkporthighlight3()
    {
        int size1 = 0;
        GameBoard gb = new GameBoard();
        CrewCard cd = new CrewCard(1, Red,3);
        Player p1 = new Player(0,"1");
        Ship s1= new Ship(p1);
        ports.add(new Port("London", getSquareAt(1, 14)));


        s1.setLocation(new GameSquare(1,14,gb));
        s1.setDirection(E);
        p1.addCrewCard(cd);

        PositionHelper.getAvailableMoves(s1);
        size1 = PositionHelper.getAvailableMoves(s1).size();
        assertEquals(size1, 47);


    }
}
