package helpers;

/**
 * Created by aaw13 on 02/02/2017.
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


}
