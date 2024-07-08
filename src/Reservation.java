/**
 * The Reservation class represents a booking made by a guest for a selected room in a hotel.
 */


public class Reservation {
    private final String guestName;
    private final Room room;
    private final Hotel hotel;
    private final int checkInDate;
    private final int checkOutDate;
    private final double pricePerDay;
    private final double totalPrice;


    /**
     * Creates a new reservation given the guest's name, room number, check-in date, and check-out date.
     *
     * @param guestName    The name of the guest.
     * @param room         The room being reserved.
     * @param checkInDate  The check-in date.
     * @param checkOutDate The check-out date.
     */


    public Reservation(String guestName, Room room, int checkInDate, int checkOutDate){
        this.guestName = guestName;
        this.room = room;
        this.hotel = room.getHotel();
        this.pricePerDay = room.getPrice();
        this.totalPrice = pricePerDay * (checkOutDate - checkInDate + 1);
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }


    /**
     * Gets the name of the guest that made the reservation.
     *
     * @return The guest's name.
     */


    public String getGuestName(){
        return guestName;
    }


    /**
     * Gets the check-in date.
     *
     * @return The check-in date.
     */


    public int getCheckInDate() {
        return checkInDate;
    }


    /**
     * Gets the check-out date.
     *
     * @return The check-out date.
     */


    public int getCheckOutDate() {
        return checkOutDate;
    }


    /**
     * Gets the room that is being reserved.
     *
     * @return The Room object that is reserved.
     */


    public Room getRoom(){
        return room;
    }


    /**
     * Displays the reservation's details
     */


    public void viewReservation(){
        System.out.println("Guest Name: " + guestName);
        System.out.println("Hotel: " + hotel.getName());
        System.out.println("Room Number: " + room.getRoomNumber());
        System.out.println("Check-in Date: " + checkInDate);
        System.out.println("Check-out Date: " + checkOutDate);
        System.out.println("Price per Day: " + pricePerDay);
        System.out.println("Total Price: " + totalPrice);
    }


    /**
     * Gets the total price of the reservation.
     *
     * @return The total price of the reservation.
     */


    public double getTotalPrice(){
        return totalPrice;
    }
}
