package buccaneer.helpers;

import buccaneer.enumData.Direction;

/**
 * Direction Helper - helps with working out direction related infomation
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
        if (pos1.getX() < pos2.getX()){           //X is increasing
            if (pos1.getY() < pos2.getY()){       //Y is increasing
                return Direction.NE;
            }
            else if (pos1.getY() > pos2.getY()){  //Y is decreasing
                return Direction.SE;
            }
            else{
                return Direction.E;
            }
        }
        else if (pos1.getX() > pos2.getX()){      //X is decreasing
            if (pos1.getY() < pos2.getY()){       //Y is increasing
                return Direction.NW;
            }
            else if (pos1.getY() > pos2.getY()){  //Y is decreasing
                return Direction.SW;
            }
            else{
                return Direction.W;
            }
        }
        else {
            if (pos1.getY() < pos2.getY()) {      //Y is increasing
                return Direction.N;
            }
            else if (pos1.getY() > pos2.getY()){  //Y is decreasing
                return Direction.S;
            }
            else {
                System.out.print("Tried to find a direction that does not exist!");
                return Direction.N;
            }
        }
    }

    public static boolean isSameDirection(Position start, Position end, Direction dir) {
        //TODO: Add in NE, NW etc...
        switch (dir){
            case N:
                if (start.getX()==end.getX()&&start.getY()<end.getY()){
                    return true;
                }
                break;
            case E:
                if (start.getX()<end.getX()&&start.getY()==end.getY()){
                    return true;
                }
                break;
            case S:
                if (start.getX()==end.getX()&&start.getY()>end.getY()){
                    return true;
                }
                break;
            case W:
                if (start.getX()>end.getX()&&start.getY()==end.getY()){
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }

}
