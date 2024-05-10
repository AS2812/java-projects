import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class OrderManager  {
    private List<Order> orders;

    public OrderManager() {
        orders = new ArrayList<>();
    }

    public void placeOrder(Order order) {
        orders.add(order);
        System.out.println("Order placed successfully. Order number: " + order.getOrderNumber());
    }

    public void cancelOrder(int orderNumber) {
        Order order = findOrder(orderNumber);
        if (order != null) {
            orders.remove(order);
            System.out.println("Order cancelled successfully.");
        } else {
            System.out.println("Order not found.");
        }
    }

    public Order findOrder(int orderNumber) {
        // Sort the list before performing binary search
        Collections.sort(orders, new Ordercompartor());

        int index = Collections.binarySearch(orders, new Order(orderNumber), new Ordercompartor());
        if (index >= 0) {
            return orders.get(index);
        } else {
            return null;
        }
    }
}
