package buccaneer.treasure;

import buccaneer.enumData.TreasureType;
import buccaneer.helpers.Tradeable;

//TODO: Javadoc


/**
 * Treasure object
 */
public class Treasure extends Tradeable {
    private TreasureType type;
    //TODO: getters and setters for treasure properties

    public Treasure(TreasureType t){
        type = t;
        super.setValue(t.getValue());
    }

    public TreasureType getType() {
        return type;
    }

    public void setType(TreasureType type) {
        this.type = type;
    }

    public String getFriendlyName() {
        return this.type.getName();
    }
}
