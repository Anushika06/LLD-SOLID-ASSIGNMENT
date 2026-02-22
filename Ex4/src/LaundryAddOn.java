public class LaundryAddOn implements AddOnPricing {

    @Override
    public Money monthly() {
        return new Money(500.0);
    }
}