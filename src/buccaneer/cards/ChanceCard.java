package buccaneer.cards;

/**
 * Chance Card
 */
//TODO: Manage storing of buccaneer.cards and card data/methods
//TODO: Implement card methods on board

public class ChanceCard implements CardObject {
    private int id;
    private String text;

    public ChanceCard (int id, String text)
    {
        this.id = id;
        this.text = text;

    }

    public int getID() {
        return id;
    }

    public String getText() {
        return text;
    }
}
