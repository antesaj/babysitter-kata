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
        service = BabysitterService.getService();
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
        BabysitterService service2 = BabysitterService.getService();
        assertFalse(service2.isAvailable(start));
    }

    @Test
    public void isAvailablePassesWhenRequestingAvailableNight() {
        assertTrue(service.isAvailable(start));
    }

    @Test
    public void testFamilyAPayCalculation() {
        service.addReservation("FamilyA", start, end);
        PayCalculator payCalculator = BabysitterService.getPayCalculator("FamilyA");
        int amountOwed = payCalculator.calculateAmountOwed(start, end);
        assertEquals(95, amountOwed);
        Calendar start2 = new GregorianCalendar(
                2019, Calendar.MAY, 4, 18, 0);
        Calendar end2 = new GregorianCalendar(
                2019, Calendar.MAY, 4, 23, 0);
        int amountOwed2 = payCalculator.calculateAmountOwed(start2, end2);
        assertEquals(75, amountOwed2);
    }
}
