package buccaneer.treasure;

import buccaneer.enumData.TreasureType;

//TODO: Javadoc


/**
 * Treasure object
 */
public class Treasure {
    TreasureType type;
    int value;
    String friendlyName;
    //TODO: getters and setters for treasure properties


    public TreasureType getType() {
        return type;
    }

    public void setType(TreasureType type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }
}
