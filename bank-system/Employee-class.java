import javax.swing.JOptionPane;
import java.util.*;

public class Employee extends Person {
   
    private double salary = 3000.0;
    
    LinkedList<Customer> assignedCustomers = new LinkedList<>();
    int servedCustomers = 0;
    
    Customer currentCustomer;
    
    public Employee(String name, String address, String user_name, String password, String id) {
        super(name, address, user_name, password, id);
    }

    public void changePassword(String pass){
        boolean flag = true;
        
        while(flag){
            String oldPass = JOptionPane.showInputDialog(null, "To change your password, please enter your old password:");
            if(this.password().equals(oldPass)) {
                this.setPassword(pass);
                JOptionPane.showMessageDialog(null, "Password changed successfully, your new password is: " + pass);
                flag = false;
            } else {
                JOptionPane.showMessageDialog(null, "Wrong password");
            }
        }
    }
    
    public String currentCustomer(){
        return currentCustomer + "\nTransaction: " + currentCustomer.wantedTransaction;
    }
    
    public void viewAssignedCustomers() {
        if(currentCustomer != null){
            int ans = JOptionPane.showConfirmDialog(null, "Do you want any other transactions?", "Transaction", JOptionPane.YES_NO_OPTION);
            if(ans == JOptionPane.NO_OPTION) {
                servedCustomers++;
                currentCustomer = null;
            } else {
                return;
            }
        }

        if (assignedCustomers.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No assigned Customers yet");
            return;
        }
        
        for (Customer customer : assignedCustomers) {
            JOptionPane.showMessageDialog(null, customer.toString());
        }

        int proceed = JOptionPane.showConfirmDialog(null, "Do you want to proceed to the next customer?", "Next Customer", JOptionPane.YES_NO_OPTION);
        if(proceed == JOptionPane.YES_OPTION) {
            currentCustomer = assignedCustomers.poll();
        }
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
