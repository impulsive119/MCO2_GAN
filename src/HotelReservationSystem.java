import java.util.ArrayList;
import java.util.NoSuchElementException;


/**
 * The HotelReservationSystem class manages operations involving hotels, rooms, and reservations, which includes
 * modification of data, input verification, and data output.
 */


public class HotelReservationSystem {
    private final ArrayList<Hotel> hotels = new ArrayList<>();
    private final ArrayList<String> hotelNames = new ArrayList<>();


    /**
     * Creates a new hotel using the inputted name, checks if the name is unique, and adds it if it is.
     *
     * @param hotelName The name of the new hotel.
     */


    public void createHotel(String hotelName) {
        if (isNameUnique(hotelNames, hotelName)) {
            Hotel newHotel = new Hotel(hotelName);
            hotels.add(newHotel);
            hotelNames.add(newHotel.getName());
            System.out.println("[Hotel " + newHotel.getName() + " Created]");
            System.out.println(" ");
        } else {
            System.out.println("[Hotel Name is Already Used]");
            System.out.println(" ");
        }
    }


    /**
     * Displays a hotel's high and low level information upon toggle.
     *
     * @param hotel The hotel object that is to be viewed.
     */


    public void viewHotel(Hotel hotel) {
        boolean exit = false;


        do {
            System.out.println("Hotel Name: " + hotel.getName());
            System.out.println("Total number of rooms: " + hotel.getNumberOfRooms());
            System.out.println("Monthly earnings: " + hotel.getMonthlyEarnings());
            System.out.println("Choose Information to View: ");
            System.out.println("1. Room availability per date");
            System.out.println("2. Room Information");
            System.out.println("3. Reservation Information");
            System.out.println("4. Exit");


            int option = InputHelper.nextInt();


            switch (option) {
                case 1:
                    System.out.println("Select a date from 1 to 31: ");
                    int date = InputHelper.nextInt();
                    if(date < 1 || date > 31){
                        System.out.println("[Invalid Date]");
                        System.out.println(" ");
                        break;
                    }
                    hotel.viewAvailabilityOnDate(date);
                    enterKeyToExit();
                    break;
                case 2:
                    Room room = hotel.selectRoom();
                    if (room != null){
                        room.viewRoom();
                    }
                    else{
                        break;
                    }
                    enterKeyToExit();
                    break;
                case 3:
                    Reservation reservation = hotel.selectReservation();


                    if(reservation != null)
                    {
                        reservation.viewReservation();
                    }
                    else{
                        break;
                    }
                    enterKeyToExit();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("[Invalid Option]");
                    System.out.println(" ");
            }
        } while (!exit);
    }


    /**
     * Allows modification of certain details (name, rooms, prices, etc.) for a given hotel.
     *
     * @param hotel The hotel object to manage.
     */


    public void manageHotel(Hotel hotel) {
        boolean exit = false;


        do {
            System.out.println("Choose Option:");
            System.out.println("1. Change Hotel Name");
            System.out.println("2. Add Rooms");
            System.out.println("3. Remove Room");
            System.out.println("4. Change Room Price");
            System.out.println("5. Remove Reservation");
            System.out.println("6. Remove Hotel");
            System.out.println("7. Exit");


            int option = InputHelper.nextInt();
            switch (option) {
                case 1:
                    System.out.print("Enter New Name: ");
                    String newName = InputHelper.nextStr();
                    if (!isNameUnique(hotelNames, newName)){
                        System.out.println("[Name is Already Taken]");
                        System.out.println(" ");
                        break;
                    }
                    if(!confirmModification()){
                        break;
                    }
                    hotelNames.remove(hotel.getName());
                    hotel.changeName(newName);
                    hotelNames.add(newName);
                    break;
                case 2:
                    System.out.println("Enter Type of Room to Add:");
                    System.out.println("1. Standard");
                    System.out.println("2. Deluxe");
                    System.out.println("3. Executive");
                    int roomType = InputHelper.nextInt();
                    if(roomType < 1 || roomType > 3){
                        System.out.println("[Invalid Input]");
                        System.out.println(" ");
                        break;
                    }
                    System.out.println("[Enter Number of Rooms to Add]");
                    int numOfRooms = InputHelper.nextInt();
                    if(numOfRooms < 1){
                        System.out.println("[Invalid Input]");
                        System.out.println(" ");
                        break;
                    }
                    if(roomType == 1 && numOfRooms + hotel.getNumberOfStandardRooms() > 30){
                        System.out.println("[Hotel Cannot Have More Than 30 Standard Rooms]");
                        System.out.println(" ");
                        break;
                    }
                    if(roomType == 2 && numOfRooms + hotel.getNumberOfDeluxeRooms() > 10){
                        System.out.println("[Hotel Cannot Have More Than 10 Deluxe Rooms]");
                        System.out.println(" ");
                        break;
                    }
                    if(roomType == 3 && numOfRooms + hotel.getNumberOfExecutiveRooms() > 10){
                        System.out.println("[Hotel Cannot Have More Than 10 Executive Rooms]");
                        System.out.println(" ");
                        break;
                    }
                    hotel.addRooms(numOfRooms, roomType);
                    break;
                case 3:
                    boolean stop = false;
                    do {
                        Room room = hotel.selectRoom();


                        if (room == null) {
                            System.out.println("[Invalid Room]");
                        } else if (room.getNumOfReservations() != 0) {
                            System.out.println("[Cannot Remove Room With Active Reservations]");
                        } else if (confirmModification()) {
                            hotel.removeRoom(room);
                        }


                        System.out.println("[Enter YES to Remove Another Room and Anything Else to Exit]");
                        String repeat = InputHelper.nextStr();
                        if(!repeat.equals("YES")){
                            stop = true;
                        }
                    }while(hotel.getNumberOfRooms() > 1 && !stop);


                    if (hotel.getNumberOfRooms() == 1) {
                        System.out.println("[There Must Always Be At Least One Room]");
                    }
                    break;
                case 4:
                    if (hotel.getNumberOfReservations() != 0) {
                        System.out.println("[There Must Be No Active Reservations in Order to Change the Room Price]");
                        System.out.println(" ");
                        break;
                    }


                    System.out.println("Enter New Price: ");
                    double newPrice = InputHelper.nextDouble();
                    InputHelper.nextStr();


                    if (newPrice < 100) {
                        System.out.println("[Price Must Be Greater Than Or Equal To 100.00]");
                        System.out.println(" ");
                        break;
                    }


                    if (!confirmModification()) {
                        break;
                    }


                    hotel.changePrice(newPrice);
                    break;
                case 5:
                    Reservation reservation = hotel.selectReservation();
                    if(reservation == null){
                        break;
                    }
                    if(!confirmModification()){
                        break;
                    }
                    hotel.removeReservation(reservation);
                    break;
                case 6:
                    if(!confirmModification()){
                        break;
                    }
                    hotels.remove(hotel);
                    hotelNames.remove(hotel.getName());
                    System.out.println("[Hotel Removed]");
                    System.out.println(" ");
                    exit = true;
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("[Invalid Option]");
                    System.out.println(" ");
            }
        } while (!exit);
    }


    /**
     * Books a reservation in a selected room in a selected hotel, given the dates given are valid.
     *
     * @param hotel The hotel object where the reservation will be made.
     */


    public void bookReservation(Hotel hotel) {
        System.out.println("Hotel: " + hotel.getName());
        Room chosenRoom = hotel.selectRoom();
        if(chosenRoom == null){
            return;
        }


        System.out.println("[Please Input a Check-In Date]");
        int checkInDate = InputHelper.nextInt();
        if(checkInDate < 1 || checkInDate > 30){
            System.out.println("[Invalid Check-In Date Provided]");
            return;
        }


        System.out.println("[Please Input a Check-Out Date]");
        int checkOutDate = InputHelper.nextInt();
        if(checkOutDate > 31 || checkOutDate < 2){
            System.out.println("[Invalid Check-Out Date Provided]");
            System.out.println(" ");
            return;
        }


        if(checkInDate >= checkOutDate){
            System.out.println("[Check-In Date Must Be Before Check-Out Date]");
            System.out.println(" ");
            return;
        }


        if (chosenRoom.isReserved(checkInDate, checkOutDate)){
            System.out.println("[Room is Already Reserved on These Dates]");
            System.out.println(" ");
            return;
        }


        System.out.println("[Input Guest Name]");
        String guestName = InputHelper.nextStr();


        hotel.addReservation(chosenRoom, guestName, checkInDate, checkOutDate);
    }


    /**
     * Checks if a name is unique given the list of existing names.
     *
     * @param names The list of existing names.
     * @param name  The name to check.
     * @return True if the name is unique (not found in the list), false otherwise.
     */


    public boolean isNameUnique(ArrayList<String> names, String name) {return !names.contains(name);}


    /**
     * Prints the names of all the hotels.
     */


    public void printHotelNames() {
        for (int i = 0; i < hotels.size(); i++) {
            System.out.println((i + 1) + ": " + hotels.get(i).getName());
        }
    }


    /**
     * Allows the user to select a hotel from the current list of hotels.
     *
     * @return The selected Hotel object, which is null if the selected hotel is invalid.
     */


    public Hotel selectHotel(){
        if (hotels.isEmpty()) {
            System.out.println("[No hotels available]");
            System.out.println(" ");
            return null;
        }


        try {
            System.out.println("Select Hotel: ");
            printHotelNames();
            System.out.print("Enter your choice: ");
            int chosenHotelIndex = InputHelper.nextInt();


            if (chosenHotelIndex > 0 && chosenHotelIndex <= hotels.size()) {
                Hotel chosenHotel = hotels.get(chosenHotelIndex - 1);
                System.out.println(" ");
                return chosenHotel;
            } else {
                System.out.println("[Invalid Hotel]");
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
     * Asks for confirmation before modifying an object.
     *
     * @return True if the user confirms, false otherwise.
     */


    public boolean confirmModification(){
        System.out.println("[Enter CONFIRM to confirm modification]");
        String confirmation = InputHelper.nextStr();
        if(confirmation.equals("CONFIRM")){
            return true;
        }
        else{
            System.out.println("[Modification Discarded]");
            System.out.println(" ");
            return false;
        }
    }


    /**
     * Waits for the user to enter any key before proceeding.
     */


    public void enterKeyToExit(){
        System.out.print("[Enter Any Key to Exit] ");
        String exit = InputHelper.nextStr();
        if(exit != null) {
            System.out.println(" ");
        }
    }
}
