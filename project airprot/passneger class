//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.PrintStream;
import java.util.ArrayList;

public class Passenger {
    protected static ArrayList<Passenger> exists = new ArrayList();
    protected String Name;
    protected ArrayList<Flight> BookingHistory = new ArrayList();

    public Passenger(String Name) {
        this.Name = Name;
    }

    public String getName() {
        return this.Name;
    }

    public ArrayList<Flight> getBookingHistory() {
        return this.BookingHistory;
    }

    public void bookFlight(Flight flight) {
        this.BookingHistory.add(flight);
        System.out.println("Flight booked successfully!");
    }

    public void viewBookingHistory() {
        System.out.println("Booking History for " + this.Name + ":");

        for(int i = 0; i < this.BookingHistory.size(); ++i) {
            PrintStream var10000 = System.out;
            Object var10001 = this.BookingHistory.get(i);
            var10000.println("Flight number =00" + ((Flight)var10001).getFlight_number());
            var10000 = System.out;
            String var2 = ((Flight)this.BookingHistory.get(i)).Source;
            var10000.println(var2 + "-" + ((Flight)this.BookingHistory.get(i)).destination);
            var10000 = System.out;
            var10001 = this.BookingHistory.get(i);
            var10000.println("date flight : " + ((Flight)var10001).getDeparture_time());
            var10000 = System.out;
            var10001 = this.BookingHistory.get(i);
            var10000.println("your ticket price = " + ((Flight)var10001).getTicket_price() + " $");
            var10000 = System.out;
            var10001 = this.BookingHistory.get(i);
            var10000.println("Transit = " + ((Flight)var10001).isTransit());
        }

    }
}
