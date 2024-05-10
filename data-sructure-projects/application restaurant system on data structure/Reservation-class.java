class Reservation {
    private int reservationNumber;
    private String customerName;
    private int numberOfPeople;
    private String date;
    private String time;

    public Reservation(int reservationNumber, String customerName, int numberOfPeople, String date, String time)
    {
        this.reservationNumber = reservationNumber;
        this.customerName = customerName;
        this.numberOfPeople = numberOfPeople;
        this.date = date;
        this.time = time;
    }


    public int getReservationNumber()
    {
        return reservationNumber;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public int getNumberOfPeople()
    {
        return numberOfPeople;
    }

    public String getDate()
    {
        return date;
    }

    public String getTime()
    {
        return time;
    }


    @Override
    public String toString()
    {
        return "Reservation Number: " + reservationNumber + ", Customer: " + customerName + ", Date: " + date + ", Time: " + time + ", Number of People: " + numberOfPeople;
    }
}
