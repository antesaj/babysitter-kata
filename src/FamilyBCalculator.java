import java.util.Calendar;

public class FamilyBCalculator implements PayCalculator {
    private static final int BEFORE_TEN = 12;
    private static final int BETWEEN_TEN_AND_TWELVE = 8;
    private static final int AFTER_TWELVE = 16;

    public int calculateAmountOwed(Calendar start, Calendar end) {
        // $12 per hour before 10pm, $8 between 10 and 12, $16 the rest of night
        int startHour = start.get(Calendar.HOUR_OF_DAY);
        int endHour = end.get(Calendar.HOUR_OF_DAY);

        int amountOwed = 0;

        return amountOwed;
    }
}
