import java.util.Calendar;
import java.util.HashMap;

public class FamilyBCalculator implements PayCalculator {
    private static final int BEFORE_TEN = 12;
    private static final int BETWEEN_TEN_AND_TWELVE = 8;
    private static final int AFTER_TWELVE = 16;

    private HashMap<Integer, Integer> payChart = new HashMap<>();

    public FamilyBCalculator() {
        // Loop index represents the hours in a day.
        for (int i = 0; i < 24; i++) {
            if (i >= 17 && i < 22) {
                payChart.put(i, BEFORE_TEN);
            }
            if (i == 22 || i == 23) {
                payChart.put(i, BETWEEN_TEN_AND_TWELVE);
            }
            if (i >= 0 && i < 5) {
                payChart.put(i, AFTER_TWELVE);
            }
        }
    }

    public int calculateAmountOwed(Calendar start, Calendar end) {
        // $12 per hour before 10pm, $8 between 10 and 12, $16 the rest of night
        int startHour = start.get(Calendar.HOUR_OF_DAY);
        int endHour = end.get(Calendar.HOUR_OF_DAY);

        int amountOwed = 0;
        while (startHour != endHour) {
            if (startHour == 24) {
                startHour = 0;
                if (startHour == endHour) {
                    break;
                }
            }
            amountOwed += payChart.get(startHour);
            startHour++;
        }

        return amountOwed;
    }
}
