import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sun.util.calendar.Gregorian;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Test cases used to test drive the BabysitterService application.
 *
 * @author Andrew Antes
 */
public class BabysitterPayTest {
    private BabysitterService service;
    private Calendar start;
    private Calendar end;

    private GregorianCalendar generateDatetime(int day, int hour, int min) {
        return new GregorianCalendar(2019, Calendar.MAY, day, hour, min);
    }

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

    @Test(expected = UnregisteredFamilyException.class)
    public void unregisteredFamilyExceptionWhenRegisteringUnrecognizedFamily()
        throws UnregisteredFamilyException, ReservationOutOfRangeException {
        service.addReservation("FamilyD", start, end);
    }

    @Test(expected = ReservationOutOfRangeException.class)
    public void reservationOutOfRangeExceptionWhenAttemptingMoreThanOneNight()
        throws ReservationOutOfRangeException, UnregisteredFamilyException {
        Calendar newDay = new GregorianCalendar(
                2019, Calendar.MAY, 6, 3,0);
        service.addReservation(BabysitterService.FAMILY_A, start, newDay);
    }


    @Test
    public void isOneNightPassesOnlyWhenRangeIsLessThanElevenHours() {
        Calendar newDay = new GregorianCalendar(
                2019, Calendar.MAY, 6, 3,0);
        assertTrue(service.isOneNight(start, end));
        assertFalse(service.isOneNight(start, newDay));
    }

    @Test
    public void isAvailableFailsWhenRequestingReservedNight() throws ReservationOutOfRangeException {
        String family = BabysitterService.FAMILY_A;
        try {
            service.addReservation(family, start, end);
        } catch (UnregisteredFamilyException e) {
            e.printStackTrace();
        }
        assertFalse(service.isAvailable(start));
        BabysitterService service2 = BabysitterService.getService();
        assertFalse(service2.isAvailable(start));
    }

    @Test
    public void testFamilyAPayCalculation() {
        // Pays $15 per hour before 11pm, $20 per hour rest of night.
        PayCalculator payCalculator = BabysitterService.getPayCalculator(BabysitterService.FAMILY_A);
        GregorianCalendar startAtNinePM = generateDatetime(6, 21, 0);
        GregorianCalendar endAtElevenPM = generateDatetime(6, 23, 0);
        GregorianCalendar startAtSixPM = generateDatetime(6, 18, 0);
        GregorianCalendar endAtThreeAM = generateDatetime(7, 3, 0);
        GregorianCalendar startAtTwoAM = generateDatetime(7,2,0);
        GregorianCalendar endAtFourAM = generateDatetime(7,4,0);

        int amountOwed = payCalculator.calculateAmountOwed(startAtNinePM, endAtElevenPM);
        int amountOwed2 = payCalculator.calculateAmountOwed(startAtSixPM, endAtThreeAM);
        int amountOwed3 = payCalculator.calculateAmountOwed(startAtTwoAM, endAtFourAM);

        assertEquals(30, amountOwed); // 15*2
        assertEquals(155, amountOwed2); // 15*5 + 20*4
        assertEquals(40, amountOwed3); // 20*2
    }

    @Test
    public void testFamilyBCalculation() {
        PayCalculator payCalculator = BabysitterService.getPayCalculator(BabysitterService.FAMILY_B);

    }

    @Test
    public void testFamilyCCalculation() {
        PayCalculator payCalculator = BabysitterService.getPayCalculator(BabysitterService.FAMILY_C);

    }
}
