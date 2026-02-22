import java.util.List;

class PricingResult {
    final double subtotal;
    final List<LineDetail> details;

    PricingResult(double subtotal, List<LineDetail> details) {
        this.subtotal = subtotal;
        this.details = details;
    }
}