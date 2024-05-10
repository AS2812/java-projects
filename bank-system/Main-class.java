import javax.swing.*;
import java.util.*;

public class Main {
    static Manager m1 = new Manager("Youssef", "alex", "Jo", "1234", "1");

    public static void main(String[] args) {
        Employee employee1 = new Employee("Abdelrahman", "alex", "abdo", "1234", "1");
        Employee employee2 = new Employee("Akram", "alex", "Akram99", "1234", "2");
        Employee employee3 = new Employee("ZiadA", "alex", "zoz", "1234", "3");
        Employee employee4 = new Employee("ZiadW", "alex", "wael", "1234", "4");
        Employee employee5 = new Employee("Ahmed", "alex", "ayad", "1234", "5");

        m1.adddefaultEmployee(employee1);
        m1.adddefaultEmployee(employee2);
        m1.adddefaultEmployee(employee3);
        m1.adddefaultEmployee(employee4);
        m1.adddefaultEmployee(employee5);


        JOptionPane.showMessageDialog(null, "Welcome to our Bank Here where every deal is legendary");


        int identity=0;
        while (true) {
            while(true) {
                try {
                    String input = "1) Customer\n2) Employee\n3) Manager";
                    identity = tryinput(input);
                    if(identity!=0)
                        break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");

                }
            }
            switch (identity) {
                case 1:
                    Customer currentCustomer = null;
                    boolean flag = true;
                    while (flag) {
                        String newCustomerInput ="1) New customer\n2) Existing customer";
                        int newCustomer = tryinput(newCustomerInput);
                        if (newCustomer == 1) {
                            // Create new customer
                            String name = JOptionPane.showInputDialog("Enter your name");
                            String address = JOptionPane.showInputDialog("Enter your address");
                            String username = JOptionPane.showInputDialog("Enter your username");
                            String password = JOptionPane.showInputDialog("Enter your password");
                            String id = JOptionPane.showInputDialog("Enter your id");
                            String phoneNumber = JOptionPane.showInputDialog("Enter your phone number");
                            double balance = 1000 * Math.random();
                            Customer c1 = new Customer(name, address, username, password, id, phoneNumber, balance);
                            m1.customers.add(c1);
                            currentCustomer = c1;
                            break;
                        } else if (newCustomer == 2) {
                            currentCustomer = customerlogin();
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "Wrong input");
                        }
                    }
                    while (flag) {
                        String transactionInput = "What transaction you want to do " + currentCustomer.name + "?\n1) Deposit or Withdraw\n2) Check balance\n3) Calculate interest\n0) Exit";
                        int option =tryinput(transactionInput);
                        switch (option) {

                            case 0:
                                flag = false;
                                break;

                            case 1:
                                currentCustomer.assignedEmployee = assignEmployee();
                                currentCustomer.assignedEmployee.assignedCustomers.add(currentCustomer);
                                JOptionPane.showMessageDialog(null, "Your assigned Employee:\n" + currentCustomer.assignedEmployee + "\nPlease take a seat and wait for your turn");
                                break;

                            case 2:
                                currentCustomer.checkBalance();
                                break;

                            case 3:
                                String interestInput ="Enter interest rate";
                                double interestRate = tryinput(interestInput);
                                JOptionPane.showMessageDialog(null, "Interest on balance with " + interestRate + " interest rate = " + currentCustomer.calculateInterest(interestRate));
                                break;

                            default:
                                JOptionPane.showMessageDialog(null, "Wrong input");
                        }
                    }

                    break;
                case 2:
                    Employee currentEmployee = emplogin();
                    flag = true;
                    while (flag) {
                        String employeeInput = "Enter transaction you want to do\n1) Change password\n2) View assigned customer\n3) View salary\n4) Currentcustomer\n0) Exit";
                        int option = tryinput(employeeInput);
                        switch (option) {
                            case 0:
                                flag = false;
                                break;
                            case 1:
                                String newPasswordInput = JOptionPane.showInputDialog("Enter new password");
                                currentEmployee.changePassword(newPasswordInput);
                                break;
                            case 2:
                                currentEmployee.viewAssignedCustomers();
                                break;
                            case 3:
                                JOptionPane.showMessageDialog(null, "Your salary = " + currentEmployee.getSalary());
                                break;
                            case 4:
                                int trans;
                                double moni;
                                if(currentEmployee.currentCustomer == null)
                                    break;
                                JOptionPane.showMessageDialog(null, "Current customer: " + currentEmployee.currentCustomer());
                                if (currentEmployee.currentCustomer == null) {
                                    continue;
                                } else {
                                    String transactionTypeInput = "Choose transaction:\n1) Deposit\n2) Withdraw";
                                    trans = tryinput(transactionTypeInput);
                                }
                                switch (trans) {
                                    case 1:
                                        String depositAmountInput = "Enter the amount that the customer wants to deposit: ";
                                        moni = tryinput(depositAmountInput);
                                        currentEmployee.currentCustomer.deposit(moni);
                                        JOptionPane.showMessageDialog(null, "Deposit successful!");
                                        break;
                                    case 2:
                                        String withdrawAmountInput ="Enter the amount that the customer wants to withdraw: ";
                                        moni = tryinput(withdrawAmountInput);
                                        currentEmployee.currentCustomer.withdraw(moni);
                                        JOptionPane.showMessageDialog(null, "Withdrawal successful!");
                                        break;
                                    default:
                                        JOptionPane.showMessageDialog(null, "Wrong input");
                                }
                                break;
                        }
                    }
                case 3:
                    flag = managerLogin();
                    while (flag) {
                        String managerInput = "Enter the transaction you want to do\n1) Change employee password\n2) Add employee\n3) Remove employee\n4) Get Employee record\n0) Exit";
                        
                        int option = tryinput(managerInput);
                        switch (option) {
                            case 0:
                                flag = false;
                                break;
                            case 1:
                                ArrayList<Employee> employees = m1.getEmployees();
                                String employeeIdInput = JOptionPane.showInputDialog("Enter the employee you want to change password for");
                                for (Employee e : employees) {
                                    if (e.id().equals(employeeIdInput))
                                        m1.changeEmployeePassword(e);
                                    JOptionPane.showMessageDialog(null, "Password changed");
                                }
                                flag = anothertransaction();
                                break;
                            case 2:
                                m1.addEmployee();
                                flag = anothertransaction();
                                break;
                            case 3:
                                m1.removeEmployee();
                                flag = anothertransaction();
                                break;
                            case 4:
                                for (Employee e : m1.employees) {
                                    m1.getEmployeeRecords(e);
                                }
                                flag = anothertransaction();
                                break;
                        }
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Wrong input");
            }
        }
    }

    public static Employee emplogin() {
        String username, password;
        while (true) {
            username = JOptionPane.showInputDialog("Enter your username: ");
            for (Employee e : m1.employees) {
                if (username.equalsIgnoreCase(e.username())) {
                    password = JOptionPane.showInputDialog("Enter password: ");
                    if (e.password().equals(password))
                        return e;
                }
            }
            JOptionPane.showMessageDialog(null, "Wrong username or password!");
        }
    }

    public static Customer customerlogin() {
        String username, password;
        while (true) {
            username = JOptionPane.showInputDialog("Enter your username: ");
            for (Customer c : m1.customers) {
                if (username.equalsIgnoreCase(c.username())) {
                    password = JOptionPane.showInputDialog("Enter password: ");
                    if (c.password().equals(password))
                        return c;
                }
            }
            JOptionPane.showMessageDialog(null, "Wrong username or password!");
        }
    }

    public static boolean managerLogin() {
        String username, password;
        while (true) {
            username = JOptionPane.showInputDialog("Enter your username: ");
            if (username.equalsIgnoreCase(m1.username())) {
                password = JOptionPane.showInputDialog("Enter password: ");
                if (password.equals(m1.password()))
                    return true;
            }
            JOptionPane.showMessageDialog(null, "Wrong username or password");
        }
    }


    public static Employee assignEmployee() {
        int randomNum = (int)(Math.random() * 5);
        Employee that_guy = m1.employees.get(randomNum);
        for (int i = 1; i < m1.employees.size(); i++)
            if (m1.employees.get(i).assignedCustomers.size() < that_guy.assignedCustomers.size())
                that_guy = m1.employees.get(i);
        return that_guy;
    }

    public static boolean anothertransaction() {
        String input = "Do you want to make another transaction?\n1) Yes\n2) No";
        int res = tryinput(input);
        return res == 1;
    }
    public static int tryinput(String message){
        int input;
        while(true){
        try{
            String input1=JOptionPane.showInputDialog( message);
            input=Integer.parseInt(input1);
            break;
        }catch(NumberFormatException exception){
            JOptionPane.showMessageDialog(null,"Enter integer number");
        }
        }
    return input;
    
}
}
