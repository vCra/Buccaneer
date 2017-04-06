import buccaneer.cards.ChanceCard;
import buccaneer.enumData.TreasureType;
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
public class TestPirateIsland {
    @Test
    public void testTreasure(){
        TreasureIsland island = new TreasureIsland();
        assertEquals(island.getTreasures().size(), 20);

        Treasure t1 = new Treasure(TreasureType.RUBIE);
        island.addTreasure(t1);
        assertEquals(island.getTreasures().size(), 21);

        assertTrue(island.removeTreasure(t1)); //Can we remove it
        assertEquals(island.getTreasures().size(), 20);

        assertEquals(island.getTreasures().size(), island.getTreasureQty());
    }

    @Test
    public void testCards(){
        TreasureIsland island = new TreasureIsland();
        assertNotSame(island.getTopCard(), island.getTopCard());

        //Test that cards are put in different order
        ChanceCard c1 = island.getTopCard();
        island.addChanceCard(c1);
        assertNotSame(c1, island.getTopCard());
    }
}
