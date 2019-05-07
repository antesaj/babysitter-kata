import java.util.Calendar;
import java.util.HashMap;

public class FamilyACalculator implements PayCalculator {
    private static final int BEFORE_ELEVEN = 15;
    private static final int AFTER_ELEVEN = 20;

    private HashMap<Integer, Integer> payChart = new HashMap<>();

    public FamilyACalculator() {
        // Loop index represents the hours in a day.
        for (int i = 0; i < 24; i++) {
            if (i >= 17 && i < 23) {
                payChart.put(i, BEFORE_ELEVEN);
            }
            if ((i >= 0 && i < 5) || i == 23) {
                payChart.put(i, AFTER_ELEVEN);
            }
        }
    }

    public int calculateAmountOwed(Calendar start, Calendar end) {
        // Pays $15 per hour before 11pm, $20 per hour rest of night.
        int startHour = start.get(Calendar.HOUR_OF_DAY);
        int endHour = end.get(Calendar.HOUR_OF_DAY);

        int amountOwed = 0;
        while(startHour != endHour) {
            if (startHour == 24) {
                startHour = 0;
            }
            amountOwed += payChart.get(startHour);
            startHour++;
        }
        return amountOwed;
    }
}
