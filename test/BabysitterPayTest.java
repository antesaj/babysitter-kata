import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;

public class BabysitterPayTest {
    @Test
    public void isAvailableFailsWhenRequestingTimeBeforeFivePM() {
        BabysitterService service = new BabysitterService();
        assertFalse(service.isAvailable(new GregorianCalendar(
                2019, Calendar.MAY, 2, 2, 0).getTime()));
    }
}
