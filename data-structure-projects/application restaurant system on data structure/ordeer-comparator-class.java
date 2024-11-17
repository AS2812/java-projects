import java.util.Comparator;

public class Ordercompartor implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        // Assuming getOrderNumber() returns an integer representing the order number
        return Integer.compare(o1.getOrderNumber(), o2.getOrderNumber());
    }
}
