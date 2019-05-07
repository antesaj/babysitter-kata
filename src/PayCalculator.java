import java.util.Calendar;

public interface PayCalculator {
    /**
     * Calculates the amount owed for babysitting services. Since fractional hours
     * are not considered, this returns a whole number (int).
     *
     * @param start start date/time of service.
     * @param end end date/time of service.
     * @return int representing total cost.
     */
    int calculateAmountOwed(Calendar start, Calendar end);
}
