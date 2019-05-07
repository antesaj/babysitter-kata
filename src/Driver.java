import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Driver {
    public static void main(String[] args) throws UnregisteredFamilyException {
        Scanner in = new Scanner(System.in);

        BabysitterService service = BabysitterService.getService();

        System.out.println("Babysitter Services\n");
        System.out.println("Registered Families:");
        for (int i = 0; i < service.getRegisteredFamilies().size(); i++) {
            System.out.println(service.getRegisteredFamilies().get(i));
        }

        System.out.print("\nWhich family is requesting services? ");
        String family = in.nextLine();

        if (!service.getRegisteredFamilies().contains(family)) {
            System.out.println("That family is not registered.");
            throw new UnregisteredFamilyException();
        }

        System.out.println("\nStart Date and Time\nFF" +
                "(yyyy.MM.dd at HH:mm)\n" +
                "Example: 2019.05.07 at 21:00");
        String startDateInput = in.nextLine();
        Date startDateAndTime = parseDate(startDateInput);

        System.out.println("\nStart Date and Time\nFF" +
                "(yyyy.MM.dd at HH:mm)\n" +
                "Example: 2019.05.07 at 21:00");
        String endDateInput = in.nextLine();
        Date endDateAndTime = parseDate(endDateInput);

    }

    public static Date parseDate(String dateInput) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm");
        Date startDate = null;
        try {
            startDate = dateFormat.parse(dateInput);
        } catch (ParseException e) {
            System.out.println("Invalid format. yyyy.MM.dd 'at' HH:mm z required.");
            System.out.println("Example: 2019.05.07 at 21:00");
        }
        return startDate;
    }
}
