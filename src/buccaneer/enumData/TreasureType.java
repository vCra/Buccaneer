package buccaneer.enumData;

/**
 * Created by aaw13 on 02/02/2017.
 */
//TODO: Javadoc

public enum TreasureType {
    DIAMOND, RUBIE, GOLD, PEARL, RUM;

    public int getValue(){
        switch (this){
            case DIAMOND:
                {return 5;}
            case GOLD:
                {return 4;}
            case PEARL:
                {return 3;}
            case RUM:
                {return 2;}
            case RUBIE:
                {return 5;}
            default:
                return 0;
        }
    }
    public String getName(){
        switch (this){
            case DIAMOND:
                return "Diamond";
            case GOLD:
                return "Bar of gold";
            case PEARL:
                return "Pearl";
            case RUM:
                return "Barrel of rum";
            case RUBIE:
                return "Ruby";
            default:
                return "";
        }
    }
}
