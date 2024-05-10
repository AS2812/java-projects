//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.InputMismatchException;
import java.util.Scanner;

public class Airport {
    public static Manager manager = new Manager(" Jason ");
    public static Pilot pilot = new Pilot(" Joe");

    public Airport() {
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Airport Management System!");
        manager.setnamepilots(pilot);

        while(true) {
            while(true) {
                try {
                    System.out.println("Please select your role:");
                    System.out.println("1. Passenger");
                    System.out.println("2. Pilot");
                    System.out.println("3. Manager");
                    System.out.println("4. Exit");
                    int choice = scan.nextInt();
                    scan.nextLine();
                    switch (choice) {
                        case 1:
                            if (manager.flights.isEmpty()) {
                                System.out.println("No Flights right now\nWe are really sorry!");
                            } else {
                                passengerMenu(scan);
                            }
                            break;
                        case 2:
                            pilotMenu(scan, manager);
                            break;
                        case 3:
                            managerMenu(scan, manager);
                            break;
                        case 4:
                            System.out.println("Exiting the program. Goodbye!");
                            return;
                        default:
                            System.out.println("Invalid choice. Please select a valid option.");
                    }
                } catch (InputMismatchException var3) {
                    System.out.println("wrong input! please enter valid choice number");
                    scan.nextLine();
                }
            }
        }
    }

    public static void passengerMenu(Scanner scan) {
        Passenger pass = null;

        try {
            int ch;
            if (Passenger.exists.isEmpty()) {
                ch = 1;
            } else {
                System.out.println("Passenger menu:");
                System.out.println("1. New Passenger");
                System.out.println("2. Existing Passenger");
                ch = scan.nextInt();
                scan.nextLine();
            }

            int Pass_num;
            switch (ch) {
                case 1:
                    System.out.println("Enter your name");
                    Passenger.exists.add(new Passenger(scan.nextLine()));
                    pass = (Passenger)Passenger.exists.getLast();
                    break;
                case 2:
                    System.out.println("Who are You?");

                    for(Pass_num = 0; Pass_num < Passenger.exists.size(); ++Pass_num) {
                        System.out.println(Pass_num + 1 + ") " + ((Passenger)Passenger.exists.get(Pass_num)).getName());
                    }

                    try {
                        Pass_num = scan.nextInt();
                        pass = (Passenger)Passenger.exists.get(Pass_num - 1);
                    } catch (IndexOutOfBoundsException var7) {
                        System.err.println("wrong input! please enter valid choice number");
                    } catch (NullPointerException var8) {
                        System.err.println("wrong input! please enter valid choice number");
                    }
                    break;
                default:
                    System.out.println("Enter a vaild num");
            }

            System.out.println("Welcome " + pass.getName());
            System.out.println("1. book a flight.");
            System.out.println("2. view booking history");
            Pass_num = scan.nextInt();
            scan.nextLine();
            switch (Pass_num) {
                case 1:
                    int choice_f;
                    for(choice_f = 0; choice_f < manager.flights.size(); ++choice_f) {
                        System.out.println(choice_f + 1 + ")   from  " + ((Flight)manager.flights.get(choice_f)).getSource() + " to " + ((Flight)manager.flights.get(choice_f)).getDestination());
                        System.out.println("Date : " + ((Flight)manager.flights.get(choice_f)).Departure_time);
                    }

                    choice_f = scan.nextInt() - 1;
                    System.out.println("choose the class of your flight ");
                    System.out.println("you have 4 class :");
                    System.out.println("1-Economy class = 100 $\n2-Premium Economy class = 200 $\n3-Business class = 300 $ \n4-First Class = 400 $");
                    int price = scan.nextInt();
                    scan.nextLine();
                    double[] prices = new double[]{100.0, 200.0, 300.0, 400.0};
                    ((Flight)manager.flights.get(choice_f)).setTicket_price(prices[price - 1]);
                    pass.bookFlight((Flight)manager.flights.get(choice_f));
                    break;
                case 2:
                    pass.viewBookingHistory();
                    break;
                default:
                    System.out.println("invalid choice");
            }
        } catch (InputMismatchException var9) {
            System.err.println("wrong input! please enter valid choice number");
            scan.nextLine();
        }

    }

    public static void pilotMenu(Scanner scan, Manager manager) {
        try {
            if (manager.pilots.isEmpty()) {
                System.out.println("Error: No pilots in the system!");
            } else {
                System.out.println("Pilot Menu:");
                System.out.println("Select Captain:");

                int captaine;
                for(captaine = 0; captaine < manager.pilots.size(); ++captaine) {
                    System.out.println(captaine + 1 + " " + ((Pilot)manager.pilots.get(captaine)).getName());
                }

                captaine = scan.nextInt();
                scan.nextLine();
                if (captaine > 0 && captaine <= manager.pilots.size()) {
                    System.out.println("\n1. View Assigned Flights");
                    System.out.println("2. Assign Flight");
                    int choice = scan.nextInt();
                    scan.nextLine();
                    switch (choice) {
                        case 1:
                            ((Pilot)manager.pilots.get(captaine - 1)).viewAssignedFlights();
                            break;
                        case 2:
                            if (manager.getAvailable().isEmpty()) {
                                System.out.println("No flights flights for assignment.");
                            } else {
                                System.out.println("Choose a flight to assign:");

                                int flightChoice;
                                Flight flightToAssign;
                                for(flightChoice = 0; flightChoice < manager.getAvailable().size(); ++flightChoice) {
                                    flightToAssign = (Flight)manager.getAvailable().get(flightChoice);
                                    System.out.println(flightChoice + 1 + ". Flight 00" + flightToAssign.Flight_number + " from " + flightToAssign.getSource() + " to " + flightToAssign.getDestination());
                                }

                                flightChoice = scan.nextInt();
                                scan.nextLine();
                                if (flightChoice > 0 && flightChoice <= manager.getAvailable().size()) {
                                    flightToAssign = (Flight)manager.getAvailable().get(flightChoice - 1);
                                    ((Pilot)manager.pilots.get(captaine - 1)).getAssignedFlight().add(flightToAssign);
                                    System.out.println("Flight assigned successfully!");
                                    manager.getAvailable().remove(manager.available.indexOf(flightToAssign));
                                } else {
                                    System.out.println("Invalid flight choice.");
                                }
                            }
                            break;
                        default:
                            System.out.println("Invalid choice. Please select a valid option.");
                    }
                } else {
                    System.out.println("Invalid choice captaine.");
                }
            }
        } catch (InputMismatchException var6) {
            System.out.println("Wrong input! please enter valid choice number");
            scan.nextLine();
        }

    }

    public static void managerMenu(Scanner scan, Manager manager) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Manager menu:");
            System.out.println("1. Add pilot");
            System.out.println("2. Remove Pilot");
            System.out.println("3. Ask for manager");
            System.out.println("4. Add Flight");
            int choice_m = scan.nextInt();
            scan.nextLine();
            switch (choice_m) {
                case 1:
                    System.out.println("Please enter the name of pilot : ");
                    String name = sc.nextLine();
                    manager.addPilot(new Pilot(name));
                    break;
                case 2:
                    if (manager.pilots.size() != 0) {
                        System.out.println("please choose which one of pilot do you want remove : ");

                        int numb;
                        for(numb = 0; numb < manager.pilots.size(); ++numb) {
                            System.out.println(numb + 1 + " " + ((Pilot)manager.pilots.get(numb)).name);
                        }

                        numb = sc.nextInt();
                        manager.removePilot((Pilot)manager.pilots.get(numb - 1));
                    } else {
                        System.out.println("no longer pilots in the system !");
                    }
                    break;
                case 3:
                    manager.manageSystem();
                    break;
                case 4:
                    System.out.println("Enter source: ");
                    String source = scan.nextLine();
                    System.out.println("Enter destination: ");
                    String destination = scan.nextLine();

                    int x;
                    do {
                        System.out.println("choose do you want to transit : \n1-yes\n2-no");
                        x = scan.nextInt();
                        scan.nextLine();
                    } while(x != 1 && x != 2);

                    boolean[] transit = new boolean[]{true, false};
                    manager.addFlight(source, destination, 0.0, transit[x - 1]);
                    break;
                default:
                    System.out.println("invalid choice");
            }
        } catch (InputMismatchException var9) {
            System.out.println("enter valid choice number");
            scan.nextLine();
        } catch (IndexOutOfBoundsException var10) {
            System.out.println("Enter a vali4d number");
        }

    }
}
