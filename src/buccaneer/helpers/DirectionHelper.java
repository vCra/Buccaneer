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
}
