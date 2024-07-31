package cli;

import model.Hotel;
import model.HotelReservationSystem;
import model.Reservation;
import model.Room;

import java.util.ArrayList;

/**
 * The ViewHRS class handles the user interface layer of the Hotel Reservation System through the command line by getting user inputs and printing out
 * prompts for the user to follow.
 */
public class ViewHRS {

    /**
     * Prompts the user to enter a non-blank string. If the input is blank, it returns null.
     *
     * @return The input string from the user, or null if the input is blank.
     */
    public String inputString() {
        String hotelName = InputHelper.nextStr();
        if (hotelName.isBlank()) {
            System.out.println("Invalid Input");
            return null;
        } else {
            return hotelName;
        }
    }

    /**
     * Prompts the user to enter a string that may be blank.
     *
     * @return The input string from the user.
     */
    public String inputPossiblyBlankString() {
        return InputHelper.nextStr();
    }

    /**
     * Prompts the user to enter a date and validates that it is a date from 1 to 31.
     *
     * @return The input date from the user, or -1 if the date is invalid.
     */
    public int inputDate() {
        int date = InputHelper.nextInt();
        if (date < 1 || date > 31) {
            System.out.println("Invalid Date");
            return -1;
        } else {
            return date;
        }
    }

    /**
     * Prompts the user to enter a room type and validates that it is an integer from 1 to 3.
     *
     * @return The input room type from the user, or -1 if the room type is invalid.
     */
    public int inputRoomType() {
        int roomType = InputHelper.nextInt();
        if (roomType < 1 || roomType > 3) {
            System.out.println("Invalid Room Type");
            return -1;
        } else {
            return roomType;
        }
    }

    /**
     * Prompts the user to enter an integer.
     *
     * @return The input integer from the user.
     */
    public int inputInteger() {
        return InputHelper.nextInt();
    }

    /**
     * Prompts the user to enter CONTINUE in order to keep repeating an action.
     *
     * @return true if the user input is "CONTINUE", false otherwise.
     */
    public boolean inputContinue() {
        String continueAction = InputHelper.nextStr();
        return continueAction.equals("CONTINUE");
    }

    /**
     * Prompts the user to enter a double.
     *
     * @return The input double from the user.
     */
    public double inputDouble() {
        return InputHelper.nextDouble();
    }

    /**
     * Prompts the user to confirm a modification by entering "CONFIRM".
     *
     * @return true if the user input is "CONFIRM", false otherwise.
     */
    public boolean inputConfirmModification() {
        String confirm = InputHelper.nextStr();
        return confirm.equals("CONFIRM");
    }

    /**
     * Prints a prompt asking the user to enter a new hotel name.
     */
    public void printEnterNewHotelName() {
        System.out.print("Enter New Hotel Name: ");
    }

    /**
     * Prints a message indicating that a new hotel has been created.
     *
     * @param hotel The newly created hotel.
     */
    public void printNewHotel(Hotel hotel) {
        System.out.println("[Hotel " + hotel.getName() + " Created]");
        System.out.println(" ");
    }

    public void printNewHotelName(Hotel hotel) {
        System.out.println("[New Hotel Name: " + hotel.getName() + " ]");
        System.out.println(" ");
    }

    /**
     * Prints an error message indicating that the hotel name is already used.
     */
    public void printHotelNameError() {
        System.out.println("[Hotel Name is Already Used]");
        System.out.println(" ");
    }

    /**
     * Prints a message indicating that there are no available hotels.
     */
    public void printNoHotels() {
        System.out.println("[There are no Available hotels]");
        System.out.println(" ");
    }

    /**
     * Prints a message indicating that there are no active reservations.
     */
    public void printNoReservations() {
        System.out.println("[There are no Active Reservations]");
        System.out.println(" ");
    }

    /**
     * Prints a message indicating that the hotel must have at least one room.
     */
    public void printMinimumRooms() {
        System.out.println("[Hotel must have at least 1 room]");
        System.out.println(" ");
    }

    /**
     * Prints a message indicating that a hotel must have no active reservations in order to to perform a certain action.
     */
    public void printHasActiveReservations() {
        System.out.println("[Must Have No Active Reservations to Perform this Action]");
        System.out.println(" ");
    }

    /**
     * Prints detailed information about a hotel.
     *
     * @param hotel The hotel for which information is being displayed.
     */
    public void printHotelInfo(Hotel hotel) {
        System.out.println("Hotel Name: " + hotel.getName());
        System.out.println("Total number of rooms: " + hotel.getNumberOfRooms());
        System.out.println("Total number of Standard Rooms: " + hotel.getNumberOfStandardRooms());
        System.out.println("Total number of Deluxe Rooms: " + hotel.getNumberOfDeluxeRooms());
        System.out.println("Total number of Executive Rooms: " + hotel.getNumberOfExecutiveRooms());
        System.out.println("Monthly earnings: " + hotel.getMonthlyEarnings());
        System.out.println("Choose Information to View: ");
        System.out.println("1. Room availability per date");
        System.out.println("2. Room Information");
        System.out.println("3. Reservation Information");
        System.out.println("4. Exit");
    }

    /**
     * Prints a prompt for check-in date.
     */
    public void printCheckIn() {
        System.out.println("Check-In: ");
    }

    /**
     * Prints a prompt for check-out date.
     */
    public void printCheckOut() {
        System.out.println("Check-Out: ");
    }

    /**
     * Prints a prompt for selecting a date between 1 and 31.
     */
    public void printSelectDate() {
        System.out.print("Select a Date from 1 to 31: ");
    }

    /**
     * Prints a list of available hotels and prompts the user to select one.
     *
     * @param HRS The hotel reservation system containing the list of hotels.
     */
    public void printSelectHotel(HotelReservationSystem HRS) {
        System.out.println("Select a Hotel");
        for (int i = 1; i <= HRS.getNumOfHotels(); i++) {
            System.out.println(i + ". " + HRS.getHotelNames().get(i - 1));
        }
    }

    /**
     * Prints a list of rooms available in the selected hotel and prompts the user to select one.
     *
     * @param hotel The hotel from which rooms are being listed.
     */
    public void printSelectRoom(Hotel hotel) {
        System.out.println("Select a Room");
        System.out.println(hotel.getRoomNumbers());
    }

    /**
     * Prints a list of reservations in the selected hotel and prompts the user to select one.
     *
     * @param hotel The hotel from which reservations are being listed.
     */
    public void printSelectReservation(Hotel hotel) {
        System.out.println("Select a Reservation");
        for (int i = 1; i <= hotel.getNumberOfReservations(); i++) {
            System.out.println(i + ". " + hotel.getReservations().get(i - 1).getGuestName());
        }
    }

    /**
     * Prints a message indicating that rooms have been added.
     *
     * @param roomNumbers A list of the room numbers that were added.
     */
    public void printAddedRooms(ArrayList<Integer> roomNumbers) {
        System.out.println(roomNumbers + " Added");
        System.out.println(" ");
    }

    /**
     * Prints detailed information about a reservation.
     *
     * @param reservation The reservation for which information is being displayed.
     */
    public void printReservationInfo(Reservation reservation) {
        System.out.println("Guest Name: " + reservation.getGuestName());
        System.out.println("Hotel: " + reservation.getRoom().getHotel().getName());
        System.out.println("Room Number: " + reservation.getRoom().getRoomNumber());
        System.out.println("Room Type: " + reservation.getRoom().getRoomType());
        System.out.println("Check-in Date: " + reservation.getCheckInDate());
        System.out.println("Check-out Date: " + reservation.getCheckOutDate());
        System.out.println("Total Price: " + reservation.getTotalPrice());
        System.out.println("Discount Code: " + reservation.getDiscountCode());
        System.out.println(" ");
    }

    /**
     * Prints a message indicating that the date is invalid.
     */
    public void printInvalidDate() {
        System.out.println("[Invalid Date]");
        System.out.println(" ");
    }

    /**
     * Prints a message indicating that the check-in date must be before the check-out date.
     */
    public void printCheckInError() {
        System.out.println("[Check-In Date Must Be Before Check-Out Date]");
        System.out.println(" ");
    }

    /**
     * Prints a message indicating that the selected hotel is invalid.
     */
    public void printInvalidHotel() {
        System.out.println("[Invalid Hotel]");
        System.out.println(" ");
    }

    /**
     * Prints a message indicating that the selected reservation is invalid.
     */
    public void printInvalidReservation() {
        System.out.println("[Invalid Reservation]");
        System.out.println(" ");
    }

    /**
     * Prints a message indicating that the selected room is invalid.
     */
    public void printInvalidRoom() {
        System.out.println("[Invalid Room]");
        System.out.println(" ");
    }

    /**
     * Prints a prompt asking the user to enter the guest name.
     */
    public void printEnterGuestName() {
        System.out.println("Input Guest Name: ");
    }

    /**
     * Prints a prompt asking the user if they want to enter a discount code.
     */
    public void printEnterDiscountCode() {
        System.out.println("Would You Like to Enter a Discount Code?");
        System.out.println("Enter a Discount Code to Avail of It or Anything Else to Proceed");
    }

    /**
     * Prints the main menu of the Hotel Reservation System.
     */
    public void printHRSMenu() {
        System.out.println("HOTEL RESERVATION SYSTEM");
        System.out.println("1. Create Hotel");
        System.out.println("2. View Hotel");
        System.out.println("3. Manage Hotel");
        System.out.println("4. Book Reservation");
        System.out.println("5. Exit");
    }

    /**
     * Prints the menu for managing a hotel.
     */
    public void printHotelManagementMenu() {
        System.out.println("Choose Option:");
        System.out.println("1. Change Hotel Name");
        System.out.println("2. Add Rooms");
        System.out.println("3. Remove Room");
        System.out.println("4. Change Room Price");
        System.out.println("5. Add Premium to Date");
        System.out.println("6. Remove Reservation");
        System.out.println("7. Remove Hotel");
        System.out.println("8. Exit");
    }

    /**
     * Prints the availability and reservation status of rooms for a specific date.
     *
     * @param availableDates A list of available dates.
     * @param reservedDates A list of reserved dates.
     */
    public void printAvailabilityOnDate(ArrayList<Integer> availableDates, ArrayList<Integer> reservedDates) {
        System.out.println("Available Rooms: ");
        System.out.println(availableDates);
        System.out.println("Reserved Rooms: ");
        System.out.println(reservedDates);
        System.out.println(" ");
    }

    /**
     * Prints detailed information about a room.
     *
     * @param room The room for which information is being displayed.
     */
    public void printRoomInfo(Room room) {
        System.out.println("Room Number: " + room.getRoomNumber());
        System.out.println("Room Type: " + room.getRoomType());
        System.out.println("Room Price: " + room.getPrice());
        System.out.println("Days room is available:");
        System.out.println(room.getAvailableDates());
        System.out.println("Days room is reserved:");
        System.out.println(room.getReservedDates());
        System.out.println(" ");
    }

    /**
     * Prints a prompt asking the user to enter the type of room to add.
     */
    public void printEnterRoomType() {
        System.out.println("Enter Type of Room to Add:");
        System.out.println("1. Standard");
        System.out.println("2. Deluxe");
        System.out.println("3. Executive");
    }

    /**
     * Prints a prompt asking the user to enter the number of rooms to add.
     */
    public void printEnterNumOfRooms() {
        System.out.println("Enter Number Of Rooms: ");
    }

    /**
     * Prints a message indicating that the selected room type is invalid.
     */
    public void printInvalidRoomType() {
        System.out.println("[Invalid Room Type]");
        System.out.println(" ");
    }

    /**
     * Prints a message indicating that the number of rooms in a hotel cannot exceed 50.
     */
    public void printTooManyRooms() {
        System.out.println("[The Number of Rooms in a Hotel Cannot Exceed 50]");
        System.out.println(" ");
    }

    /**
     * Prints a message indicating that a room with active reservations cannot be removed.
     */
    public void printCannotRemoveRoomWithReservations() {
        System.out.println("[Cannot Remove Room With Active Reservations]");
        System.out.println(" ");
    }

    /**
     * Prints a prompt asking the user to enter a new price for a room.
     */
    public void printEnterNewPrice() {
        System.out.println("Enter New Price: ");
    }

    /**
     * Prints a message indicating that the price must be greater than or equal to 100.00.
     */
    public void printMinimumHotelPrice() {
        System.out.println("[Price Must Be Greater Than Or Equal To 100.00]");
        System.out.println(" ");
    }

    /**
     * Prints a prompt asking the user to set a premium for a room.
     */
    public void printSetPremium() {
        System.out.println("Set a Premium from x0.5 to x1.5 of the Base Price");
    }

    /**
     * Prints a message indicating that the provided premium value is invalid.
     */
    public void printInvalidPremium() {
        System.out.println("[Invalid Premium]");
        System.out.println(" ");
    }

    /**
     * Prints a message indicating that the room is reserved on the selected date.
     */
    public void printRoomIsReserved() {
        System.out.println("[Room is Reserved on this Date]");
        System.out.println(" ");
    }

    /**
     * Prints a message indicating the status of a reservation.
     *
     * @param status The status code representing different reservation outcomes.
     */
    public void printCheckReservation(int status) {
        switch (status) {
            case 1:
                System.out.println("[Reservation Added]");
                System.out.println(" ");
                break;
            case 2:
                System.out.println("[This Discount may Only Be Availed If Stay Lasts At Least 5 Days]");
                System.out.println(" ");
                break;
            case 3:
                System.out.println("[This Discount may Only Be Availed If Stay Is on a Payday]");
                System.out.println(" ");
                break;
            case 4:
                break;
        }
    }

    /**
     * Prints a prompt asking the user to enter "CONFIRM" to proceed with a change.
     */
    public void printConfirmation() {
        System.out.println("Enter CONFIRM to Proceed With Change");
    }

    /**
     * Prints a prompt asking the user to enter any key to exit.
     */
    public void printToggle() {
        System.out.println("Enter any key to Exit");
    }

    /**
     * Prints a message indicating that the modification has been discarded.
     */
    public void printDiscardModification() {
        System.out.println("[Modification Discarded]");
        System.out.println(" ");
    }

    /**
     * Prints a message indicating that the modification has been confirmed.
     */
    public void printModificationConfirmed() {
        System.out.println("[Modification Confirmed]");
        System.out.println(" ");
    }

    /**
     * Prints a message indicating that the selected option is invalid.
     */
    public void printInvalidOption() {
        System.out.println("[Invalid Option]");
        System.out.println(" ");
    }

    /**
     * Prints a prompt asking the user to enter "CONTINUE" to keep removing rooms, or anything else to stop.
     */
    public void printKeepRemovingRooms() {
        System.out.println("Enter CONTINUE to keep removing rooms and anything else to stop");
    }

    /**
     * Prints a blank line for spacing.
     */
    public void printSpace() {
        System.out.println(" ");
    }
}