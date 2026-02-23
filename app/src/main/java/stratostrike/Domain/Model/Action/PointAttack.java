package stratostrike.Domain.Model.Action;

import stratostrike.Domain.Model.*;
import stratostrike.Domain.Model.Army.*;

public class PointAttack extends Capability {
    private int damage;

    public PointAttack() {super();}

    public PointAttack(String name, String description, int damage, Shape shape, Shape range) {
        super(name, description, shape, range);
        this.damage = damage;
    
    }

    @Override
    public Action cloneAction() {
        PointAttack clone = new PointAttack(this.name, this.description, this.damage, this.shape, this.range);
        return clone;
    }

    @Override
    public boolean isValidTarget(Board board, Position target, StratoShip actor) {
        if (target.getShip() != null) {
            if (target.getShip().isDestroyed()) { // se la nave è già distrutta, non è un bersaglio valido
                System.out.println("La nave in quella posizione è già distrutta, non è un bersaglio valido.");
                return false;
            }
            if (target.getShip().idArmy != actor.idArmy) { // la nave è un bersaglio valido se appartiene all'armata avversaria
                return true;
            } else {
                System.out.println("La nave appartiene al giocatore attivo, non è un bersaglio valido.");
                return false;
            }
        } else {
            System.out.println("Non c'è nessuna nave in quella posizione, non è un bersaglio valido.");
            return false;
        }
    }

    @Override
    public void doAction(Board board, Position target, StratoShip actor) {
        System.out.println("Attacco riuscito! Danno inflitto: " + damage);
        target.getShip().changeStatus(damage);
        if (target.getShip().isDestroyed()) {
            System.out.println("La nave " + target.getShip().getName() + " avversaria è stata distrutta!");
        } else {
            System.out.println("HP rimanenti della nave " + target.getShip().getName() + " avversaria: " + target.getShip().getHp());
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