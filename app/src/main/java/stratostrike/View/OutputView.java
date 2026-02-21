package stratostrike.View;

import stratostrike.Controller.MakeTurn;
import stratostrike.Domain.Model.Observer;

public class OutputView implements Observer {

    MakeTurn makeTurn;

    public OutputView(MakeTurn makeTurn) {
        this.makeTurn = makeTurn;
    }

    @Override
    public void update() {
        // Implementazione del metodo update per aggiornare la visualizzazione
        System.out.println("La visualizzazione è stata aggiornata.");
    }
    
}
