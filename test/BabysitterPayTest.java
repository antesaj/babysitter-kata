import org.junit.After;
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

    @After
    public void tearDown() {
        service.reservations.clear();
    }

    @Test
    public void isAvailablePassesWhenRequestingAvailableNight() {
        assertTrue(service.isAvailable(start));
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
        String family = BabysitterService.FAMILY_A;
        service.addReservation(family, start, end);
        assertFalse(service.isAvailable(start));
        BabysitterService service2 = BabysitterService.getService();
        assertFalse(service2.isAvailable(start));
    }

    @Test
    public void testFamilyAPayCalculation() {
        PayCalculator payCalculator = BabysitterService.getPayCalculator(BabysitterService.FAMILY_A);
        // Case where time starts before 11pm and ends after 11pm
        int amountOwed = payCalculator.calculateAmountOwed(start, end);
        assertEquals(95, amountOwed);

        // Case where time range doesn't go past 11pm
        Calendar start2 = new GregorianCalendar(
                2019, Calendar.MAY, 4, 18, 0);
        Calendar end2 = new GregorianCalendar(
                2019, Calendar.MAY, 4, 23, 0);
        int amountOwed2 = payCalculator.calculateAmountOwed(start2, end2);
        assertEquals(75, amountOwed2);

        // Case where start/end has a fractional hour
        Calendar fractionalStart = new GregorianCalendar(
                2019, Calendar.MAY, 4, 18, 30);
        Calendar fractionalEnd = new GregorianCalendar(
                2019, Calendar.MAY, 5, 2, 30);
        int amountOwed3 = payCalculator.calculateAmountOwed(fractionalStart, fractionalEnd);
        assertEquals(95, amountOwed3);
    }

    @Test
    public void testFamilyBCalculation() {
        PayCalculator payCalculator = BabysitterService.getPayCalculator(BabysitterService.FAMILY_B);
        int amountOwed = payCalculator.calculateAmountOwed(start, end);
        assertEquals(80, amountOwed);

        Calendar start2 = new GregorianCalendar(
                2019, Calendar.MAY, 4, 22, 0);
        Calendar end2 = new GregorianCalendar(
                2019, Calendar.MAY, 5, 1, 0);
        int amountOwed2 = payCalculator.calculateAmountOwed(start2, end2);
        assertEquals(32, amountOwed2);
    }

    @Test
    public void testFamilyCCalculation() {
        PayCalculator payCalculator = BabysitterService.getPayCalculator(BabysitterService.FAMILY_C);
        int amountOwed = payCalculator.calculateAmountOwed(start, end);
        assertEquals(78, amountOwed);
    }
}
