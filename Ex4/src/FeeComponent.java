public interface FeeComponent {

    Money monthly();

    default Money deposit() {
        return new Money(0); 
    }
}