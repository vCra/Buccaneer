import buccaneer.helpers.GameState;
import buccaneer.helpers.TurnTracker;
import buccaneer.main.GameBoard;
import buccaneer.main.GameSquare;
import buccaneer.main.Player;
import buccaneer.ports.Port;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Tests the turn tracker
 * @author adam
 * @version 0.2
 */
public class TurnTrackerTest {

    @Test
    public void testLondonGoesFirst() {
        TurnTracker turnTracker = new TurnTracker();

        Player p1 = new Player(0, "1");
        GameBoard gb = new GameBoard();
        GameSquare gs = new GameSquare(1, 1, gb);
        Port port1 = new Port("London", gs);
        p1.setPort(port1);

        Player p2 = new Player(1, "2");
        GameSquare gs2 = new GameSquare(1,2, gb);
        Port port2 = new Port("Genoa", gs2);
        p2.setPort(port2);

        Player p3 = new Player(2, "3");
        GameSquare gs3 = new GameSquare(1, 3, gb);
        Port port3 = new Port("Marseilles", gs3);
        p3.setPort(port3);

        Player p4 = new Player(3, "4");
        GameSquare gs4 = new GameSquare(1,4, gb);
        Port port4 = new Port("Cadiz", gs4);
        p4.setPort(port4);

        turnTracker.addPlayer(p1);
        turnTracker.addPlayer(p2);
        turnTracker.addPlayer(p3);
        turnTracker.addPlayer(p4);
        turnTracker.begin();
        assertEquals(turnTracker.getCurrentPlayer().getPort().getName(), "London");
    }

    @Test
    public void testIncrementCurrentTurn() {
        TurnTracker turnTracker = new TurnTracker();

        Player p1 = new Player(0, "1");
        GameBoard gb = new GameBoard();
        GameSquare gs = new GameSquare(1, 1, gb);
        Port port1 = new Port("London", gs);
        p1.setPort(port1);

        Player p2 = new Player(1, "2");
        GameSquare gs2 = new GameSquare(1,2, gb);
        Port port2 = new Port("Genoa", gs2);
        p2.setPort(port2);

        turnTracker.addPlayer(p1);
        turnTracker.addPlayer(p2);
        turnTracker.begin();

        turnTracker.nextTurn();
        assertEquals(turnTracker.getCurrentTurn(), 2);
    }

    @Test
    public void firstTurnSetTo1() {
        Player p1 = new Player(0, "1");
        GameBoard gb = new GameBoard();
        GameSquare gs = new GameSquare(1, 1, gb);
        Port port1 = new Port("London", gs);
        p1.setPort(port1);

        Player p2 = new Player(1, "2");
        GameSquare gs2 = new GameSquare(1,2, gb);
        Port port2 = new Port("Genoa", gs2);
        p2.setPort(port2);

        Player p3 = new Player(2, "3");
        GameSquare gs3 = new GameSquare(1, 3, gb);
        Port port3 = new Port("Marseilles", gs3);
        p3.setPort(port3);

        Player p4 = new Player(3, "4");
        GameSquare gs4 = new GameSquare(1,4, gb);
        Port port4 = new Port("Cadiz", gs4);
        p4.setPort(port4);

        TurnTracker turnTracker = new TurnTracker();
        turnTracker.addPlayer(p1);
        turnTracker.addPlayer(p2);
        turnTracker.addPlayer(p3);
        turnTracker.addPlayer(p4);
        turnTracker.begin();
        int turn = turnTracker.getCurrentTurn();
        turnTracker.nextTurn();
        assertEquals(turn, 1);
    }

    @Test
    public void testCurrentTurnNumber() {
        Player p1 = new Player(0, "1");
        GameBoard gb = new GameBoard();
        GameSquare gs = new GameSquare(1, 1, gb);
        Port port1 = new Port("London", gs);
        p1.setPort(port1);

        Player p2 = new Player(1, "2");
        GameSquare gs2 = new GameSquare(1,2, gb);
        Port port2 = new Port("Genoa", gs2);
        p2.setPort(port2);

        Player p3 = new Player(2, "3");
        GameSquare gs3 = new GameSquare(1, 3, gb);
        Port port3 = new Port("Marseilles", gs3);
        p3.setPort(port3);

        Player p4 = new Player(3, "4");
        GameSquare gs4 = new GameSquare(1,4, gb);
        Port port4 = new Port("Cadiz", gs4);
        p4.setPort(port4);

        TurnTracker turnTracker = new TurnTracker();
        turnTracker.addPlayer(p1);
        turnTracker.addPlayer(p2);
        turnTracker.addPlayer(p3);
        turnTracker.addPlayer(p4);
        turnTracker.begin();
        for (int i = 1; i < 5; i++) {
            turnTracker.nextTurn();
        }
        assertEquals(turnTracker.getCurrentTurn(), 5);
    }

    @Test
    public void testGameStates() {
        TurnTracker turnTracker = new TurnTracker();
        turnTracker.setState(GameState.SPIN);
        assertEquals(turnTracker.getState(), GameState.SPIN);
    }
}
