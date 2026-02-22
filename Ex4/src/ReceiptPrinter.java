public class ReceiptPrinter {

    public static void print(BookingRequest req, FeeResult res) {

        System.out.println("Room: " +
                LegacyRoomTypes.nameOf(req.roomType) +
                " | AddOns: " + req.addOns);

        System.out.println("Monthly: " + res.monthly);
        System.out.println("Deposit: " + res.deposit);
        System.out.println("TOTAL DUE NOW: " + res.totalNow);
    }
}