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

        System.out.println("Seleziona l'armata per il giocatore " + setupArmy.getPlayerUsername());

        System.out.println("=== SELEZIONE ARMATA ===");
        System.out.println("Scegli la fazione desiderata:");
        for (String el : Settings.ArmyTipology) {
            System.out.println((Settings.ArmyTipology.indexOf(el)) + ": " + el);
        }
        for (String el : setupArmy.getArmyNames()) {
            System.out.println((setupArmy.getArmyNames().indexOf(el) + Settings.ArmyTipology.size()) + ": " + el);
        }

        selectionView.askForArmy();     
    }


    
}
