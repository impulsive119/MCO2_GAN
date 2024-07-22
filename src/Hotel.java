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
        rooms.add(firstFloor.addRoom());
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
        System.out.println("New Name: " + name);
        System.out.println(" ");
    }



    public void addPremiumToDate(int date, double premium){
        for (Room room: rooms){
            room.getDate(date).setPrice(room.getPrice() * premium);
        }
    }

    public void addRooms(int numOfRooms, int roomType) {
        ArrayList<Room> addedRooms = new ArrayList<>();
        ArrayList<Integer> roomNumbers = new ArrayList<>();

        for (int j = 0; j < numOfRooms; j++) {
            switch (roomType) {
                case 1:
                    if (firstFloor.getNumOfRooms() < 10) {
                        addedRooms.add(firstFloor.addRoom());
                    } else if (firstFloor.getNumOfRooms() == 10 && secondFloor.getNumOfRooms() < 10) {
                        addedRooms.add(secondFloor.addRoom());
                    } else if (firstFloor.getNumOfRooms() == 10 && secondFloor.getNumOfRooms() == 10) {
                        addedRooms.add(thirdFloor.addRoom());
                    }
                    break;
                case 2:
                    addedRooms.add(fourthFloor.addRoom());
                    break;
                case 3:
                    addedRooms.add(fifthFloor.addRoom());
                    break;
            }
        }

        for (Room room : addedRooms) {
            roomNumbers.add(room.getRoomNumber());
        }

        rooms.addAll(addedRooms);

        Collections.sort(roomNumbers);

        String plural = (addedRooms.size() > 1) ? "s" : "";
        System.out.println("Room" + plural + " " + roomNumbers + " Added");
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
        System.out.println("Room " + roomNumber + " Removed");

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
        System.out.println("Price Changed To: " + this.price);
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
     * Prints the room numbers of all the rooms in the hotel.
     */


    public void printRoomNumbers(){
        ArrayList<Integer> roomNumbers = new ArrayList<>();
        for (Room room : rooms) {
            roomNumbers.add(room.getRoomNumber());
        }

        Collections.sort(roomNumbers);
        System.out.println(roomNumbers);
    }


    /**
     * Prints the names of all guests with reservations in the hotel.
     */


    public void printReservations(){
        for(int i = 0; i < reservations.size(); i++){
            System.out.println((i + 1) + ": " + reservations.get(i).getGuestName());
        }
    }


    /**
     * Removes a selected reservation from the hotel.
     *
     * @param reservation The reservation to be removed.
     */


    public void removeReservation(Reservation reservation){
        reservations.remove(reservation);
        reservation.getRoom().removeReservation(reservation);
        System.out.println("[Reservation Removed]");
        System.out.println(" ");
    }


    /**
     * Adds a reservation for a selected room given the guest's name and dates of stay.
     *
     * @param room        The room to be reserved.
     * @param guestName   The name of the guest.
     * @param checkInDate The check-in date for the reservation.
     * @param checkOutDate The check-out date for the reservation.
     */


    public void addReservation(Room room, String guestName, int checkInDate, int checkOutDate, int discountType) {
        Reservation newReservation = switch (discountType) {
            case 1 -> new Reservation(guestName, room, checkInDate, checkOutDate, new IWorkHereDiscount());
            case 2 ->
                    new Reservation(guestName, room, checkInDate, checkOutDate, new Stay4Get1Discount());
            case 3 -> new Reservation(guestName, room, checkInDate, checkOutDate, new PaydayDiscount());
            default -> new Reservation(guestName, room, checkInDate, checkOutDate);
        };
        reservations.add(newReservation);
        room.addReservation(newReservation);
        System.out.println("[Reservation Added]");
        System.out.println(" ");
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
            System.out.println("[Invalid input]");
            System.out.println(" ");
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
        return firstFloor.getNumOfRooms() + secondFloor.getNumOfRooms() + thirdFloor.getNumOfRooms();
    }

    public int getNumberOfDeluxeRooms(){
        return fourthFloor.getNumOfRooms();
    }

    public int getNumberOfExecutiveRooms(){
        return fifthFloor.getNumOfRooms();
    }

    public ArrayList<Room> getRooms(){
        return rooms;
    }

    public ArrayList<Reservation> getReservations(){
        return reservations;
    }
}
