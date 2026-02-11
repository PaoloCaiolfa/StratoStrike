package stratostrike.Domain.Model.Army;

public abstract class SkyShip extends StratoShip {
    
    public SkyShip() {}

    public SkyShip(String name, int hp) {
        super(name, hp);  
    }
}
