public class ClothingProduct extends Product
{
    protected String size ;
    protected String fabric;
    ClothingProduct(int id , float price , String name , String size , String fabric)
    {
        super(id, price , name);
        this.size=size;
        this.fabric=fabric;
    }

    public String getSize()
    {
        return size;
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

    public void setSize(String size)
    {
        this.size = size;
    }

    public String getFabric()
    {
        return fabric;
    }

    public void setFabric(String fabric)
    {
        this.fabric = fabric;
    }
}
