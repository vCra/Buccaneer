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

import static buccaneer.enumData.CardColor.Black;
import static buccaneer.enumData.CardColor.Red;
import static buccaneer.enumData.Direction.*;
import static java.awt.Color.red;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by L on 01/05/2017.
 */
public class PositionHelperTest {
    @Test
    public void playerGetterAndSetterTest (){

        int size;
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

    @Test
    public void goingThroughShipDetection (){


        GameBoard gb = new GameBoard();
        CrewCard cd = new CrewCard(1, Red,3);
        Player p1 = new Player(0,"1");
        Player p2 = new Player(0,"2");
        Ship s1= new Ship(p1);
        Ship s2= new Ship(p2);

        s1.setLocation(new GameSquare(7,19,gb));
        s1.setDirection(N);
        p1.addCrewCard(cd);

        s2.setLocation(new GameSquare(6, 19,gb));
        s2.setDirection(N);
        p2.addCrewCard(cd);


    }


    @Test
    public void shipstrengthTest()
    {

        int size1;
        int size2;

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
    public void shipstrengthinportTest()
    {

        int size1;
        int size2;

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
        int p1size;
        int p2size;
        int p3size;
        int p4size;

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

    @Test
    public void isNextTo()
    {
        boolean size;
        GameBoard gb = new GameBoard();
        CrewCard cd = new CrewCard(1, Red,3);
        Player p1 = new Player(0,"1");
        Ship s1= new Ship(p1);

        s1.setLocation(new GameSquare(7,19,gb));
        s1.setDirection(N);
        p1.addCrewCard(cd);

        Position pos1 = new Position(3,2);
        Position pos2 = new Position(3,4);

        PositionHelper.getAvailableMoves(s1);
        PositionHelper.isNextTo(pos1,pos2);
        size = PositionHelper.isNextTo(pos1,pos2);
        assertEquals(size, false);
    }
}
