public class DoubleRoom implements RoomPricing {

    @Override
    public Money monthly() {
        return new Money(15000.0);
    }
}