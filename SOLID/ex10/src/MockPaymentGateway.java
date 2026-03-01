public class MockPaymentGateway implements PaymentService {

    @Override
    public String charge(String studentId, double amount) {
        // predictable fake transaction
        return "MOCK-TXN-0001";
    }
}