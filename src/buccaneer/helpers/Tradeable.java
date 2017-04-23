package buccaneer.helpers;

/**
 * Created by awalker on 19/04/2017.
 */
public class Tradeable extends Receivable {
    int value;
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
}
