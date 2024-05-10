import javax.swing.JOptionPane;
import java.util.*;

public class Manager extends Person {
    double salary = 3100;
    ArrayList<Customer> customers = new ArrayList<>();
    ArrayList<Employee> employees = new ArrayList<>();

    public Manager(String name, String address, String username, String password, String id) {
        super(name, address, username, password, id);
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void changeEmployeePassword(Employee e) {
        String newPassword = JOptionPane.showInputDialog(null, "Enter the new password for the desired employee");
        e.setPassword(newPassword);
    }

    public void addEmployee() {
        String name = JOptionPane.showInputDialog(null, "Enter the employee's name");
        String address = JOptionPane.showInputDialog(null, "Enter their address");
        String username = JOptionPane.showInputDialog(null, "Create a username for them");
        String password = JOptionPane.showInputDialog(null, "Create their password");
        String id = JOptionPane.showInputDialog(null, "Enter their id");

        employees.add(new Employee(name, address, username, password, id));
    }

    public void removeEmployee() {
        StringBuilder employeeList = new StringBuilder();
        for (Employee e : employees) {
            employeeList.append("Employee with id number ").append(e.id()).append(" ").append(e.name).append("\n");
        }
        JOptionPane.showMessageDialog(null, employeeList.toString());

           int  choice = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of the employee you'd like to remove"));
           int choice1=choice-1;
        if(choice1 < 0 || choice1>= employees.size()) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number");}
        else{
            
        JOptionPane.showMessageDialog(null, "Employee removed");
        employees.removeIf(x -> x.id().equals(String.valueOf(choice1)));
        }
    
    }

    public void getEmployeeRecords(Employee employee) {
        JOptionPane.showMessageDialog(null, "Employee " + employee.name + " has served " + employee.servedCustomers + " customers");
    }

    public void adddefaultEmployee(Employee employee) {
        employees.add(employee);
    }
}
