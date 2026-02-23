public class MessAddOn implements FeeComponent {

    @Override
    public Money monthly() {
        return new Money(1000.0);
    }
}