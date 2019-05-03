import java.util.Calendar;

public class BabysitterService {
    public static final int LATEST_HOUR_AVAILABLE = 4;
    public static final int EARLIEST_HOUR_AVAILABLE = 17;

    public boolean isAvailable(Calendar date) {
        return date.get(Calendar.HOUR_OF_DAY) <= LATEST_HOUR_AVAILABLE ||
                date.get(Calendar.HOUR_OF_DAY) >= EARLIEST_HOUR_AVAILABLE;
    }

    public boolean isOneNight(Calendar start, Calendar end) {
        return true;
    }
}
