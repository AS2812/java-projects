import java.time.LocalTime;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuManager menuManager = new MenuManager();
        OrderManager orderManager = new OrderManager();
        ReservationSystem reservationSystem = new ReservationSystem();

        boolean exit = false;

        while (!exit) {
            try {
                System.out.println("\nRestaurant System Menu:");
                System.out.println("1. Menu Management");
                System.out.println("2. Order Management");
                System.out.println("3. Reservation System");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        handleMenuManagement(scanner, menuManager);
                        break;
                    case 2:
                        handleOrderManagement(scanner, orderManager);
                        break;
                    case 3:
                        handleReservationSystem(scanner, reservationSystem);
                        break;
                    case 4:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                scanner.nextLine();
            }
        }

        System.out.println("Exiting Restaurant System. Goodbye!");
    }

    private static void handleMenuManagement(Scanner scanner, MenuManager menuManager) {
        boolean exitMenuManagement = false;
        while (!exitMenuManagement) {
            try {
                System.out.println("\nMenu Management:");
                System.out.println("1. Add Menu Item");
                System.out.println("2. Update Menu Item");
                System.out.println("3. Remove Menu Item");
                System.out.println("4. Search Menu Item by Name");
                System.out.println("5. Search Menu Items by Category");
                System.out.println("6. Display Menu");
                System.out.println("7. Back to Main Menu");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter item name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter item price: ");
                        double price = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Enter item description: ");
                        String description = scanner.nextLine();
                        System.out.print("Enter item category: ");
                        String category = scanner.nextLine();
                        menuManager.addMenu(name, price, description, category);
                        break;
                    case 2:
                        System.out.print("Enter item name to update: ");
                        String itemName = scanner.nextLine();
                        System.out.print("Enter new price: ");
                        double newPrice = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Enter new description: ");
                        String newDescription = scanner.nextLine();
                        System.out.print("Enter new category: ");
                        String newCategory = scanner.nextLine();
                        menuManager.updateMenu(itemName, newPrice, newDescription, newCategory);
                        break;
                    case 3:
                        System.out.print("Enter item name to remove: ");
                        String itemToRemove = scanner.nextLine();
                        menuManager.removeItem(itemToRemove);
                        break;
                    case 4:
                        System.out.print("Enter item name to search: ");
                        String searchName = scanner.nextLine();
                        MenuItem foundItem = menuManager.searchName(searchName);
                        if (foundItem != null) {
                            System.out.println("Found item: " + foundItem);
                        } else {
                            System.out.println("Item not found.");
                        }
                        break;
                    case 5:
                        System.out.print("Enter category to search: ");
                        String searchCategory = scanner.nextLine();
                        List<MenuItem> foundItems = menuManager.searchCategory(searchCategory);
                        if (!foundItems.isEmpty()) {
                            System.out.println("Found items:");
                            for (MenuItem item : foundItems) {
                                System.out.println(item);
                            }
                        } else {
                            System.out.println("No items found in the category.");
                        }
                        break;
                    case 6:
                        System.out.println("\nMenu:");
                        menuManager.displayMenu();
                        break;
                    case 7:
                        exitMenuManagement = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("An error occurred in menu management: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private static void handleOrderManagement(Scanner scanner, OrderManager orderManager) {
        boolean exitOrderManagement = false;
        while (!exitOrderManagement) {
            try {
                System.out.println("\nOrder Management:");
                System.out.println("1. Place Order");
                System.out.println("2. Cancel Order");
                System.out.println("3. Back to Main Menu");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

              switch (choice) {
    case 1:
    System.out.print("Enter order number: ");
    int orderNumber = scanner.nextInt();
    Order order = new Order(orderNumber);
    boolean addMoreItems = true;
    while (addMoreItems) {
        System.out.println("\nAvailable Menu Items:");
        MenuManager.displayMenu();

        boolean validItemName = false;
        String itemName = "";
        while (!validItemName) {
            System.out.print("Enter the name of the item you want to add to the order: ");
            itemName = scanner.nextLine().trim();
            // Retrieve the selected menu item
            MenuItem menuItem = MenuManager.searchName(itemName);
            if (menuItem == null) {
                System.out.println("Item not found. Please choose a valid item name.");
            } else {
                validItemName = true;
            }
        }

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        // Calculate the total price for this item based on quantity
        double totalPrice = MenuItem.getPrice() * quantity;

        // Add the item to the order
        order.addItem(itemName, quantity, totalPrice);

        // Ask if the user wants to add more items
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Add more items? (yes/no): ");
            String addMore = scanner.next().toLowerCase();
            if (addMore.equals("yes")) {
                validInput = true;
            } else if (addMore.equals("no")) {
                validInput = true;
                addMoreItems = false;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }

    // Display the order details and confirm
    System.out.println("\nOrder Summary:");
    order.displayOrderDetails();
    System.out.println("Total Price: $" + order.calculateTotalPrice());

    boolean validConfirm = false;
    while (!validConfirm) {
        System.out.print("Are you sure you want to place this order? (yes/no): ");
        String confirmOrder = scanner.next().toLowerCase();
        if (confirmOrder.equals("yes")) {
            validConfirm = true;
            orderManager.placeOrder(order);
            System.out.println("Order placed successfully!");
        } else if (confirmOrder.equals("no")) {
            validConfirm = true;
            System.out.println("Order placement cancelled.");
        } else {
            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
        }
    }
    break;
                case 2:
                    System.out.print("Enter order number to cancel: ");
                    int orderNumToCancel = scanner.nextInt();
                    orderManager.cancelOrder(orderNumToCancel);
                    break;
                case 3:
                    exitOrderManagement = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("An error occurred in order management: " + e.getMessage());
            scanner.nextLine();
        }
    }
}
    private static void handleReservationSystem(Scanner scanner, ReservationSystem reservationSystem) {
        boolean exitReservationSystem = false;
        while (!exitReservationSystem) {
            try {
                System.out.println("\nReservation System:");
                System.out.println("1. Make Reservation");
                System.out.println("2. Cancel Reservation");
                System.out.println("3. Display Reservations");
                System.out.println("4. Back to Main Menu");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                                switch (choice) {
                    case 1:
                        System.out.print("Enter reservation number: ");
                        int reservationNumber = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter customer name: ");
                        String customerName = scanner.nextLine();
                        System.out.print("Enter number of people: ");
                        int numberOfPeople = scanner.nextInt();
                        scanner.nextLine();
                         LocalDate currentDate = LocalDate.now();
                         LocalDate date;
                         do {
                             System.out.print("Enter date (YYYY-MM-DD): ");
                             date = LocalDate.parse(scanner.nextLine());
                             if (date.isBefore(currentDate)) {
                                 System.out.println("Date cannot be before today's date. Please enter a valid date.");
                                                            }
                                } while (date.isBefore(currentDate));
                                 // Loop until a valid time is entered
                                    boolean validTime = false;
                                    String time = "";
                                     while (!validTime) {
                                    System.out.print("Enter time (HH:mm): ");
                                      time = scanner.nextLine();
                                        try {
                                            LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
                                            validTime = true;
                                             } catch (Exception e) {
                                                System.out.println("Invalid time format. Please enter time in HH:mm format.");
                                                                    }
                                                     }
                                                        reservationSystem.makeReservation(reservationNumber, customerName, numberOfPeople, date.toString(), time);
                                     break;
                    case 2:
                        System.out.print("Enter reservation number to cancel: ");
                        int reservationNumToCancel = scanner.nextInt();
                        reservationSystem.cancelReservation(reservationNumToCancel);
                        break;
                    case 3:
                        reservationSystem.displayReservations();
                        break;
                    case 4:
                        exitReservationSystem = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("An error occurred in reservation system: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }
}
