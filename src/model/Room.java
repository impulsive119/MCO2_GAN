package model;

import java.util.ArrayList;


/**
 * The Room class represents a room in a hotel, which can be reserved on certain dates and has a room number and
 * a price based on the hotel it is in.
 */

public class Room {
    private final int roomNumber;
    protected Floor floor;
    private final ArrayList<Reservation> reservations = new ArrayList<>();
    protected String roomType;


    /**
     * Creates a new Room given a specified room number and floor to be added to.
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
     * Gets the room number.
     *
     * @return The room number.
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
     * @return The list of dates in which the room is reserved.
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

    /**
     * Gets the dates on which the room is reserved.
     *
     * @return The list of dates in which the room is available.
     */

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

    /**
     * Books a reservation for the room given the guest's name, check-in date, check-out date, and discount type, if applicable.
     *
     * @param guestName  The name of the guest.
     * @param checkInDate The check-in date.
     * @param checkOutDate  The check-out date.
     * @param discountType The discount type used for the reservation.
     * @return 1 if the reservation was added successfully, 2 if the STAY4_GET1 discount was used incorrectly, and 3 if the PAYDAY discount was used incorrectly.
     */

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
     * Checks if the room is reserved during the given check-in and check-out dates.
     *
     * @param checkInDate  The check-in date.
     * @param checkOutDate The check-out date.
     * @return true if the room is reserved for any date within the given range, false otherwise.
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
     * Removes a reservation from the room, marking the dates it reserved as available again.
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
     * @return The total earnings of the room.
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
     * @return The Hotel the room is in.
     */

    public Hotel getHotel(){
        return floor.getHotel();
    }

    /**
     * Gets the room's type.
     *
     * @return The roomType of the room.
     */

    public String getRoomType(){
        return roomType;
    }
}
