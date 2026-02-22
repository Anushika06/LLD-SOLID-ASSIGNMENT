public class GymAddOn implements FeeComponent {

    @Override
    public Money monthly() {
        return new Money(300.0);
    }
}