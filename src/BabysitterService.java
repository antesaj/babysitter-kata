import java.util.Calendar;
import java.util.ArrayList;
import java.util.HashMap;

public class BabysitterService {
    private HashMap<String, ArrayList<Calendar>> reservations;

    public static final int LATEST_HOUR_AVAILABLE = 4;
    public static final int EARLIEST_HOUR_AVAILABLE = 17;
    public static final int START_TIME_INDEX = 0;
    public static final int END_TIME_INDEX = 1;

    public BabysitterService() {
        reservations = new HashMap<>();
    }

    public void addReservation(String family, Calendar start, Calendar end) {
        if (isAvailable(start) && isAvailable(end) && isOneNight(start, end)) {
            ArrayList<Calendar> times = new ArrayList<>();
            times.add(start);
            times.add(end);
            reservations.put(family, times);
        }
    }

    public boolean isAvailable(Calendar date) {
        boolean isInRange = date.get(Calendar.HOUR_OF_DAY) <= LATEST_HOUR_AVAILABLE ||
                date.get(Calendar.HOUR_OF_DAY) >= EARLIEST_HOUR_AVAILABLE;

        boolean nightIsAvailable = true;
        int requestedMonth = date.get(Calendar.MONTH);
        int requestedDay = date.get(Calendar.DAY_OF_MONTH);
        for (Object value : reservations.values()) {
            ArrayList<Calendar> times = (ArrayList<Calendar>) value;
            int reservedMonth = times.get(START_TIME_INDEX).get(Calendar.MONTH);
            int reservedDay = times.get(START_TIME_INDEX).get(Calendar.DAY_OF_MONTH);
            if (requestedMonth == reservedMonth && requestedDay == reservedDay) {
                nightIsAvailable = false;
            }
        }
        return isInRange && nightIsAvailable;
    }

    public boolean isOneNight(Calendar start, Calendar end) {
        long diffInSeconds = (end.getTimeInMillis() - start.getTimeInMillis()) / 1000;
        int diffInHours = (int) diffInSeconds / 3600;
        return diffInHours <= 11;
    }
}
