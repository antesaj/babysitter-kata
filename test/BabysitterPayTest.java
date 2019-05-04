import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class BabysitterPayTest {
    private BabysitterService service;
    private Calendar start;
    private Calendar end;

    @Before
    public void setUp() {
        service = new BabysitterService();
        start = new GregorianCalendar(
                2019, Calendar.MAY, 4, 18, 0);
        end = new GregorianCalendar(
                2019, Calendar.MAY, 5, 3, 0);
    }

    @Test
    public void isAvailableFailsWhenRequestingTimeBetweenFourAMandFivePM() {
        assertFalse(service.isAvailable(new GregorianCalendar(
                2019, Calendar.MAY, 2, 5, 0)));
    }

    @Test
    public void isAvailablePassesWhenRequestingTimeBetweenFivePMandFourAM() {
        assertTrue(service.isAvailable(new GregorianCalendar(
                2019, Calendar.MAY, 2,18, 0)));
    }

    @Test
    public void isOneNightPassesOnlyWhenRangeIsLessThanElevenHours() {
        Calendar newDay = new GregorianCalendar(
                2019, Calendar.MAY, 6, 3,0);
        assertTrue(service.isOneNight(start, end));
        assertFalse(service.isOneNight(start, newDay));
    }

    @Test
    public void isAvailableFailsWhenRequestingReservedNight() {
        String family = "FamilyA";
        service.addReservation(family, start, end);
        assertFalse(service.isAvailable(start));
    }

    @Test
    public void isAvailablePassesWhenRequestingAvailableNight() {
        assertTrue(service.isAvailable(start));
    }

    @Test
    public void testFamilyAPayCalculation() {
        // Pays $15 per hour before 11pm, $20 per hour rest of night.


    }
}
