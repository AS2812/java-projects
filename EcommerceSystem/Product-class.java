public class Product
{
   protected int productId ;
   protected float price;
   protected String name;
   Product(int id , float price , String name )
   {
      this.productId=id;
      this.price=price;
      this.name=name;
   }
   public void setproductid()
   {
       this.productId=productId;
   }
   public int getproductid()
   {
       return productId;
   }
   public void setprice()
   {
       this.price=price;
   }
   public float getprice()
   {
       return productId;
   }
   public void setname()
   {
       this.name=name;
   }
   public String getname()
   {
   return name;
   }
}
