import java.util.ArrayList;


/**
 * The Room class represents a room in a hotel, which can be reserved on certain dates and has a room number and
 * a price based on the hotel it is in.
 */


public class Room {
    protected int roomNumber;
    protected double price;
    protected Hotel hotel;
    protected final ArrayList<Reservation> reservations = new ArrayList<>();
    protected final ArrayList<Date> dates = new ArrayList<>();
    protected String roomType;


    /**
     * Creates a new Room given a specified room number and hotel to be added to.
     *
     * @param roomNumber The room number.
     * @param hotel      The hotel it will be added to.
     */


    public Room(int roomNumber, Hotel hotel) {
        this.roomNumber = roomNumber;
        this.price = hotel.getPrice();
        this.hotel = hotel;
        this.roomType = "Standard";
        initializeDates();
    }

    protected void initializeDates() {
        for (int i = 0; i < 31; i++) {
            dates.add(new Date(i + 1, price));
        }
    }

    /**
     * Sets the price of the room.
     *
     * @param price The new price of the room.
     */


    public void setPrice(double price){
        this.price = price;
        for(Date date: dates){
            date.setPrice(price);
        }
    }


    /**
     * Gets the price of the room.
     *
     * @return The price of the room.
     */


    public int getRoomNumber(){
        return roomNumber;
    }


    /**
     * Gets the price of the room.
     *
     * @return The price of the room.
     */


    public double getPrice(){
        return price;
    }


    /**
     * Gets the dates on which the room is reserved.
     *
     * @return An ArrayList of the dates the room is reserved.
     */

    public ArrayList<Integer> getAvailableDates(){
        ArrayList<Integer> availableDates = new ArrayList<>();
        for(Date date: dates){
            if (date.getAvailability()){
                availableDates.add(date.getDate());
            }
        }

        return availableDates;
    }


    public ArrayList<Integer> getReservedDates(){
        ArrayList<Integer> reservedDates = new ArrayList<>();
        for(Date date: dates){
            if (!date.getAvailability()){
                reservedDates.add(date.getDate());
            }
        }

        return reservedDates;
    }


    /**
     * Adds a reservation to the room, marking the dates specified by the reservation as such.
     *
     * @param reservation The reservation to be added.
     */


    public void addReservation(Reservation reservation){
        reservations.add(reservation);

        for(int i = reservation.getCheckInDate().getDate(); i <= reservation.getCheckOutDate().getDate(); i++){
            dates.get(i - 1).setAvailability(false);
        }
    }


    /**
     * Checks if the room is reserved during the specified check-in and check-out dates.
     *
     * @param checkInDate  The check-in date.
     * @param checkOutDate The check-out date.
     * @return true if the room is reserved for any date within the specified range, otherwise false.
     */


    public boolean isReserved(int checkInDate, int checkOutDate){
        boolean isReserved = false;
        for (int k = checkInDate; k < checkOutDate; k++){
                if (!dates.get(k).getAvailability()){
                    isReserved = true;
                    break;
                }
        }

        return isReserved;
    }


    /**
     * Removes a reservation from the room, marking dates as available again.
     *
     * @param reservation The reservation to be removed.
     */


    public void removeReservation(Reservation reservation){
        reservations.add(reservation);

        for(int i = reservation.getCheckInDate().getDate(); i <= reservation.getCheckOutDate().getDate(); i++){
            dates.get(i).setAvailability(true);
        }

        reservations.remove(reservation);
    }


    /**
     * Gets the number of active reservations for the room.
     *
     * @return The number of reservations.
     */


    public int getNumOfReservations() {
        return reservations.size();
    }


    /**
     * Gets the total earnings of the room in a month based on its reservations.
     *
     * @return The total earnings the room makes in a month.
     */


    public double getRoomEarnings(){
        double monthlyEarnings = 0;


        for (Reservation reservation : reservations) {
            monthlyEarnings += reservation.getTotalPrice();
        }


        return monthlyEarnings;
    }


    /**
     * Displays the room's information.
     */


    public void viewRoom(){
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Room Type: " + roomType);
        System.out.println("Room Price: " + price);
        System.out.println("Days room is available:");
        System.out.println(getAvailableDates());
        System.out.println("Days room is reserved:");
        System.out.println(getReservedDates());
        System.out.println(" ");
    }


    /**
     * Gets the hotel the room is in.
     *
     * @return The Hotel object the room is in.
     */

    public Hotel getHotel(){
        return hotel;
    }

    public String getRoomType(){
        return roomType;
    }

    public Date getDate(int date){
        return dates.get(date - 1);
    }
}
