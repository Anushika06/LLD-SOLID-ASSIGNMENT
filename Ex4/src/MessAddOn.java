public class MessAddOn implements AddOnPricing {

    @Override
    public Money monthly() {
        return new Money(1000.0);
    }
}