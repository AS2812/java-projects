import java.util.*;
class Order {
    private int orderNumber;
    private List<OrderItem> items;

    public Order(int orderNumber)
    {
        this.orderNumber = orderNumber;
        items = new ArrayList<>();
    }


    public void addItem(String itemName, int quantity, double price)
    {
        items.add(new OrderItem(itemName, quantity, price));
    }


    public void removeItem(String itemName)
    {
        items.removeIf(item -> item.getItemName().equals(itemName));
    }


    public double calculateTotalCost(double taxRate, double gratuityRate) {
        double subtotal = 0;
        for (OrderItem item : items)
        {
            subtotal += item.calculateSubtotal();
        }
        double tax = subtotal * taxRate;
        double gratuity = subtotal * gratuityRate;
        return subtotal + tax + gratuity;
    }


    public int getOrderNumber()
    {
        return orderNumber;
    }

    public List<OrderItem> getItems()
    {
        return items;
    }
}
