public class SingleRoom implements RoomPricing {

    @Override
    public Money monthly() {
        return new Money(14000.0);
    }
}