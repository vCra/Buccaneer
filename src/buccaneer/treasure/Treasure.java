package buccaneer.treasure;

import buccaneer.enumData.TreasureType;

//TODO: Javadoc


/**
 * Treasure object
 */
public class Treasure {
    private TreasureType type;
    //TODO: getters and setters for treasure properties

    public Treasure(TreasureType t){
        type = t;
    }

    public TreasureType getType() {
        return type;
    }

    public void setType(TreasureType type) {
        this.type = type;
    }

    public int getValue() {
        return this.type.getValue();
    }

    public String getFriendlyName() {
        return this.type.getName();
    }
}
