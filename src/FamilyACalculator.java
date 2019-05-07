import java.util.Calendar;

public class FamilyACalculator implements PayCalculator {
    private static final int BEFORE_ELEVEN = 15;
    private static final int REST_OF_NIGHT = 20;

    public int calculateAmountOwed(Calendar start, Calendar end) {
        // Pays $15 per hour before 11pm, $20 per hour rest of night.
        int startHour = start.get(Calendar.HOUR_OF_DAY);
        int endHour = end.get(Calendar.HOUR_OF_DAY);

        int amountOwed = 0;
        return amountOwed;
    }
}
