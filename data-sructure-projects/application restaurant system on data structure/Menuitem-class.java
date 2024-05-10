class MenuItem
{
    private String name;
    private static double price;
    private String description;
    private String category;

    public MenuItem(String name, double price, String description, String category)
    {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

     public  String getName()
     {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public static double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    // Override toString method for easy printing
    @Override
    public String toString()
    {
        return "Name: " + name + ", Price: $" + price + ", Description: " + description + ", Category: " + category;
    }
}
