import java.util.*;
class ReservationSystem
{
    private Stack<Reservation> reservations;
    private Map<String, Integer> tableAvailability;

    public ReservationSystem()
    {
        reservations = new Stack<>();
        tableAvailability = new HashMap<>();
        tableAvailability.put("Table1", 4);
        tableAvailability.put("Table2", 6);
        tableAvailability.put("Table3", 2);
    }

    public void makeReservation(int reservationNumber, String customerName, int numberOfPeople, String date, String time)
    {
        for (Map.Entry<String, Integer> entry : tableAvailability.entrySet())
        {
            if (entry.getValue() >= numberOfPeople)
            {
                Reservation reservation = new Reservation(reservationNumber, customerName, numberOfPeople, date, time);
                reservations.push(reservation);
                tableAvailability.put(entry.getKey(), entry.getValue() - numberOfPeople);
                System.out.println("Reservation made successfully. Table: " + entry.getKey());
                return;
            }
        }
        System.out.println("No available tables for the requested number of people.");
    }


    public void cancelReservation(int reservationNumber)
    {
        for (int i = 0; i < reservations.size(); i++)
        {
            if (reservations.get(i).getReservationNumber() == reservationNumber)
            {
                Reservation canceledReservation = reservations.remove(i);
                String table = findTableForReservation(canceledReservation);
                if (table != null)
                {
                    tableAvailability.put(table, tableAvailability.get(table) + canceledReservation.getNumberOfPeople());
                }
                System.out.println("Reservation cancelled successfully.");
                return;
            }
        }
        System.out.println("Reservation not found.");
    }


    private String findTableForReservation(Reservation reservation)
    {
        for (Map.Entry<String, Integer> entry : tableAvailability.entrySet())
        {
            if (entry.getValue() >= reservation.getNumberOfPeople())
            {
                return entry.getKey();
            }
        }
        return null;
    }

    public void displayReservations()
    {
        System.out.println("Current Reservations:");
        for (Reservation reservation : reservations)
        {
            System.out.println(reservation);
        }
    }
}
