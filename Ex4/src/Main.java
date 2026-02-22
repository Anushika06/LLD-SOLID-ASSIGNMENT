import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Hostel Fee Calculator ===");

        BookingRequest req = new BookingRequest(
                LegacyRoomTypes.DOUBLE,
                List.of(AddOn.LAUNDRY, AddOn.MESS)
        );

        HostelFeeCalculator calc = new HostelFeeCalculator();
        FeeResult result = calc.calculate(req);

        ReceiptPrinter.print(req, result);

        BookingIdGenerator idGen = new BookingIdGenerator();
        String bookingId = idGen.generate();

        FakeBookingRepo repo = new FakeBookingRepo();
        repo.save(bookingId, req, result.monthly, result.deposit);
    }
}