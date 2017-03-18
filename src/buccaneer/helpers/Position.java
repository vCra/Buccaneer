package buccaneer.helpers;

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


    public boolean isIsland() {
        return PositionHelper.isIsland(this);
    }

    public boolean isEdge() {
        return PositionHelper.isEdge(this);
    }
    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o instanceof Position) {
            Position o2 = (Position) o;
            result = (this.getX() == o2.getX() && this.getY() == o2.getY());
        }
        return result;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
