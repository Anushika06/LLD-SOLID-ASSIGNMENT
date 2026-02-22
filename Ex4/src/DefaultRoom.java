public class DefaultRoom implements FeeComponent {

    public Money monthly() { return new Money(16000.0); }
    public Money deposit() { return new Money(5000.0); }
}