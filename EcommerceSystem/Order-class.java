import javax.swing.JOptionPane;

public class Order
{
    protected int customerId;
    protected int orderId;
    protected int nProducts;
    protected Product [] product = new Product[nProducts];
    protected float totalPrice;
    Order(int customerId, int orderId , Product[] prodcut, float totalPrice , int nProducts)
    {
        this.customerId=customerId;
        this.orderId=orderId;
        this.product=prodcut;
        this.totalPrice=totalPrice;
        this.nProducts=nProducts;
    }
    public void printOrder()
    {
        StringBuilder orderInfo = new StringBuilder();
        orderInfo.append("Order ID: ").append(orderId).append("\n");
        orderInfo.append("Customer ID: ").append(customerId).append("\n");
        orderInfo.append("Total Price: ").append(totalPrice).append("\n");
        orderInfo.append("Products in Cart:\n");
        for (int i = 0; i < nProducts; i++)
        {
            if(product[i]== null)
            {
            continue;
            }
            else
            {
                orderInfo.append(product[i].name).append(" - $").append(product[i].price).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, orderInfo.toString(), "Order Summary", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, "Order Succesfully!", "thanks for joinning us ", JOptionPane.INFORMATION_MESSAGE);
        }

     public void printnoorder()
        {
        JOptionPane.showMessageDialog(null, "declined", "thanks for joinning us ", JOptionPane.ERROR_MESSAGE);
        }
}

