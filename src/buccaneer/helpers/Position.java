package buccaneer.helpers;

import buccaneer.main.GameBoard;

/**
 * Stores a position of an object, using X and Y coordinates as integers.
 */
//TODO: Javadoc

public class Position {
    private int x;
    private int y;



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

    public boolean isPort(GameBoard board) {
        return PositionHelper.isPort(this, board);
    }

    public boolean isEdge() {
        return PositionHelper.isEdge(this);
    }

    public boolean containsShip(GameBoard board){
        return PositionHelper.isShip(this, board);
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
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
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
