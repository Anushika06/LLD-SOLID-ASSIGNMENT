public class TripleRoom implements FeeComponent {

    public Money monthly() { return new Money(12000.0); }
    public Money deposit() { return new Money(5000.0); }
}