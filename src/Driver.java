import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.NumberFormat;

public class Driver {
    public static void main(String[] args) throws UnregisteredFamilyException {
        Scanner in = new Scanner(System.in);

        BabysitterService service = BabysitterService.getService();

        System.out.println("Babysitter Services\n");

        System.out.println("Registered Families:");
        for (int i = 0; i < service.getRegisteredFamilies().size(); i++) {
            System.out.println(service.getRegisteredFamilies().get(i));
        }

        String family = "";

        do {
            System.out.print("\nWhich family is requesting services (q/Q to quit)? ");
            family = in.nextLine();

            if (family.equalsIgnoreCase("q")) {
                break;
            }

            if (!service.getRegisteredFamilies().contains(family)) {
                System.out.println("That family is not registered.");
                throw new UnregisteredFamilyException();
            }

            System.out.println("\nStart Date and Time\nFF" +
                    "(yyyy.MM.dd at HH:mm)\n" +
                    "Example: 2019.05.07 at 21:00");
            String startDateInput = in.nextLine();
            Date startDateAndTime = parseDate(startDateInput);
            GregorianCalendar start = new GregorianCalendar();
            start.setTime(startDateAndTime);

            System.out.println("\nEnd Date and Time\nFF" +
                    "(yyyy.MM.dd at HH:mm)\n" +
                    "Example: 2019.05.07 at 21:00");
            String endDateInput = in.nextLine();
            Date endDateAndTime = parseDate(endDateInput);
            GregorianCalendar end = new GregorianCalendar();
            end.setTime(endDateAndTime);

            System.out.println(service.isOneNight(start, end));

            service.addReservation(family, start, end);
            PayCalculator calc = service.getPayCalculator(family);
            int totalCost = calc.calculateAmountOwed(start, end);
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            System.out.println("\nReservation added. Amount owed: " + formatter.format(totalCost));

        } while (true);


    }

    public static Date parseDate(String dateInput) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm");
        Date startDate = null;
        try {
            startDate = dateFormat.parse(dateInput);
        } catch (ParseException e) {
            System.out.println("Invalid format. yyyy.MM.dd 'at' HH:mm required.");
            System.out.println("Example: 2019.05.07 at 21:00");
        }
        return startDate;
    }
}
