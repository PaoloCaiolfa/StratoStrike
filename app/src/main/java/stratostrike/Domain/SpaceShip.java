package stratostrike.Domain;

public class SpaceShip extends StratoShip {
    private String type;

    public SpaceShip(String name, int hp, String type) {
        super(name, hp);
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
