import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;


/**
 * The Hotel class represents a hotel and allows the user to manage rooms, book reservations, and edit pricing.
 */


public class Hotel {
    private String name;
    private final ArrayList<Room> rooms = new ArrayList<>();
    private final ArrayList<Room> firstFloor = new ArrayList<>();
    private final ArrayList<Room> secondFloor = new ArrayList<>();
    private final ArrayList<Room> thirdFloor = new ArrayList<>();
    private final ArrayList<Room> fourthFloor = new ArrayList<>();
    private final ArrayList<Room> fifthFloor = new ArrayList<>();
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
        Room room = new Room(101, this);
        rooms.add(room);
        firstFloor.add(room);
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


    public void changeName(String name){
        this.name = name;
        System.out.println("New Name: " + name);
        System.out.println(" ");
    }


    /**
     * Adds a number of rooms to the hotel.
     *
     * @param numOfRooms The number of rooms to add.
     */


    public void addRooms(int numOfRooms, int roomType){
        ArrayList<Integer> addedRooms = new ArrayList<>();

        for(int j = 0; j < numOfRooms; j++) {
            switch(roomType){
                case 1:
                    if(firstFloor.size() < 10){
                        addRoomToFloor(firstFloor, 1, addedRooms, roomType);
                    } else if (firstFloor.size() == 10 && secondFloor.size() < 10) {
                        addRoomToFloor(secondFloor, 2, addedRooms, roomType);
                    }else if (firstFloor.size() == 10 && secondFloor.size() == 10) {
                        addRoomToFloor(thirdFloor, 3, addedRooms, roomType);
                    }
                    break;
                case 2:
                    addRoomToFloor(fourthFloor, 4, addedRooms, roomType);
                    break;
                case 3:
                    addRoomToFloor(fifthFloor, 5, addedRooms, roomType);
                    break;
            }
        }
        String plural = "";

        if (addedRooms.size() > 1){
            plural = plural.concat("s");
        }

        Collections.sort(addedRooms);
        System.out.println("Room" + plural + " " + addedRooms + " Added");
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
                firstFloor.remove(roomDigits - 1);
                break;
            case 2:
                secondFloor.remove(roomDigits - 1);
                break;
            case 3:
                thirdFloor.remove(roomDigits - 1);
                break;
            case 4:
                fourthFloor.remove(roomDigits - 1);
                break;
            case 5:
                fifthFloor.remove(roomDigits - 1);
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


    public void viewAvailabilityOnDate(int date){
        ArrayList<Room> reservedRooms = new ArrayList<>();
        ArrayList<Room> availableRooms = new ArrayList<>();


        for(Room room: rooms){
            if (room.getDate(date - 1).getAvailability()){
                availableRooms.add(room);
            }
            else{
                reservedRooms.add(room);
            }
        }


        System.out.println("Available rooms:");
        for(Room room: availableRooms){
            System.out.println(room.getRoomNumber());
        }
        System.out.println("Reserved rooms:");
        for(Room room: reservedRooms){
            System.out.println(room.getRoomNumber());
        }


        System.out.println(" ");
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
        room.setPrice(price); // Set the price for the room
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


    public Room selectRoom() {
        try {
            System.out.println("Select Room: ");
            printRoomNumbers();
            System.out.print("Enter your choice: ");
            int chosenRoomNumber = InputHelper.nextInt();


            for (Room room: rooms){
                if(room.getRoomNumber() == chosenRoomNumber){
                    return room;
                }
            }


            System.out.println("[Invalid Room]");
            System.out.println(" ");
            return null;


        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("[Invalid input]");
            System.out.println(" ");
            return null;
        }
    }


    /**
     * Allows the user to select a reservation from the current list of reservations in the hotel.
     *
     * @return The selected Reservation object, which is null if the reservation is invalid.
     */


    public Reservation selectReservation(){
        if (reservations.isEmpty()) {
            System.out.println("[No Active Reservations]");
            System.out.println(" ");
            return null;
        }


        try {
            System.out.println("Select Reservation: ");
            printReservations();
            System.out.print("Enter your choice: ");
            int chosenReservationIndex = InputHelper.nextInt();


            if (chosenReservationIndex > 0 && chosenReservationIndex <= reservations.size()) {
                return reservations.get(chosenReservationIndex - 1);
            } else {
                System.out.println("[Invalid Reservation]");
                System.out.println(" ");
                return null;
            }
        } catch (NumberFormatException | IndexOutOfBoundsException | NoSuchElementException e) {
            System.out.println("[Invalid input]");
            System.out.println(" ");
            return null;
        }
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

    public void addRoomToFloor(ArrayList<Room> floor, int floorNumber, ArrayList<Integer> addedRooms, int roomType){
        int roomNumber;
        ArrayList<Integer> roomNumbers = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            roomNumbers.add(floorNumber * 100 + i + 1);
        }

        boolean missingRoom = false;
        int i = 0;
        while(i < floor.size() && !missingRoom) {
            roomNumber = roomNumbers.get(i);
            if (floor.get(i).getRoomNumber() != roomNumber) {
                addRoomType(floor, roomNumber, addedRooms, roomType);
                missingRoom = true;
            }
            else{
                i++;
            }
        }
        if(!missingRoom && i == floor.size()) {
            roomNumber = roomNumbers.get(i);
            addRoomType(floor, roomNumber, addedRooms, roomType);
        }
    }

    public void addRoomType(ArrayList<Room> floor, int roomNumber, ArrayList<Integer> addedRooms, int roomType){
        Room room = switch (roomType) {
            case 1 -> new Room(roomNumber, this);
            case 2 -> new DeluxeRoom(roomNumber, this);
            case 3 -> new ExecutiveRoom(roomNumber, this);
            default -> null;
        };

        rooms.add(room);
        floor.add(room);
        addedRooms.add(roomNumber);
    }

    public int getNumberOfStandardRooms(){
        return firstFloor.size() + secondFloor. size() + thirdFloor.size();
    }

    public int getNumberOfDeluxeRooms(){
        return fourthFloor.size();
    }

    public int getNumberOfExecutiveRooms(){
        return fifthFloor.size();
    }
}
