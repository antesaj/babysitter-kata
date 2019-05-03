import java.util.Calendar;
import java.util.ArrayList;
import java.util.HashMap;

public class BabysitterService {
    private HashMap<String, ArrayList<Calendar>> reservations;

    public static final int LATEST_HOUR_AVAILABLE = 4;
    public static final int EARLIEST_HOUR_AVAILABLE = 17;

    public void addReservation(String family, Calendar start, Calendar end) {
        if (isAvailable(start) && isAvailable(end) && isOneNight(start, end)) {

        }
    }

    public boolean isAvailable(Calendar date) {
        return date.get(Calendar.HOUR_OF_DAY) <= LATEST_HOUR_AVAILABLE ||
                date.get(Calendar.HOUR_OF_DAY) >= EARLIEST_HOUR_AVAILABLE;
    }

    public boolean isOneNight(Calendar start, Calendar end) {
        long diffInSeconds = (end.getTimeInMillis() - start.getTimeInMillis()) / 1000;
        int diffInHours = (int) diffInSeconds / 3600;
        return diffInHours <= 11;
    }
}
