package buccaneer.cards;

import buccaneer.enumData.CardColor;

/**
 * Crew Card
 */
//TODO: Manage storing of buccaneer.cards and card data/methods
//TODO: Implement card methods on board
//TODO: Implament card score calculator
public class CrewCard implements CardObject {
    private int id;
    private CardColor color;
    private int value;

    public CrewCard(int id, CardColor color, int value) {
        this.id = id;
        this.color = color;
        this.value = value;
    }

    public int getID() {
        return id;
    }

    public CardColor getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }
}
