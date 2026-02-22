public class FeeResult {

    public final Money monthly;
    public final Money deposit;
    public final Money totalNow;

    public FeeResult(Money monthly, Money deposit) {
        this.monthly = monthly;
        this.deposit = deposit;
        this.totalNow = monthly.plus(deposit);
    }
}