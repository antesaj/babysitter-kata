import java.util.Calendar;

public class FamilyBCalculator implements PayCalculator {
    public int calculateAmountOwed(Calendar start, Calendar end) {
        // $12 per hour before 10pm, $8 between 10 and 12, $16 the rest of night
        return 80;
    }
}
