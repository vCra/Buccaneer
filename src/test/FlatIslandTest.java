import buccaneer.cards.CrewCard;
import buccaneer.enumData.CardColor;
import buccaneer.enumData.TreasureType;
import buccaneer.helpers.Position;
import buccaneer.islands.FlatIsland;

import buccaneer.treasure.Treasure;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Jakub Janas on 4/21/2017.
 */
public class FlatIslandTest
{
    @Test
    public void addAndRemoveCrewCardTest ()
    {
        FlatIsland flatIsland = new FlatIsland(new Position(0, 0), new Position(1, 1));
        CrewCard crewCard = new CrewCard(1, CardColor.Black, 1);

        flatIsland.addCrewCard(crewCard);
        ArrayList<CrewCard> cards = new ArrayList<CrewCard>();
        cards.add(crewCard);

        assertEquals(cards, flatIsland.getCrewCards());
    }

    @Test
    public void addAndGetTreasureTest ()
    {
        FlatIsland flatIsland = new FlatIsland(new Position(0, 0), new Position(1, 1));
        Treasure treasure = new Treasure(TreasureType.DIAMOND);

        flatIsland.addTreasure(treasure);
        ArrayList<Treasure> cards = new ArrayList<Treasure>();
        cards.add(treasure);

        assertEquals(cards, flatIsland.getAndRemoveTreasure());
    }

    @Test
    public void getTreasureTest ()
    {
        FlatIsland flatIsland = new FlatIsland(new Position(0, 0), new Position(1, 1));
        Treasure treasure = new Treasure(TreasureType.DIAMOND);

        flatIsland.addTreasure(treasure);

        assertNotNull(flatIsland.getTreasures());
    }
}
