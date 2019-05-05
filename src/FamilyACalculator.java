import java.util.Calendar;

public class FamilyACalculator implements PayCalculator {

    public int calculateAmountOwed(Calendar start, Calendar end) {
        // Pays $15 per hour before 11pm, $20 per hour rest of night.
        int hourlyPay = 15;
        int payRestOfNight = 20;

        int startHour = start.get(Calendar.HOUR_OF_DAY);
        int endHour = end.get(Calendar.HOUR_OF_DAY);
        int amountOwed = 0;
        if (endHour <= 4) {
            amountOwed += payRestOfNight;
        }
        amountOwed += (23 - startHour) * hourlyPay;
        return amountOwed;
    }
}
