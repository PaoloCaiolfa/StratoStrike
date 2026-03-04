package stratostrike.Domain.Model;

import stratostrike.Domain.Model.Army.Army;
import stratostrike.Domain.Model.Army.StratoShip;
import stratostrike.Domain.Model.Action.SpecialAbility;
import java.util.ArrayList;

public class Player {
    private String username;
    private String mail;
    private String password;
    private int idPlayer;
    private Army army = new Army(null,idPlayer);

    public Player(String username, int idPlayer) {
        this.username = username;
        this.idPlayer = idPlayer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
        army.setIdArmy(idPlayer);
    }

    public boolean isFleetDestroyed() {
        return army.isFleetDestroyed();
    }

    public ArrayList<SpecialAbility> getAllSpecialAbilities() {
        ArrayList<SpecialAbility> specialAbilities = new ArrayList<>();
        for (StratoShip ship : army.getShips()) {
            
            ship.getSpecialAbilities().forEach(
                action -> {
                    if (action instanceof SpecialAbility) {
                        specialAbilities.add((SpecialAbility) action);
                    }
                }  
            );
        }
        return specialAbilities;
    }

    public boolean allActivatorsVerified(Context context) {
        ArrayList<SpecialAbility> availableActions = getAllSpecialAbilities();
        for (SpecialAbility action : availableActions) {
            System.out.println( action.printActivators());
            if (!action.allActivatorsVerified(context).isValid()) {
                return false;
            }
        }
        return true;
    }
}