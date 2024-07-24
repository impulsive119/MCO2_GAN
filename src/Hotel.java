import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;


/**
 * The Hotel class represents a hotel and allows the user to manage rooms, book reservations, and edit pricing.
 */


public class Hotel {
    private String name;
    private final ArrayList<Room> rooms = new ArrayList<>();
    private final Floor firstFloor;
    private final Floor secondFloor;
    private final Floor thirdFloor;
    private final Floor fourthFloor;
    private final Floor fifthFloor;
    private final ArrayList<Reservation> reservations = new ArrayList<>();
    private double price;


    /**
     * Creates a new hotel with the inputted name and initializes it with a starting room.
     *
     * @param name The name of the new hotel.
     */


    public Hotel(String name){
        this.name = name;
        this.price = 1299.00;
        firstFloor = new Floor(1, this);
        secondFloor = new Floor(2, this);
        thirdFloor = new Floor(3, this);
        fourthFloor = new Floor(4, this);
        fifthFloor = new Floor(5, this);
        rooms.add(firstFloor.addRoom(1));
    }


    /**
     * Gets the number of rooms in the hotel.
     *
     * @return The number of rooms in the hotel.
     */


    public int getNumberOfRooms(){
        int numberOfRooms = 0;
        for (Room room: rooms){
            if (room != null){
                numberOfRooms++;
            }
        }


        return numberOfRooms;
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


    public void setName(String name){
        this.name = name;
    }



    public void addPremiumToDate(int date, double premium){
        for (Room room: rooms){
            room.getDate(date).setPrice(room.getPrice() * premium);
        }
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

        rooms.addAll(addedRooms);

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

        rooms.remove(room);

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
        for (Room room: rooms){
            room.setPrice(price);
        }
    }


    /**
     * Displays the availability of rooms on a specific date.
     *
     * @param date The date for which availability is checked.
     */


    public ArrayList<Integer> getAvailableRoomsOnDate(int date){
        ArrayList<Integer> availableRooms = new ArrayList<>();


        for(Room room: rooms){
            if (room.getDate(date).getAvailability()){
                availableRooms.add(room.getRoomNumber());
            }
        }

        return availableRooms;
    }

    public ArrayList<Integer> getReservedRoomsOnDate(int date){
        ArrayList<Integer> reservedRooms = new ArrayList<>();


        for(Room room: rooms){
            if (!room.getDate(date).getAvailability()){
                reservedRooms.add(room.getRoomNumber());
            }
        }

        return reservedRooms;
    }


    /**
     * Prints the names of all guests with reservations in the hotel.
     */

    public void removeReservation(Reservation reservation){
        reservations.remove(reservation);
        reservation.getRoom().removeReservation(reservation);
    }


    /**
     * Adds a reservation for a selected room given the guest's name and dates of stay.
     *
     * @param room        The room to be reserved.
     * @param guestName   The name of the guest.
     * @param checkInDate The check-in date for the reservation.
     * @param checkOutDate The check-out date for the reservation.
     */


    public void addReservation(Room room, String guestName, int checkInDate, int checkOutDate, String discountType) {
        Reservation newReservation = switch (discountType) {
            case "I_WORK_HERE" -> new Reservation(guestName, room, checkInDate, checkOutDate, new IWorkHereDiscount());
            case "STAY4_GET1" ->
                    new Reservation(guestName, room, checkInDate, checkOutDate, new Stay4Get1Discount());
            case "PAYDAY" -> new Reservation(guestName, room, checkInDate, checkOutDate, new PaydayDiscount());
            default -> new Reservation(guestName, room, checkInDate, checkOutDate);
        };
        reservations.add(newReservation);
        room.addReservation(newReservation);
    }

    /**
     * Allows the user to select a room from the current list of rooms in the hotel.
     *
     * @return The selected Room object, which is null if the selected room is invalid.
     */


    public Room selectRoom(int roomNumber) {
        try {
            for (Room room: rooms){
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
     * @return The selected Reservation object, which is null if the reservation is invalid.
     */


    public Reservation selectReservation(int reservationNumber){
        try {
            if (reservationNumber > 0 && reservationNumber <= reservations.size()) {
                return reservations.get(reservationNumber - 1);
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


        for(Room room: rooms){
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
        return reservations.size();
    }

    public int getNumberOfStandardRooms(){
        int numOfRooms = 0;
        for(Room room: rooms){
            if(room.getRoomType().equals("Standard")){
                numOfRooms++;
            }
        }
        return numOfRooms;
    }

    public int getNumberOfDeluxeRooms(){
        int numOfRooms = 0;
        for(Room room: rooms){
            if(room.getRoomType().equals("Deluxe")){
                numOfRooms++;
            }
        }
        return numOfRooms;
    }

    public int getNumberOfExecutiveRooms(){
        int numOfRooms = 0;
        for(Room room: rooms){
            if(room.getRoomType().equals("Executive")){
                numOfRooms++;
            }
        }
        return numOfRooms;
    }

    public ArrayList<Integer> getRoomNumbers(){
        ArrayList<Integer> roomNumbers = new ArrayList<>();
        for(Room room: rooms){
            roomNumbers.add(room.getRoomNumber());
        }
        return roomNumbers;
    }

    public ArrayList<Reservation> getReservations(){
        return reservations;
    }
}
