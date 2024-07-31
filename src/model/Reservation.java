package model;

import java.util.ArrayList;

/**
 * The Reservation class represents a booking made by a guest for a selected room in a hotel.
 */


public class Reservation {
    private final String guestName;
    private final Room room;
    private final Hotel hotel;
    private final String discount;
    private final int checkInDate;
    private final int checkOutDate;


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
        this.discount = "N/A";
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    /**
     * Creates a new reservation given the guest's name, room number, check-in date, check-out date, and discount code.
     *
     * @param guestName    The name of the guest.
     * @param room         The room being reserved.
     * @param checkInDate  The check-in date.
     * @param checkOutDate The check-out date.
     * @param discount     The discount code used to book the reservation.
     */

    public Reservation(String guestName, Room room, int checkInDate, int checkOutDate, String discount){
        this.guestName = guestName;
        this.room = room;
        this.hotel = room.getHotel();
        this.discount = discount;
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
     * Gets the total price of the reservation.
     *
     * @return The total price of the reservation.
     */

    public double getTotalPrice(){
        double price = 0;
        for(int i = 0; i < getReservedDates().size(); i++){
            price += getReservedDates().get(i).getPrice();
        }
        return switch (discount) {
            case "I_WORK_HERE" -> price * 0.9;
            case "STAY4_GET1" -> price - getReservedDates().getFirst().getPrice();
            case "PAYDAY" -> price * 0.93;
            default -> price;
        };
    }

    /**
     * Gets the discount code of the reservation.
     *
     * @return The discount code of the reservation.
     */

    public String getDiscountCode(){
        return discount;
    }

    /**
     * Gets the list of dates being reserved by this reservation.
     *
     * @return The list of dates being reserved.
     */

    public ArrayList<Date> getReservedDates(){
        ArrayList<Date> dates = new ArrayList<>();
        for(int i = checkInDate; i < checkOutDate; i++){
            dates.add(new Date(i, (room.getPrice() * hotel.getPremiums()[i - 1])));
        }
        return dates;
    }
}
