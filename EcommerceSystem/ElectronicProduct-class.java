
public class ElectronicProduct extends Product
{

    protected String brand;
    protected int warrantyPeriod;
    ElectronicProduct(int id , float price , String name , String brand , int warrantyPeriod)
    {
        super(id,price,name);
        this.brand=brand;
        this.warrantyPeriod=warrantyPeriod;
    }
    public void setbrand()
    {
        this.brand=brand;
    }
    public String getbrand()
    {
        return brand;
    }
    public void setwarrantyPeriod()
    {
        this.warrantyPeriod=warrantyPeriod;
    }
    public int getwarrantyPeriod()
    {
        return warrantyPeriod;
    }

    public int getProductId()
    {
        return productId;
    }

    public void setProductId(int productId)
    {
        this.productId = productId;
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
