package stratostrike.Domain.Model.Army.Factory;

import stratostrike.Domain.Model.Army.Army;


//polymorphism per gestire la creazione in base a differenti logiche di creazione delle army
public interface ArmyFactory {
    public Army createArmy(String armyName);
}
