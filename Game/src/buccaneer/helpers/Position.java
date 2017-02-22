package buccaneer.helpers;

<<<<<<< HEAD:Game/src/buccaneer/helpers/Position.java
import buccaneer.enumData.Direction;

=======
import enumData.Direction;
import javafx.geometry.Pos;
import helpers.PositionHelper;
>>>>>>> origin/master:Game/src/helpers/Position.java
import java.util.ArrayList;
/**
 * Stores a position of an object, using X and Y coordinates as integers.
 */
//TODO: Implement PositionHelper to show avalable locations to move, calculate if moves are legal etc...
//TODO: check if set locations are possible, and throw exceptions (TODO: make exceptions) if they are not
public class Position {
    int x;
    int y;

    public Position(int x, int y) {
        set(x, y);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }


    public boolean isIsland(){
        return PositionHelper.isIsland(this);
    }


}
