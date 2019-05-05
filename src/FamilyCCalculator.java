import java.util.Calendar;

public class FamilyCCalculator implements PayCalculator {
    private static final int BEFORE_NINE = 21;
    private static final int REST_OF_NIGHT = 15;

    public int calculateAmountOwed(Calendar start, Calendar end) {

        int startHour = start.get(Calendar.HOUR_OF_DAY);
        int endHour = end.get(Calendar.HOUR_OF_DAY);

        int amountOwed = 0;
        if (endHour <= 4 || endHour > 21) {
            amountOwed += REST_OF_NIGHT;
        }
        amountOwed += (21 - startHour) * BEFORE_NINE;
        return amountOwed;
    }
}
