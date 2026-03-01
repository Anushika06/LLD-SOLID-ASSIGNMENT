public class MockDriverAllocator implements DriverAllocationService {

    @Override
    public String allocate(String studentId) {
        // predictable fake driver
        return "MOCK-DRV-01";
    }
}