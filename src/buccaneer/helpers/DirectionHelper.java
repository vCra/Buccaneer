package buccaneer.helpers;

import buccaneer.enumData.Direction;

/**
 * Created by awalker on 03/03/2017.
 */
public class DirectionHelper {
    public static int directionToAngle(Direction dir) {
        switch (dir) {
            case N:
                return 0;
            case NE:
                return 45;
            case E:
                return 90;
            case SE:
                return 135;
            case S:
                return 180;
            case SW:
                return 225;
            case W:
                return 270;
            case NW:
                return 315;
            default:
                return 0;
        }
    }

    public static Direction numToDir(int num){
        switch (num){
            case 1:
                return Direction.E;
            case 2:
                return Direction.N;
            case 3:
                return Direction.W;
            case 4:
                return Direction.S;
            default:
                return Direction.N;
        }
    }    /**
     * Takes 2 directions and returns the direction of the second pos from the first
     * @param pos1 the first reference pos
     * @param pos2 the second pos (to go to)
     * @return a direction from the first to the second.
     */
    public static Direction positionToDirection(Position pos1, Position pos2){
        return Direction.N;
    }
}
