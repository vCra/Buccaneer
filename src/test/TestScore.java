import buccaneer.helpers.Score;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


/**
 * Test the Score class
 */
public class TestScore {
    @Test
    public void testHasWon() {
        Score score1 = new Score();
        Score score2 = new Score();
        Score score3 = new Score();
        score1.setScore(20);
        score2.setScore(21);
        score3.setScore(19);
        assertTrue(score1.hasWon() && score2.hasWon() && !score3.hasWon());
    }
}
