import java.util.*;

class OrderManager {
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
        for (Order order : orders) {
            if (order.getOrderNumber() == orderNumber) {
                return order;
            }
        }
        return null;
    }
}
