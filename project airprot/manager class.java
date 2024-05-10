//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.ArrayList;

public class Manager {
    protected String name;
    protected ArrayList<Pilot> pilots;
    protected ArrayList<Flight> available = new ArrayList();
    protected ArrayList<Flight> flights = new ArrayList();

    public ArrayList<Flight> getFlights() {
        return this.flights;
    }

    public ArrayList<Flight> getAvailable() {
        return this.available;
    }

    public Manager(String name) {
        this.name = name;
        this.pilots = new ArrayList();
    }

    public void setnamepilots(Pilot joe) {
        this.pilots.add(joe);
    }

    public void addPilot(Pilot pilot) {
        if (pilot != null) {
            this.pilots.add(pilot);
            System.out.println("Pilot " + pilot.getName() + " added successfully!");
        } else {
            System.out.println("Invalid pilot object. Please provide a Pilot object.");
        }

    }

    public void removePilot(Pilot pilot) {
        if (this.pilots.contains(pilot)) {
            for(int i = 0; i < pilot.assignedFlights.size(); ++i) {
                this.available.add((Flight)pilot.assignedFlights.get(i));
            }

            this.pilots.remove(pilot);
            System.out.println("Pilot " + pilot.getName() + " removed successfully!");
        } else {
            System.out.println("Pilot " + pilot.getName() + " not found in the system.");
        }

    }

    public void manageSystem() {
        System.out.println("Manager " + this.name + " is managing the airport system.");
        System.out.println("the Pilots are in the system : ");
        if (this.pilots.isEmpty()) {
            System.out.println("no pilots are in the system !");
        } else {
            for(int i = 0; i < this.pilots.size(); ++i) {
                System.out.println(i + 1 + "-pilot  " + ((Pilot)this.pilots.get(i)).name);
            }
        }

    }

    public void addFlight(String source, String destination, double price, boolean transit) {
        Flight flight = new Flight();
        flight.setDestination(destination);
        flight.setSource(source);
        flight.setTicket_price(price);
        flight.setTransit(transit);
        flight.setDeparture_time();
        this.available.add(flight);
        this.flights.add(flight);
        System.out.println("Flight from " + source + " to " + destination + " added successfully!");
    }

    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
    }
}
