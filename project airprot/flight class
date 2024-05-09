//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Flight {
    Scanner user;
    protected static int numOfFlights = 0;
    protected int Flight_number;
    protected String Departure_time;
    protected String destination;
    protected boolean transit;
    protected double Ticket_price;
    protected String Source;

    public Flight() {
        this.user = new Scanner(System.in);
        ++numOfFlights;
        this.Flight_number = numOfFlights;
    }

    public Flight(String destination, boolean transit, double Ticket_price, String source) {
        this();
        this.destination = destination;
        this.transit = transit;
        this.Ticket_price = Ticket_price;
        this.Source = source;
    }

    public int getFlight_number() {
        return this.Flight_number;
    }

    public void setFlight_number(int Flight_number) {
        this.Flight_number = Flight_number;
    }

    public String getDeparture_time() {
        return this.Departure_time;
    }

    public void setDeparture_time() {
        LocalTime time = null;
        LocalDate date = null;

        boolean x;
        String d;
        do {
            x = false;

            try {
                System.out.println("Enter flight time (HH:MM)");
                d = this.user.nextLine();
                time = LocalTime.parse(d, DateTimeFormatter.ofPattern("HH:mm"));
            } catch (DateTimeParseException var5) {
                System.out.println("Enter correct time");
                x = true;
            }
        } while(x);

        do {
            x = false;

            try {
                while(true) {
                    System.out.println("Enter flight date : (dd/mm/yyyy)");
                    d = this.user.nextLine();
                    date = LocalDate.parse(d, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    if (date.isAfter(LocalDate.now())) {
                        break;
                    }

                    System.out.println("flight date must be after today date");
                }
            } catch (DateTimeParseException var6) {
                System.out.println("Enter correct Date");
                x = true;
            }
        } while(x);

        String var10001 = String.valueOf(date);
        this.Departure_time = var10001 + " and time : " + String.valueOf(time);
    }

    public String getDestination() {
        return this.destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public boolean isTransit() {
        return this.transit;
    }

    public void setTransit(boolean transit) {
        this.transit = transit;
    }

    public double getTicket_price() {
        return this.Ticket_price;
    }

    public void setTicket_price(double Ticket_price) {
        this.Ticket_price = Ticket_price;
    }

    public void setSource(String Source) {
        this.Source = Source;
    }

    public String getSource() {
        return this.Source;
    }
}
