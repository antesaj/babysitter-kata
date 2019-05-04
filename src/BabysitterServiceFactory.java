public class BabysitterServiceFactory {
    public static BabysitterService getService(String family) {
        if (family.equals("FamilyA")) {
            return new FamilyAService();
        } else {
            return null;
        }
    }
}
