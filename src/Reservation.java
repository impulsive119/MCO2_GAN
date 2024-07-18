import java.util.ArrayList;

/**
 * The Reservation class represents a booking made by a guest for a selected room in a hotel.
 */


public class Reservation {
    private final String guestName;
    private final Room room;
    private final Hotel hotel;
    private final ArrayList<Date> reservedDates = new ArrayList<>();
    private double totalPrice;
    private final String roomType;
    private final Discount discount;


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
        for(int i = checkInDate; i <= checkOutDate; i++){
            this.reservedDates.add(room.getDate(i));
            if(i != checkOutDate){
                this.totalPrice += reservedDates.getLast().getPrice();
            }
        }
        reservedDates.getLast().setIsCheckoutDate(true);
        this.roomType = room.getRoomType();
        this.discount = null;
    }

    public Reservation(String guestName, Room room, int checkInDate, int checkOutDate, Discount discount){
        this.guestName = guestName;
        this.room = room;
        this.hotel = room.getHotel();
        for(int i = checkInDate; i < checkOutDate; i++){
            this.reservedDates.add(room.getDate(i));
            this.totalPrice += reservedDates.getLast().getPrice();
        }
        reservedDates.getLast().setIsCheckoutDate(true);
        this.roomType = room.getRoomType();
        this.discount = discount;
        if(discount.getDiscountCode().equals("STAY4_GET1")){
            totalPrice = Stay4Get1Discount.getDiscountAmount(reservedDates.getFirst().getPrice(), totalPrice);
        }
        else{
            totalPrice *= (1 - discount.getDiscountPercentage());
        }

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


    public Date getCheckInDate() {
        return reservedDates.getFirst();
    }


    /**
     * Gets the check-out date.
     *
     * @return The check-out date.
     */


    public Date getCheckOutDate() {
        return reservedDates.getLast();
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
        System.out.println("Room Type: " + roomType);
        System.out.println("Check-in Date: " + reservedDates.getFirst().getDate());
        System.out.println("Check-out Date: " + reservedDates.getLast().getDate());
        System.out.println("Total Price: " + totalPrice);
        System.out.println("Discount Code: " + getDiscountCode());
    }


    /**
     * Gets the total price of the reservation.
     *
     * @return The total price of the reservation.
     */


    public double getTotalPrice(){
        return totalPrice;
    }

    private String getDiscountCode(){
        if(discount == null){
            return "N/A";
        }
        else{
            return discount.getDiscountCode();
        }
    }
}
