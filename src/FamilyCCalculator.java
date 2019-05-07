import java.util.Calendar;

public class FamilyCCalculator implements PayCalculator {
    private static final int BEFORE_NINE = 21;
    private static final int REST_OF_NIGHT = 15;

    public int calculateAmountOwed(Calendar start, Calendar end) {
        // Family C pays $21/hour before 9pm, $15 rest of night
        int startHour = start.get(Calendar.HOUR_OF_DAY);
        int endHour = end.get(Calendar.HOUR_OF_DAY);

        int amountOwed = 0;

        return amountOwed;
    }
}
