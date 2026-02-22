public class DoubleRoom implements FeeComponent {

    @Override
    public Money monthly() {
        return new Money(15000.0);
    }

    @Override
    public Money deposit() {
        return new Money(5000.0);
    }
}