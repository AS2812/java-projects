
import javax.swing.JOptionPane;
public class Main {

    public static void main(String[] args)
    {
    String name = JOptionPane.showInputDialog(null,"please enter your name: ", "welcome to the Ecommerce System !", JOptionPane.QUESTION_MESSAGE);
    int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter your id :"));
    String adress = JOptionPane.showInputDialog(null,"please enter you adress: ");
    int nproducts = Integer.parseInt(JOptionPane.showInputDialog(null, "How man products do you wanna add to your cart?"));
    Customer c1 = new Customer(name, adress , Math.abs(id));
    while(true){
     if(nproducts<0)
    {
        nproducts = Integer.parseInt(JOptionPane.showInputDialog(null, "How many products do you want to add to your cart?"));
    }
     else if (nproducts==0)
    {
         JOptionPane.showMessageDialog(null, "Thanks for joinning us");
         System.exit(0);
    }
    else
    {
        break;
    }
    }
    Cart f1 = new Cart(Math.abs(id) , Math.abs(nproducts) );
    int choice   = 0;
    int count = 0 ;
    while( nproducts != count)
    {
    choice=Integer.parseInt(JOptionPane.showInputDialog(null, "which product do you want to add to your cart \n1-smartphone\n2-hoodie\n3-Book"));
    if( choice == 1)
    {
        f1.addProduct(new ElectronicProduct(001 , 600f , "Iphone13" , "Apple" , 3));
        count++;
    }
    else if ( choice ==2)
    {
        f1.addProduct(new ClothingProduct(002 , 250f , "Hoodie" , "Large" , "Melton"));
        count++;
    }
    else if (choice == 3)
    {
        f1.addProduct( new BookProduct(003, 150f , "La Belle Et La Bete" , "Victor_Hugo" , "X_Publications" ));
        count++;
    }
    else
    {
    JOptionPane.showMessageDialog(null, "Invalid Input !", "Please Enter numbers between 1 and 3 ", 0);
    }
    }
    if(f1.calculatePrice()==0)
    {
        f1.placeOrder().printnoorder();
        System.exit(0);
    }
    int answer = (JOptionPane.showConfirmDialog(null, "Your Total price = "+f1.calculatePrice()+"$"+" would you like to place the order?", "confirmation", JOptionPane.YES_NO_OPTION));
    if(answer == 0)
    {
    f1.placeOrder().printOrder();
    }
    else
    {
    f1.placeOrder().printnoorder();
    }
    }
}

