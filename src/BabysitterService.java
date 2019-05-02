import java.util.Date;
import java.util.Calendar;

public class BabysitterService {
    public boolean isAvailable(Calendar date) {
        return date.get(Calendar.HOUR_OF_DAY) <= 4 ||
                date.get(Calendar.HOUR_OF_DAY) >= 17;
    }
}
