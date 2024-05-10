
public class Cart
{
    protected int customerId;
    protected int nProducts;
    protected int capacity = 0;
    protected Product[]products;
    protected float totaleprice = 0;

    public Cart(int customerID, int nProducts)
    {
        this.customerId = customerID;
        this.nProducts = nProducts;
        this.products = new Product[nProducts];
    }
    public void addProduct(Product product)
    {
     products[this.capacity] = product;
         this.capacity++;
    }
    public void removeProduct(int position)
    {
       Product [] newcart = new Product [nProducts];
     for(int i = 0 ; i < nProducts ; i++)
     {
         if( i != position-1 ) //index
         {
             newcart[i] = products[i];
         }
         products[i] =  newcart[i];
     }
    }
    public float calculatePrice()
    {
        totaleprice=0;
        for(int i = 0 ; i<nProducts ; i++)
        {
         if(products[i]!=null)
         {
             totaleprice +=products[i].price;
         }
        }
        return totaleprice;
    }
    public Order placeOrder()
    {
    Order o1 =  new Order(Math.absExact(customerId),Math.abs(1), products , Math.abs(totaleprice) , Math.abs(nProducts));
    return o1;
    }

    public int getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(int customerId)
    {
        this.customerId = customerId;
    }

    public int getnProducts()
    {
        return nProducts;
    }

    public void setnProducts(int nProducts)
    {
        this.nProducts = nProducts;
    }

    public Product[] getProducts()
    {
        return products;
    }
}
