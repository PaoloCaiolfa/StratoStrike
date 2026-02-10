package stratostrike.Domain;

public class Satellite extends SpaceShip {

    public Satellite() {}

    @Override
    public StratoShip cloneShip() {
        Satellite clone = new Satellite();
        clone.setName(this.name);
        clone.setHp(this.hp);
        clone.setIdArmy(this.idArmy);
        
        // Clone actions
        for (Action action : this.actions) {
            clone.addAction(action.cloneAction());
        }
        
        return clone;
    }
}
