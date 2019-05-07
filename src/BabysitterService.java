import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class BabysitterService {
    public static HashMap<String, ArrayList<Calendar>> reservations;

    public static final int LATEST_HOUR_AVAILABLE = 4;
    public static final int EARLIEST_HOUR_AVAILABLE = 17;
    public static final int START_TIME_INDEX = 0;

    public static final String FAMILY_A = "FamilyA";
    public static final String FAMILY_B = "FamilyB";
    public static final String FAMILY_C = "FamilyC";

    private List<String> registeredFamilies;

    private static BabysitterService service = null;
    private BabysitterService() {
        reservations = new HashMap<>();
        registeredFamilies = Arrays.asList("FamilyA", "FamilyB", "FamilyC");
    }

    public static BabysitterService getService() {
        if (service == null) {
            service = new BabysitterService();
        }
        return service;
    }

    public static PayCalculator getPayCalculator(String family) {
        if (family.equals(FAMILY_A)) {
            return new FamilyACalculator();
        } else if (family.equals(FAMILY_B)) {
            return new FamilyBCalculator();
        } else if (family.equals(FAMILY_C)) {
            return new FamilyCCalculator();
        } else {
            return null;
        }
    }

    public void addReservation(String family, Calendar start, Calendar end)
            throws UnregisteredFamilyException, ReservationOutOfRangeException {
        if (!registeredFamilies.contains(family)) {
            throw new UnregisteredFamilyException();
        }

        if (!isOneNight(start, end)) {
            throw new ReservationOutOfRangeException();
        }

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

    public List<String> getRegisteredFamilies() {
        return registeredFamilies;
    }
}
