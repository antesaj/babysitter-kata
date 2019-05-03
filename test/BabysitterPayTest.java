import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class BabysitterPayTest {
    private BabysitterService service;

    @Before
    public void setUp() {
        service = new BabysitterService();
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
        Calendar start = new GregorianCalendar(
                2019, Calendar.MAY, 4, 18, 0);
        Calendar end = new GregorianCalendar(
                2019, Calendar.MAY, 5, 3, 0);
        assertTrue(service.isOneNight(start, end));
    }
}
