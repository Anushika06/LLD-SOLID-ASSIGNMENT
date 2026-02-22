import java.util.*;

public class CafeteriaSystem {

    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final PricingCalculator pricing = new PricingCalculator();
    private final InvoiceFormatter formatter = new InvoiceFormatter();
    private final InvoiceRepository store = new FileStore();

    private int invoiceSeq = 1000;

    public void addToMenu(MenuItem i) {
        menu.put(i.id, i);
    }

    public void checkout(TaxPolicy taxPolicy,
                         Discount discountPolicy,
                         List<OrderLine> lines) {

        String invId = "INV-" + (++invoiceSeq);

        PricingResult pricingResult = pricing.calculate(menu, lines);
        double subtotal = pricingResult.subtotal;

        double taxPct = taxPolicy.taxPercent();
        double tax = subtotal * (taxPct / 100.0);

        double discount = discountPolicy.discount(subtotal, lines.size());

        double total = subtotal + tax - discount;

        Invoice invoice = new Invoice(
                invId,
                pricingResult.details,
                subtotal,
                taxPct,
                tax,
                discount,
                total
        );

        String printable = formatter.format(invoice);

        System.out.print(printable);

        store.save(invId, printable);

        System.out.println("Saved invoice: " + invId +
                " (lines=" + store.countLines(invId) + ")");
    }
}