package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;


/**
 * The model.Hotel class represents a hotel and allows the user to manage rooms, book reservations, and edit pricing.
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
     * Creates a new hotel with the inputted name and initializes it with a starting room.
     *
     * @param name The name of the new hotel.
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
     * Gets the number of rooms in the hotel.
     *
     * @return The number of rooms in the hotel.
     */

    public double[] getPremiums(){
        return premiums;
    }


    public int getNumberOfRooms(){
        return getRooms().size();
    }


    /**
     * Gets the name of the hotel.
     *
     * @return The number of rooms in the hotel.
     */


    public String getName(){
        return name;
    }


    /**
     * Gets the price per day of the hotel.
     *
     * @return The price per day.
     */


    public double getPrice(){
        return price;
    }


    /**
     * Changes the name of the hotel.
     *
     * @param name The new name of the hotel.
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



    public void addPremiumToDate(int date, double premium){
        premiums[date - 1] = premium;
    }

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
            }else if (thirdFloor.getNumOfRooms() == 10 && fourthFloor.getNumOfRooms() < 10) {
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
     * Removes a selected room from the hotel.
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
     * Changes the price per day of the hotel.
     *
     * @param price The new price per day.
     */


    public void changePrice(double price){
        this.price = price;
    }


    /**
     * Displays the availability of rooms on a specific date.
     *
     * @param date The date for which availability is checked.
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
     * Prints the names of all guests with reservations in the hotel.
     */

    public void removeReservation(Reservation reservation){
        reservation.getRoom().removeReservation(reservation);
    }


    /**
     * Allows the user to select a room from the current list of rooms in the hotel.
     *
     * @return The selected model.Room object, which is null if the selected room is invalid.
     */


    public Room selectRoom(int roomNumber) {
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
     * Allows the user to select a reservation from the current list of reservations in the hotel.
     *
     * @return The selected model.Reservation object, which is null if the reservation is invalid.
     */


    public Reservation selectReservation(int reservationNumber){
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
     * Gets the total monthly earnings from all the rooms in the hotel.
     *
     * @return The total monthly earnings.
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
     * @return The number of reservations.
     */


    public int getNumberOfReservations(){
        return getReservations().size();
    }

    public int getNumberOfStandardRooms(){
        int numOfRooms = 0;
        for(Room room: getRooms()){
            if(room.getRoomType().equals("Standard")){
                numOfRooms++;
            }
        }
        return numOfRooms;
    }

    public int getNumberOfDeluxeRooms(){
        int numOfRooms = 0;
        for(Room room: getRooms()){
            if(room.getRoomType().equals("Deluxe")){
                numOfRooms++;
            }
        }
        return numOfRooms;
    }

    public int getNumberOfExecutiveRooms(){
        int numOfRooms = 0;
        for(Room room: getRooms()){
            if(room.getRoomType().equals("Executive")){
                numOfRooms++;
            }
        }
        return numOfRooms;
    }

    public ArrayList<Integer> getRoomNumbers(){
        ArrayList<Integer> roomNumbers = new ArrayList<>();
        for(Room room: getRooms()){
            roomNumbers.add(room.getRoomNumber());
        }
        Collections.sort(roomNumbers);
        return roomNumbers;
    }

    public ArrayList<Reservation> getReservations(){
        ArrayList<Reservation> reservations = new ArrayList<>();
        for(Room room: getRooms()){
            reservations.addAll(room.getReservations());
        }
        return reservations;
    }

    public ArrayList<Room> getRooms(){
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.addAll(firstFloor.getRooms());
        rooms.addAll(secondFloor.getRooms());
        rooms.addAll(thirdFloor.getRooms());
        rooms.addAll(fourthFloor.getRooms());
        rooms.addAll(fifthFloor.getRooms());
        return rooms;
    }

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
