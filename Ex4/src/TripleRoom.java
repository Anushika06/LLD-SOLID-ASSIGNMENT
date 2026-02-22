public class TripleRoom implements RoomPricing {

    @Override
    public Money monthly() {
        return new Money(12000.0);
    }
}