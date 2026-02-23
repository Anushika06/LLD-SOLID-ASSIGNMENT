import java.util.*;

public class Demo02 {
    public static void main(String[] args) {

        System.out.println("=== Cafeteria Billing ===");

        CafeteriaSystem sys = new CafeteriaSystem();

        sys.addToMenu(new MenuItem("M1", "Veg Thali", 80.00));
        sys.addToMenu(new MenuItem("C1", "Coffee", 30.00));
        sys.addToMenu(new MenuItem("S1", "Sandwich", 60.00));

        //Student Invoice
        List<OrderLine> studentOrder = List.of(
                new OrderLine("M1", 2),
                new OrderLine("C1", 1)
        );

        sys.checkout(
                new StudentTaxPolicy(),
                new StudentDiscount(),
                studentOrder
        );

        System.out.println();

        // Staff Invoice
        List<OrderLine> staffOrder = List.of(
                new OrderLine("M1", 1),
                new OrderLine("C1", 1),
                new OrderLine("S1", 1)
        );

        sys.checkout(
                new StaffTaxPolicy(),
                new StaffDiscount(),
                staffOrder
        );
    }
}