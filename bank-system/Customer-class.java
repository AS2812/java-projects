
import javax.swing.JOptionPane;
import java.lang.*;

public class Customer extends Person {
 
    private String phoneNumber;
    private double balance;
    String wantedTransaction;
    Employee assignedEmployee;


    public Customer(String name, String address, String username, String password, String id, String phoneNumber, double balance) {
        super(name, address, username, password, id);
        setPhoneNumber(phoneNumber);
        setBalance(balance);
    }



    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = Math.abs(balance);
    }

    public void deposit(double amount) {
        balance += amount;
        JOptionPane.showMessageDialog(null, "Deposit of $" + amount + " successful. Current balance: $" + balance);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            JOptionPane.showMessageDialog(null, "Withdrawal of $" + amount + " successful. Current balance: $" + balance);
        } else {
            JOptionPane.showMessageDialog(null, "Insufficient funds. Withdrawal failed.");
        }
    }

    public void checkBalance() {
        JOptionPane.showMessageDialog(null, "Current balance: $" + balance);
    }

    public double calculateInterest(double rate) {
       
        return balance * (rate / 100);
    }


    public String toString() {
        return super.toString() +
               "\nPhone Number: " + getPhoneNumber() + "\n" +
               "Balance: $" + getBalance();
    }
}
