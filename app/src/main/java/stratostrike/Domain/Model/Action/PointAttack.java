package stratostrike.Domain.Model.Action;

import stratostrike.Domain.Model.*;
import java.util.ArrayList;
import stratostrike.Domain.Model.validate.*;

public class PointAttack extends Capability {
    private int damage;

    public PointAttack() {super();}

    public PointAttack(String name, String description, int damage, Shape shape, Shape range, ArrayList<Validate> valids) {
        super(name, description, shape, range, valids);
        this.damage = damage;
    
    }
    
    @Override
    public Action cloneAction() {
        PointAttack clone = new PointAttack(this.name, this.description, this.damage, this.shape, this.range, this.validators);
        return clone;
    }


    @Override
    public void doAction(Context context) {
        Position target = context.getTargetPosition();

        System.out.println("Attacco riuscito! Danno inflitto: " + damage);
        if (target.getShip() != null) {
            target.getShip().changeStatus(damage);
            if (target.getShip().isDestroyed()) {
                System.out.println("La nave " + target.getShip().getName() + " avversaria è stata distrutta!");
            } else {
                System.out.println("HP rimanenti della nave " + target.getShip().getName() + " avversaria: " + target.getShip().getHp());
            }
        }   
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append("         Descrizione: ").append(description).append("\n");
        details.append("         Danno: ").append(damage).append("\n");
        if (shape != null && shape instanceof Circle) {
            Circle circle = (Circle) shape;
            details.append("         Raggio: ").append(circle.getRadius());
        }
        if (range != null && range instanceof Circle) {
            Circle circle = (Circle) range;
            details.append("         Raggio d'azione: ").append(circle.getRadius());
        }
        return details.toString();
    }

}