package stratostrike.Domain.Model.Army.Factory;

public class ArmyManager {
    public static ArmyFactory getFactory(String fazione) {
        try {
            String fazioneNormalizzata = fazione.substring(0, 1).toUpperCase() + fazione.substring(1).toLowerCase();
            String className = "stratostrike.Domain.Model.Army.Factory." + fazioneNormalizzata + "ArmyFactory";
            
            Class<?> clazz = Class.forName(className);

            java.lang.reflect.Method method = clazz.getMethod("getInstance");

            return (ArmyFactory) method.invoke(null);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
       

