package stratostrike.Domain.Model.Action;

import java.util.ArrayList;

import stratostrike.Domain.Model.Context;
import stratostrike.Domain.Model.Position;
import stratostrike.Domain.Model.Army.StratoShip;
import stratostrike.Domain.Model.Shape.Shape;
import stratostrike.Domain.Model.validate.Validate;

public class DroneAttack extends Capability {

    private int damage;

    public DroneAttack() { super(); }

    public DroneAttack(String name, String description, int damage, Shape shape, Shape range, ArrayList<Validate> validets) {
        super(name, description, shape, range, validets);
        this.damage = damage;
    }

    @Override
    public Action cloneAction() {
        DroneAttack clone = new DroneAttack(this.name, this.description, this.damage, this.shape, this.range, this.validators);
        return clone;
    }

    @Override
    public void doAction(Context context) {
        StratoShip myShip = context.getSelectedShip();
        Position target = context.getTargetPosition(); 
        Position myShipPos = context.getBoard().getShipPosition(myShip);

        int dx = Integer.signum(target.getX() - myShipPos.getX());
        int dy = Integer.signum(target.getY() - myShipPos.getY());

        int impactX = target.getX() - dx;
        int impactY = target.getY() - dy;
        int impactZ = target.getZ();

        Position impactPosition = context.getBoard().getPosition(impactX, impactY, impactZ);

        // Sposta la ship sulla posizione di impatto
        context.getBoard().moveShip(myShip, impactPosition);
        System.out.println("Il drone si è spostato in " + impactPosition);

        // Infligge danno alla nave nemica
        target.getShip().changeStatus(damage);
        System.out.println("Impatto! Danno inflitto a " + target.getShip().getName() + ": " + damage);

        if (target.getShip().isDestroyed()) {
            System.out.println("La nave " + target.getShip().getName() + " è stata distrutta!");
        } else {
            System.out.println("HP rimanenti: " + target.getShip().getHp());
        }

        // Il drone si autodistrugge nella posizione di impatto
        myShip.changeStatus(myShip.getHp());
        System.out.println("Il drone " + myShip.getName() + " si è autodistrutto in " + impactPosition);
    }

    public int getDamage() { return damage; }

    public void setDamage(int damage) { this.damage = damage; }

    @Override
    public String getDetails() {
        return "         Descrizione: " + description + "\n" +
               "         Danno (su impatto): " + damage + "\n";
    }
}