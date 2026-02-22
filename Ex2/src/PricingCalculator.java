import java.util.*;

public class PricingCalculator {

    public PricingResult calculate(Map<String, MenuItem> menu, List<OrderLine> lines) {
        double subtotal = 0.0;
        List<LineDetail> details = new ArrayList<>();

        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;

            subtotal += lineTotal;
            details.add(new LineDetail(item.name, l.qty, lineTotal));
        }

        return new PricingResult(subtotal, details);
    }
}