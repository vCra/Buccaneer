import buccaneer.enumData.Direction;
import buccaneer.helpers.Position;
import buccaneer.main.GameBoard;
import buccaneer.main.GameSquare;
import buccaneer.main.Player;
import buccaneer.ports.Port;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Jakub Janas on 4/5/2017.
 */
public class PortTest
{
    @Test
    public void playerGetterAndSetterTest ()
    {
        GameSquare square = new GameSquare(0, 0, new GameBoard());
        Port port = new Port("Poznan", square);
        Player player = new Player(0, "Kuba");

        port.setOwner(player);

        assertEquals(player, port.getOwner());
    }

    @Test
    public void nameGetterAndSetterTest ()
    {
        GameSquare square = new GameSquare(0, 0, new GameBoard());
        Port port = new Port("Poznan", square);
        String name = "Aberystwyth";

        port.setName(name);

        assertEquals(name, port.getName());
    }

    @Test
    public void positionGetterAndSetterTest ()
    {
        GameSquare square = new GameSquare(0, 0, new GameBoard());
        Port port = new Port("Poznan", square);
        Position position = new Position(1, 1);

        port.setLocation(position);

        assertEquals(position, port.getLocation());
    }

    @Test
    public void getWaterFaceTest ()
    {
        GameSquare square = new GameSquare(0, 0, new GameBoard());
        Port port = new Port("Poznan", square);

        assertEquals(Direction.N, port.getWaterFace());

        port.setName("London");
        assertEquals(Direction.E, port.getWaterFace());

        port.setName("Genoa");
        assertEquals(Direction.N, port.getWaterFace());

        port.setName("Marseilles");
        assertEquals(Direction.W, port.getWaterFace());

        port.setName("Cadiz");
        assertEquals(Direction.S, port.getWaterFace());
    }

    @Test
    public void toStringTest ()
    {
        GameSquare square = new GameSquare(0, 0, new GameBoard());
        Port port = new Port("Poznan", square);
        String info = "Poznan at " + port.getLocation();

        assertEquals(info, port.toString());
    }
}
