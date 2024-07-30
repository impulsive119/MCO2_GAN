package model;

import java.util.ArrayList;


/**
 * The model.Room class represents a room in a hotel, which can be reserved on certain dates and has a room number and
 * a price based on the hotel it is in.
 */

public class Room {
    private final int roomNumber;
    protected Floor floor;
    private final ArrayList<Reservation> reservations = new ArrayList<>();
    protected String roomType;


    /**
     * Creates a new model.Room given a specified room number and hotel to be added to.
     *
     * @param roomNumber The room number.
     * @param floor      The hotel it will be added to.
     */


    public Room(int roomNumber, Floor floor) {
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.roomType = "Standard";
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
        return floor.getHotel().getPrice();
    }


    /**
     * Gets the dates on which the room is reserved.
     *
     * @return An ArrayList of the dates the room is reserved.
     */

    public ArrayList<Integer> getReservedDates(){
        ArrayList<Integer> reservedDates = new ArrayList<>();
        for(Reservation reservation: reservations){
           for(Date date: reservation.getReservedDates()){
               reservedDates.add(date.getDate());
           }
        }

        return reservedDates;
    }

    public ArrayList<Integer> getAvailableDates(){
        ArrayList<Integer> reservedDates = getReservedDates();
        ArrayList<Integer> availableDates = new ArrayList<>();
        for(int i = 1; i <= 31; i++){
            if(!reservedDates.contains(i)){
                availableDates.add(i);
            }
        }
        return availableDates;
    }

    public int addReservation(String guestName, int checkInDate, int checkOutDate, String discountType) {
        Reservation newReservation = null;
        int isDiscountValid = 1;
        switch (discountType){
            case "I_WORK_HERE":
                newReservation = new Reservation(guestName, this,checkInDate, checkOutDate, discountType);
                break;
            case "STAY4_GET1":
                if(checkOutDate - checkInDate < 5){
                    isDiscountValid = 2;
                }else{
                    newReservation = new Reservation(guestName, this,checkInDate, checkOutDate, discountType);
                }
                break;
            case "PAYDAY":
                if((checkInDate <= 15 && checkOutDate > 15) || (checkInDate <= 30 && checkOutDate > 30)){
                    newReservation = new Reservation(guestName, this,checkInDate, checkOutDate, discountType);
                }else{
                    isDiscountValid = 3;
                }
                break;
            default:
                newReservation = new Reservation(guestName, this,checkInDate, checkOutDate);
        }
        if(isDiscountValid == 1){
            reservations.add(newReservation);
        }
        return isDiscountValid;
    }


    /**
     * Checks if the room is reserved during the specified check-in and check-out dates.
     *
     * @param checkInDate  The check-in date.
     * @param checkOutDate The check-out date.
     * @return true if the room is reserved for any date within the specified range, otherwise false.
     */


    public boolean isReserved(int checkInDate, int checkOutDate){
        ArrayList<Integer> reservedDates = getReservedDates();

        for(int i = checkInDate; i < checkOutDate; i++){
            if (reservedDates.contains(i)) {
                return true;
            }
        }

        return false;
    }


    /**
     * Removes a reservation from the room, marking dates as available again.
     *
     * @param reservation The reservation to be removed.
     */


    public void removeReservation(Reservation reservation){
        reservations.remove(reservation);
    }

    public ArrayList< Reservation> getReservations(){
        return reservations;
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
     * Gets the hotel the room is in.
     *
     * @return The model.Hotel object the room is in.
     */

    public Hotel getHotel(){
        return floor.getHotel();
    }

    public String getRoomType(){
        return roomType;
    }
}
