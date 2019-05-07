import java.util.Calendar;
import java.util.HashMap;

public class FamilyCCalculator implements PayCalculator {
    private static final int BEFORE_NINE = 21;
    private static final int REST_OF_NIGHT = 15;

    private HashMap<Integer, Integer> payChart = new HashMap<>();

    public FamilyCCalculator() {
        // Loop index represents the hours in a day.
        for (int i = 0; i < 24; i++) {
            if (i >= 17 && i < 21) {
                payChart.put(i, BEFORE_NINE);
            }
            if (i >= 21 || (i >= 0 && i < 5)) {
                payChart.put(i, REST_OF_NIGHT);
            }
        }
    }

    // This algorithm is identical to FamilyA's calculator, however
    // the duplication is kept due to possibility of changing
    // pay calculation in the future.
    public int calculateAmountOwed(Calendar start, Calendar end) {
        // Family C pays $21/hour before 9pm, $15 rest of night
        int startHour = start.get(Calendar.HOUR_OF_DAY);
        int endHour = end.get(Calendar.HOUR_OF_DAY);

        int amountOwed = 0;
        while (startHour != endHour) {
            if (startHour == 24) {
                startHour = 0;
            }
            amountOwed += payChart.get(startHour);
            startHour++;

        }
        return amountOwed;
    }
}
