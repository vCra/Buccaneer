package buccaneer.helpers;

import buccaneer.enumData.Direction;

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

    public boolean isIsland(int x, int y){
        if (x >= 2 && x <= 4){
            if (y>=16 && y <=19){
                return true;
            }
        }
        else if(x >= 17 && x <= 19){
            if (y>=2 && y <=5){
                return true;
            }
        }
        else if(x >= 9 && x <= 12){
            if (y>=9 && y <= 12){
                return true;
            }
        }
        return false;
    }

    public boolean isIsland(Position position){
        return this.isIsland(position.getX(), position.getY());
    }
    public boolean isIsland(){
        return this.isIsland(this);
    }

    public ArrayList<Position> getAvalableMoves(Direction direction){
        ArrayList<Position> list = new ArrayList<>();
        x = this.getX();
        y = this.getY();
        boolean edge = false;
        switch (direction){
            case N:
                while (edge = false){
                    if (x + 1 > 20 || isIsland(x+1, y)){
                        edge = true;
                    }

                }

        }
        return null;
    }
}
