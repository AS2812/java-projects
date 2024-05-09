//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.PrintStream;
import java.util.ArrayList;

public class Pilot {
    protected String name;
    protected ArrayList<Flight> assignedFlights = new ArrayList();

    public Pilot(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Flight> getAssignedFlight() {
        return this.assignedFlights;
    }

    public void viewAssignedFlights() {
        System.out.println("Captaine :" + this.name);
        System.out.println("Assigned Flights:");
        if (this.assignedFlights != null) {
            for(int i = 0; i < this.assignedFlights.size(); ++i) {
                PrintStream var10000 = System.out;
                String var10001 = ((Flight)this.assignedFlights.get(i)).Source;
                var10000.println("from " + var10001 + " to " + ((Flight)this.assignedFlights.get(i)).destination);
                var10000 = System.out;
                Object var2 = this.assignedFlights.get(i);
                var10000.println("data :" + ((Flight)var2).Departure_time);
                var10000 = System.out;
                var2 = this.assignedFlights.get(i);
                var10000.println("flight number : 00" + ((Flight)var2).Flight_number);
                System.out.println("");
            }
        } else {
            System.out.println("No flights assigned yet.");
        }

    }

    public void assignFlight(Flight flight) {
        if (flight != null && this.assignedFlights != null) {
            this.assignedFlights.add(flight);
            System.out.println("Flight assigned successfully to Captaine " + this.name);
        } else {
            System.out.println("Invalid Flight!! ");
        }

    }
}
