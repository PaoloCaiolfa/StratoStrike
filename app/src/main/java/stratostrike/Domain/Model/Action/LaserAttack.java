package stratostrike.Domain.Model.Action;

import java.util.ArrayList;

import stratostrike.Domain.Model.Context;
import stratostrike.Domain.Model.Position;
import stratostrike.Domain.Model.Shape.Shape;
import stratostrike.Domain.Model.validate.Validate;


public class LaserAttack extends Capability {
    private int damage;
    
    public LaserAttack() {super();}

    public LaserAttack(String name, String description,  int damage, Shape shape, Shape range, ArrayList<Validate> valids) {
        super(name, description, shape, range, valids);
        this.damage = damage;
        System.out.println("LaserAttack creato con danno: " + damage); 
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public Action cloneAction() {
        LaserAttack clone = new LaserAttack(this.name, this.description, this.damage, this.shape, this.range, this.validators);
        return clone;
    }

    @Override
    public void doAction(Context context) {

        Position target = context.getTargetPosition();

        if (target.getShip() != null) {
            System.out.println(damage);
            target.getShip().changeStatus(damage);
            System.out.println("Attacco laser riuscito! Danno inflitto: " + this.damage);
        } else {
            System.out.println("Attacco laser fallito! Nessuna nave presente nella posizione bersaglio.");
        }
        if (target.getShip().isDestroyed()) {
                System.out.println("La nave " + target.getShip().getName() + " avversaria è stata distrutta!");
            } else {
                System.out.println("HP rimanenti della nave " + target.getShip().getName() + " avversaria: " + target.getShip().getHp());
            }
        

    }

    @Override
    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append("         Descrizione: ").append(description).append("\n");
        details.append("         Danno: ").append(damage).append("\n");
        
        return details.toString();
    }
    
}
