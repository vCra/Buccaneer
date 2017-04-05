import buccaneer.helpers.Score;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
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

    @Test
    public void testGetterandSetter() {
        Score score = new Score();
        score.setScore(10);
        assertEquals(score.getScore(), 10);
    }

    @Test
    public void testtoString() {
        Score score = new Score();
        score.setScore(10);
        String string = score.toString();
        assertEquals(string, "10");
    }

    @Test
    public void testIncrementScore() {
        Score score = new Score();
        score.setScore(10);
        score.addToScore(5);
        assertEquals(score.getScore(), 15);
    }
}
