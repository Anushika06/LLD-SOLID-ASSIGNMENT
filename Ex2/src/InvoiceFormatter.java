public class InvoiceFormatter {

    public String format(Invoice inv) {
        StringBuilder out = new StringBuilder();

        out.append("Invoice# ").append(inv.id).append("\n");

        for (LineDetail l : inv.lines) {
            out.append(String.format("- %s x%d = %.2f\n", l.name, l.qty, l.total));
        }

        out.append(String.format("Subtotal: %.2f\n", inv.subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", inv.taxPct, inv.tax));
        out.append(String.format("Discount: -%.2f\n", inv.discount));
        out.append(String.format("TOTAL: %.2f\n", inv.total));

        return out.toString();
    }
}