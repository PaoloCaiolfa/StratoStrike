package stratostrike.View;

import stratostrike.Domain.Model.Observer; 
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
        selectionView.askForArmy();






        
     
    }


    
}
