package stratostrike.Controller;

import stratostrike.Factory.*;
import stratostrike.Domain.Army;
public class ArmyManager {
    public static ArmyFactory getFactory(String fazione) {
        try {
            String fazioneNormalizzata = fazione.substring(0, 1).toUpperCase() + fazione.substring(1).toLowerCase();
            String className = "stratostrike.Factory." + fazioneNormalizzata + "ArmyFactory";
            
            Class<?> clazz = Class.forName(className);

            // Cerchiamo il METODO statico getInstance invece del costruttore
            java.lang.reflect.Method method = clazz.getMethod("getInstance");

            // Lo invochiamo. Essendo statico, il primo parametro di invoke è 'null'
            return (ArmyFactory) method.invoke(null);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
       

