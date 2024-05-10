public class Customer
{
    protected String name ;
    protected String adress;
    protected int customerid;
    Customer( String name , String adress , int  customerid)
    {
        this.name=name;
        this.adress=adress;
        this.customerid=customerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }
}
