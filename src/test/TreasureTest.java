import buccaneer.enumData.TreasureType;
import buccaneer.treasure.Treasure;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Jakub Janas on 4/6/2017.
 */
public class TreasureTest
{
    @Test
    public void typeGetterAndSetterTest ()
    {
        Treasure treasure = new Treasure(TreasureType.DIAMOND);
        TreasureType type = TreasureType.GOLD;

        treasure.setType(type);

        assertEquals(type, treasure.getType());
    }

    @Test
    public void valueGetterTest ()
    {
        Treasure treasure = new Treasure(TreasureType.DIAMOND);

        assertEquals(5, treasure.getValue());
    }

    @Test
    public void friendlyNameGetterTest ()
    {
        Treasure treasure = new Treasure(TreasureType.DIAMOND);

        assertEquals("Diamond", treasure.getFriendlyName());
    }
}
