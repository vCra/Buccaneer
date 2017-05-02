package buccaneer.enumData;
/**
 * @CardColor.enum  02/02/2017
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 * @author AAW13
 */
//TODO: Javadoc
/**
 * A enum to store the type of treasure, their value and names
 */

public enum TreasureType {
    DIAMOND, RUBY, GOLD, PEARL, RUM;

    /**
     * Returns the value of the treasure based on the what the treasure is
     */
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
            case RUBY:
                {return 5;}
            default:
                return 0;
        }
    }
    /**
     * Returns the name of the treasure to be used in game based on the what the treasure is
     */
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
            case RUBY:
                return "Ruby";
            default:
                return "";
        }
    }
}
