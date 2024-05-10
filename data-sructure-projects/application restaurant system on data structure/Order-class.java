import java.util.*;
class Order {
    private int orderNumber;
    private List<OrderItem> items;

    public Order(int orderNumber) {
        this.orderNumber = orderNumber;
        items = new ArrayList<>();
    }

    public void addItem(String itemName, int quantity, double price) {
        items.add(new OrderItem(itemName, quantity, price));
    }

    public void removeItem(String itemName) {
        items.removeIf(item -> item.getItemName().equals(itemName));
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (OrderItem item : items) {
            totalPrice += item.calculateSubtotal();
        }
        return totalPrice;
    }

    public void displayOrderDetails() {
        System.out.println("Order Number: " + orderNumber);
        System.out.println("Items:");
        for (OrderItem item : items) {
            System.out.println("- " + item.getItemName() + " | Quantity: " + item.getQuantity() + " | Price: $" + item.getPrice());
        }
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public List<OrderItem> getItems() {
        return items;
    }
}
