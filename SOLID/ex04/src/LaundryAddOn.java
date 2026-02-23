public class LaundryAddOn implements FeeComponent {

    @Override
    public Money monthly() {
        return new Money(500.0);
    }
}