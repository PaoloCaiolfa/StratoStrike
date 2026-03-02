package stratostrike.View;

import stratostrike.Domain.Model.Observer;
import stratostrike.Settings;
import stratostrike.Controller.SetupArmy; 



public class SetupView implements Observer {

    private SetupArmy setupArmy;
    private SelectionView selectionView;


    public SetupView(SetupArmy setupArmy, SelectionView selectionView) {
        this.setupArmy = setupArmy;
        this.selectionView = selectionView;
        
        setupArmy.addObserver(this);
    }

    @Override
    public void update() {
        switch (setupArmy.getGame().getCurrentEvent()) {
            case SELECT_ARMY:
                chooseArmy();
                selectionView.askForArmy(); 
                break;
            case COMPOSE_ARMY:
                askForCustomArmy();
                selectionView.askForArmyComposition();
                break;
            default:
                break;
        }

    }

    private void askForCustomArmy() {
        System.out.println("=== CREAZIONE ARMATA PERSONALIZZATA ===");
        for (String el : setupArmy.getAvailableStratoShips()) {
            System.out.println(setupArmy.getAvailableStratoShips().indexOf(el) + ": " + el);
        }
        System.out.println(setupArmy.getAvailableStratoShips().size() + ": termina composizione armata");

        System.out.println("Seleziona le navi da aggiungere alla tua armata, una alla volta. Quando hai finito, seleziona l'opzione per terminare la composizione dell'armata.");

        System.out.println("Le tue navi selezionate finora:");
        for (int i = 0; i < setupArmy.getSelectedShipsForComposition().size(); i++) {
            System.out.println("Nave " + (i+1) + ": " + setupArmy.getSelectedShipsForComposition().get(i));
        }
        System.out.println("Puoi scegliere ancora " + (Settings.MaxShipsWeightPerPlayer - setupArmy.getCompositionShipWeight()) + " navi. Attento a non superare il limite di peso della composizione dell'armata!");
        System.out.println("Peso attuale della composizione: " + setupArmy.getCompositionShipWeight() + "/" + Settings.MaxShipsWeightPerPlayer);
    }

    private void chooseArmy() {
        System.out.println("Seleziona l'armata per il giocatore " + setupArmy.getPlayerUsername());

        System.out.println("=== SELEZIONE ARMATA ===");
        System.out.println("Scegli la fazione desiderata:");
        for (String el : Settings.ArmyTipology) {
            System.out.println((Settings.ArmyTipology.indexOf(el)) + ": " + el);
        }
        for (String el : setupArmy.getArmyNames()) {
            System.out.println((setupArmy.getArmyNames().indexOf(el) + Settings.ArmyTipology.size()) + ": " + el);
        }

        System.out.println(setupArmy.getArmyNames().size() + Settings.ArmyTipology.size() + ": Crea armata personalizzata");

    }


    
}
