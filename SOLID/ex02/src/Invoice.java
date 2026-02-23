import java.util.*;

public class Invoice {
    final String id;
    final List<LineDetail> lines;
    final double subtotal;
    final double taxPct;
    final double tax;
    final double discount;
    final double total;

    Invoice(String id,
            List<LineDetail> lines,
            double subtotal,
            double taxPct,
            double tax,
            double discount,
            double total) {

        this.id = id;
        this.lines = lines;
        this.subtotal = subtotal;
        this.taxPct = taxPct;
        this.tax = tax;
        this.discount = discount;
        this.total = total;
    }
}