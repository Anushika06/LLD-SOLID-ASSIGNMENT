public class DefaultRoom implements RoomPricing {

    @Override
    public Money monthly() {
        return new Money(16000.0);
    }
}