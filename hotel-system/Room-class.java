package safwa;
import java.util.Random;
import javax.swing.JOptionPane;

public class Room {

   
    int ID, NumOfBeds;
    double Price;
    boolean Booked;
    int nights;

    Room(int ID) {
        this.ID = ID;
        this.Booked = generateBoolean(); 
        this.NumOfBeds = generateNumOfBeds(); 
        if (this.Booked) {
            this.nights = generateNights(); 
        } else {
            this.nights = 0;
        }
        this.Price = NumOfBeds * 150; 
    }

    private boolean generateBoolean() {
        return new Random().nextBoolean();
    }

    private int generateNumOfBeds() {
        return new Random().nextInt(1, 4);
    }

    private int generateNights() {
        return new Random().nextInt(1, 5);
    }

    String getRoomInfo() {
        return "Room " + this.ID + ", Status: " + (this.Booked ? "Booked\n" : "Available\n")
                + this.NumOfBeds + " beds\n" + "Price per night: " + this.Price + (this.Booked ? "\nBooked for: " + this.nights + " night(s)" : "");
    }

    void Reservation() {
        int nights;

        try {
            nights = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter number of nights you want to stay:"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number of nights!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (this.nights != 0) {
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to reserve this room?\n"
                    + "It will be a reservation for " + this.nights + " days from now.\n"
                    + " Yes/No", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                this.nights += nights;
            }
        } else {
            this.nights += nights;
            this.Booked = true;
        }
        JOptionPane.showMessageDialog(null, "Reservation complete!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
