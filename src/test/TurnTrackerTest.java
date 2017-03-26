import buccaneer.helpers.GameState;
import buccaneer.helpers.TurnTracker;
import buccaneer.main.Player;
import buccaneer.ports.Port;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by adam on 26/03/2017.
 */
public class TurnTrackerTest {

//    public void testCurrentPlayer() {
//
//    }

    @Test
    public void testLondonGoesFirst() {
        TurnTracker turnTracker = new TurnTracker();
        Player player = turnTracker.getCurrentPlayer();
        Port port = player.getPort();
        String portName = port.getName();
        assertEquals(portName, "London");
    }

    @Test
    public void testIncrementCurrentTurn() {
        TurnTracker turnTracker = new TurnTracker();
        turnTracker.nextTurn();
        assertEquals(turnTracker.getCurrentTurn(), 2);
    }

    @Test
    public void firstTurnSetTo1() {
        TurnTracker turnTracker = new TurnTracker();
        int turn = turnTracker.getCurrentTurn();
        assertEquals(turn, 1);
    }

    @Test
    public void testCurrentTurnNumber() {
        TurnTracker turnTracker = new TurnTracker();
        for (int i = 1; i <= 5; i++) {
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
