import buccaneer.cards.ChanceCard;
import buccaneer.enumData.TreasureType;
import buccaneer.islands.PirateIsland;
import buccaneer.islands.TreasureIsland;
import buccaneer.treasure.Treasure;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

/**
 * TestTreasureIsland.java
 * Tests the treasure island, including adding and removing treasure and chance cards
 */
public class TestTreasureIsland {
    @Test
    public void testCards(){
        PirateIsland island = new PirateIsland();
        assertNotSame(island.getTopCard(), island.getTopCard());

    }
}
