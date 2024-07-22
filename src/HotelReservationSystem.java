import javax.swing.text.View;
import java.util.ArrayList;
import java.util.NoSuchElementException;


/**
 * The HotelReservationSystem class manages operations involving hotels, rooms, and reservations, which includes
 * modification of data, input verification, and data output.
 */


public class HotelReservationSystem {
    private final ArrayList<Hotel> hotels = new ArrayList<>();
    private final ArrayList<String> hotelNames = new ArrayList<>();

    public boolean createHotel(String hotelName) {
        if (isNameUnique(hotelNames, hotelName)) {
            Hotel newHotel = new Hotel(hotelName);
            hotels.add(newHotel);
            hotelNames.add(newHotel.getName());
            return true;
        } else {
            return false;
        }
    }
    /*
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

        System.out.println("[Would You Like to Enter a Discount Code?]");
        System.out.println("Enter YES to Avail of Discount Code or Anything Else to Proceed]");

        String availOfDiscountCode = InputHelper.nextStr();

        if(availOfDiscountCode.equals("YES")){
            System.out.println("Enter Discount Code: ");
            String discountCode = InputHelper.nextStr();
            switch (discountCode){
                case "I_WORK_HERE":
                    hotel.addReservation(chosenRoom, guestName, checkInDate, checkOutDate, 1);
                    break;
                case "STAY4_GET1":
                    if(checkOutDate - checkInDate + 1 >= 5){
                        hotel.addReservation(chosenRoom, guestName, checkInDate, checkOutDate, 2);
                    }
                    else{
                        System.out.println("STAY4_GET1 Discount Code is Only Valid for Reservations 5 Days or Longer");
                    }
                    break;
                case "PAYDAY":
                    if((checkInDate <= 15 && checkOutDate > 15)||(checkInDate <= 28 && checkOutDate > 28)){
                        hotel.addReservation(chosenRoom, guestName, checkInDate, checkOutDate, 3);
                    }
                    else{
                        System.out.println("PAYDAY Discount Code is Only Valid for Reservations During Paydays");
                    }
                    break;
                default:
                    System.out.println("[Invalid Discount Code]");
            }
        }else{
            hotel.addReservation(chosenRoom, guestName, checkInDate, checkOutDate, 0);
        }
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


    public Hotel selectHotel(int hotelNumber){
        try {

            if (hotelNumber > 0 && hotelNumber <= hotels.size()) {
                return hotels.get(hotelNumber - 1);
            } else {
                return null;
            }
        } catch (NumberFormatException | IndexOutOfBoundsException | NoSuchElementException e) {
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

    public boolean isRoomTypeFull(Hotel hotel, int roomType){
        boolean isRoomTypeFull = false;
        switch (roomType) {
            case 1:
                if (hotel.getNumberOfStandardRooms() == 30) {
                    System.out.println("[Hotel Cannot Have More Than 30 Standard Rooms]");
                    System.out.println(" ");
                    isRoomTypeFull = true;
                }
                break;
            case 2:
                if (hotel.getNumberOfDeluxeRooms() == 10) {
                    System.out.println("[Hotel Cannot Have More Than 10 Deluxe Rooms]");
                    System.out.println(" ");
                    isRoomTypeFull = true;
                }
                break;
            case 3:
                if (hotel.getNumberOfExecutiveRooms() == 10) {
                    System.out.println("[Hotel Cannot Have More Than 10 Executive Rooms]");
                    System.out.println(" ");
                    isRoomTypeFull = true;
                }
                break;
        }
        return isRoomTypeFull;
    }

    public boolean isNumberOfRoomsInvalid(Hotel hotel, int roomType, int numOfRooms){
        if(numOfRooms < 1){
            System.out.println("[Invalid Number of Rooms]");
            System.out.println(" ");
            return true;
        }
        boolean isValidNumOfRooms = true;
        switch (roomType) {
            case 1:
                if (hotel.getNumberOfStandardRooms() + numOfRooms > 30) {
                    System.out.println("[Hotel Cannot Have More Than 30 Standard Rooms]");
                    System.out.println(" ");
                    isValidNumOfRooms = false;
                }
                break;
            case 2:
                if (hotel.getNumberOfDeluxeRooms() + numOfRooms > 10) {
                    System.out.println("[Hotel Cannot Have More Than 10 Deluxe Rooms]");
                    System.out.println(" ");
                    isValidNumOfRooms = false;
                }
                break;
            case 3:
                if (hotel.getNumberOfExecutiveRooms() + numOfRooms > 10) {
                    System.out.println("[Hotel Cannot Have More Than 10 Executive Rooms]");
                    System.out.println(" ");
                    isValidNumOfRooms = false;
                }
                break;
            default:
                System.out.println("[Invalid Room Type]");
                System.out.println(" ");
                isValidNumOfRooms = false;
                break;
        }

        return !isValidNumOfRooms;
    }

    public Hotel getHotel(String name){
        for(Hotel hotel: hotels){
            if(hotel.getName().equals(name)){
                return hotel;
            }
        }

        return null;
    }

    public int getNumOfHotels(){
        return hotels.size();
    }

    public ArrayList<String> getHotelNames(){
        return hotelNames;
    }

    public void removeHotel(Hotel hotel){
        hotels.remove(hotel);
        hotelNames.remove(hotel.getName());
    }
}
