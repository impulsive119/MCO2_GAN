package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * The Hotel class represents a real life hotel object and has methods to manage rooms, book reservations, and adjust pricing.
 */
public class Hotel {
    private String name;
    private final Floor firstFloor;
    private final Floor secondFloor;
    private final Floor thirdFloor;
    private final Floor fourthFloor;
    private final Floor fifthFloor;
    private double price;
    private final double[] premiums = new double[31];
    private final HotelReservationSystem HRS;

    /**
     * Creates a new hotel using a given name and initializes it starting with one standard room.
     *
     * @param name The name of the hotel.
     * @param HRS The HotelReservationSystem instance managing this hotel.
     */
    public Hotel(String name, HotelReservationSystem HRS){
        this.name = name;
        this.price = 1299.00;
        firstFloor = new Floor(1, this);
        secondFloor = new Floor(2, this);
        thirdFloor = new Floor(3, this);
        fourthFloor = new Floor(4, this);
        fifthFloor = new Floor(5, this);
        for(int i = 0; i < 31; i++){
            premiums[i] = 1;
        }
        addRooms(1, 1);
        this.HRS = HRS;
    }

    /**
     * Gets the premium rates for each date of the month.
     *
     * @return An array of premium rates for each day of the month.
     */
    public double[] getPremiums(){
        return premiums;
    }

    /**
     * Gets the number of rooms in the hotel.
     *
     * @return The number of rooms.
     */
    public int getNumberOfRooms(){
        return getRooms().size();
    }

    /**
     * Gets the name of the hotel.
     *
     * @return The name of the hotel.
     */
    public String getName(){
        return name;
    }

    /**
     * Gets the base price of hte hotel.
     *
     * @return The base price of the hotel.
     */
    public double getPrice(){
        return price;
    }

    /**
     * Sets a new name for the hotel.
     *
     * @param name The new name of the hotel.
     * @return True if the name is unique and not used by an existing hotel, returns false otherwise.
     */
    public boolean setName(String name){
        if(HRS.isHotelNameAvailable(name)){
            this.name = name;
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Adds a premium rate for a given date of the month.
     *
     * @param date The date (1 to 31) for which to set the premium.
     * @param premium The premium rate for the specified date.
     */
    public void addPremiumToDate(int date, double premium){
        premiums[date - 1] = premium;
    }

    /**
     * Adds a given number of rooms of a given type to the hotel.
     *
     * @param numOfRooms The number of rooms.
     * @param roomType The type of rooms to add (Standard, Deluxe, or Executive).
     * @return The list of the added room numbers.
     */
    public ArrayList<Integer> addRooms(int numOfRooms, int roomType) {
        ArrayList<Room> addedRooms = new ArrayList<>();
        ArrayList<Integer> roomNumbers = new ArrayList<>();

        for (int j = 0; j < numOfRooms; j++) {
            if (firstFloor.getNumOfRooms() < 10) {
                addedRooms.add(firstFloor.addRoom(roomType));
            } else if (firstFloor.getNumOfRooms() == 10 && secondFloor.getNumOfRooms() < 10) {
                addedRooms.add(secondFloor.addRoom(roomType));
            } else if (secondFloor.getNumOfRooms() == 10 && thirdFloor.getNumOfRooms() < 10) {
                addedRooms.add(thirdFloor.addRoom(roomType));
            } else if (thirdFloor.getNumOfRooms() == 10 && fourthFloor.getNumOfRooms() < 10) {
                addedRooms.add(fourthFloor.addRoom(roomType));
            } else if (fourthFloor.getNumOfRooms() == 10 && fifthFloor.getNumOfRooms() < 10) {
                addedRooms.add(fifthFloor.addRoom(roomType));
            }
        }

        for (Room room : addedRooms) {
            roomNumbers.add(room.getRoomNumber());
        }

        Collections.sort(roomNumbers);

        return roomNumbers;
    }

    /**
     * Removes a given room from the hotel.
     *
     * @param room The room to be removed.
     */
    public void removeRoom(Room room){
        int roomNumber = room.getRoomNumber();
        int floorNumber = roomNumber / 100;
        int roomDigits = roomNumber % 100;

        switch (floorNumber){
            case 1:
                firstFloor.removeRoom(roomDigits - 1);
                break;
            case 2:
                secondFloor.removeRoom(roomDigits - 1);
                break;
            case 3:
                thirdFloor.removeRoom(roomDigits - 1);
                break;
            case 4:
                fourthFloor.removeRoom(roomDigits - 1);
                break;
            case 5:
                fifthFloor.removeRoom(roomDigits - 1);
                break;
        }
    }

    /**
     * Sets a new base price for the hotel.
     *
     * @param price The new base price.
     */
    public void setPrice(double price){
        this.price = price;
    }

    /**
     * Gets the list of available room numbers for a given date.
     *
     * @param date The date for which availability is checked.
     * @return The list of available room numbers.
     */
    public ArrayList<Integer> getAvailableRoomsOnDate(int date){
        ArrayList<Integer> availableRooms = new ArrayList<>();

        for(Room room: getRooms()){
            if(!room.isReserved(date, date + 1)){
                availableRooms.add(room.getRoomNumber());
            }
        }

        return availableRooms;
    }

    /**
     * Gets the list of reserved room numbers for a given date.
     *
     * @param date The date for which reservations are checked.
     * @return The list of reserved room numbers.
     */
    public ArrayList<Integer> getReservedRoomsOnDate(int date){
        ArrayList<Integer> reservedRooms = new ArrayList<>();

        for(Room room: getRooms()){
            if(room.isReserved(date, date + 1)){
                reservedRooms.add(room.getRoomNumber());
            }
        }

        return reservedRooms;
    }

    /**
     * Checks if adding a given number of rooms would exceed the maximum capacity of the hotel.
     *
     * @param numOfRooms The number of rooms to add.
     * @return True if the total number of rooms would be less than or equal to 50, false otherwise.
     */
    public boolean isNumberOfRoomsValid(int numOfRooms){
        return numOfRooms >= 1 && numOfRooms + getNumberOfRooms() <= 50;
    }

    /**
     * Removes a reservation from the hotel.
     *
     * @param reservation The reservation to be removed.
     */
    public void removeReservation(Reservation reservation){
        reservation.getRoom().removeReservation(reservation);
    }

    /**
     * Gets a room using its number.
     *
     * @param roomNumber The room number.
     * @return The Room object corresponding to the room number, or null if not found.
     */
    public Room getRoom(int roomNumber) {
        try {
            for (Room room: getRooms()){
                if(room.getRoomNumber() == roomNumber){
                    return room;
                }
            }
            return null;

        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    /**
     * Gets a reservation using its number.
     *
     * @param reservationNumber The reservation number.
     * @return The Reservation object corresponding to the reservation number, or null if not found.
     */
    public Reservation getReservation(int reservationNumber){
        try {
            if (reservationNumber > 0 && reservationNumber <= getReservations().size()) {
                return getReservations().get(reservationNumber - 1);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException | NoSuchElementException e) {
            return null;
        }
        return null;
    }

    /**
     * Gets the monthly earnings from all rooms in the hotel.
     *
     * @return The monthly earnings of the hotel.
     */
    public double getMonthlyEarnings(){
        double earnings = 0;

        for(Room room: getRooms()){
            earnings += room.getRoomEarnings();
        }

        return earnings;
    }

    /**
     * Gets the number of active reservations in the hotel.
     *
     * @return The number of active reservations.
     */
    public int getNumberOfReservations(){
        return getReservations().size();
    }

    /**
     * Gets the number of standard rooms in the hotel.
     *
     * @return The number of standard rooms.
     */
    public int getNumberOfStandardRooms(){
        int numOfRooms = 0;
        for(Room room: getRooms()){
            if(room.getRoomType().equals("Standard")){
                numOfRooms++;
            }
        }
        return numOfRooms;
    }

    /**
     * Gets the number of deluxe rooms in the hotel.
     *
     * @return The number of deluxe rooms.
     */

    public int getNumberOfDeluxeRooms(){
        int numOfRooms = 0;
        for(Room room: getRooms()){
            if(room.getRoomType().equals("Deluxe")){
                numOfRooms++;
            }
        }
        return numOfRooms;
    }

    /**
     * Gets the number of executive rooms in the hotel.
     *
     * @return The number of executive rooms.
     */
    public int getNumberOfExecutiveRooms(){
        int numOfRooms = 0;
        for(Room room: getRooms()){
            if(room.getRoomType().equals("Executive")){
                numOfRooms++;
            }
        }
        return numOfRooms;
    }

    /**
     * Gets the sorted list of all room numbers in the hotel.
     *
     * @return The list of room numbers.
     */
    public ArrayList<Integer> getRoomNumbers(){
        ArrayList<Integer> roomNumbers = new ArrayList<>();
        for(Room room: getRooms()){
            roomNumbers.add(room.getRoomNumber());
        }
        Collections.sort(roomNumbers);
        return roomNumbers;
    }

    /**
     * Gets the list of the reservations in the hotel.
     *
     * @return The list of reservations.
     */
    public ArrayList<Reservation> getReservations(){
        ArrayList<Reservation> reservations = new ArrayList<>();
        for(Room room: getRooms()){
            reservations.addAll(room.getReservations());
        }
        return reservations;
    }

    /**
     * Gets the list of all rooms in the hotel.
     *
     * @return The list of rooms.
     */
    public ArrayList<Room> getRooms(){
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.addAll(firstFloor.getRooms());
        rooms.addAll(secondFloor.getRooms());
        rooms.addAll(thirdFloor.getRooms());
        rooms.addAll(fourthFloor.getRooms());
        rooms.addAll(fifthFloor.getRooms());
        return rooms;
    }

    /**
     * Gets a reservation using the guest's name.
     *
     * @param name The name of the guest.
     * @return The Reservation made under the guest's name, or null if not found.
     */
    public Reservation getReservation(String name){
        ArrayList<Room> rooms = getRooms();
        for(Room room: rooms){
            for(Reservation reservation: room.getReservations()){
                if(reservation.getGuestName().equals(name)){
                    return reservation;
                }
            }
        }
        return null;
    }
}