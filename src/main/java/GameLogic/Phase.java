package GameLogic;

public class Phase {
    private static int currentPhase = 0;
    private static TimeOfDay[] timeOfDay = TimeOfDay.values();
    private static int maxNumberOfPhases = timeOfDay.length;
    private static TimeOfDay currentTimeOfDay = timeOfDay[currentPhase];


    private Phase() {
    }

    public static TimeOfDay getCurrentTimeOfDay() {
        return currentTimeOfDay;
    }

    public static void nextPhase() {
        currentPhase++;
        if (currentPhase == maxNumberOfPhases) {
            currentPhase = 0;
        }
        currentTimeOfDay = timeOfDay[currentPhase];
    }
}
