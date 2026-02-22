public class GymAddOn implements AddOnPricing {

    @Override
    public Money monthly() {
        return new Money(300.0);
    }
}