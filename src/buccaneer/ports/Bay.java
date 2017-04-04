package buccaneer.ports;

import buccaneer.helpers.Position;

/**
 * Created by awalker on 30/03/2017.
 */
public class Bay {
    private String name;
    private Position position;
    public Bay(String name, Position position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
