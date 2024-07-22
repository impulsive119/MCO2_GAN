import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class ViewHRS extends JFrame {

    ViewHRS (){
        JPanel hrsPanel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 1000);
    }

    public void printEnterNewHotelName(){
        System.out.print("Enter New Hotel Name: ");
    }

    public void printNewHotelName (Hotel hotel){
        System.out.println("[Hotel " + hotel.getName() + " Created]");
        System.out.println(" ");
    }

    public void printHotelNameError (){
        System.out.println("[Hotel Name is Already Used]");
        System.out.println(" ");
    }

    public void printHotelInfo(Hotel hotel){
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

    public void printSelectDate(){
        System.out.println("Select a Date from 1 to 31");
    }

    public void printSelectHotel(HotelReservationSystem HRS){
        System.out.println("Select a Hotel");
        for(int i = 0; i < HRS.getNumOfHotels(); i++){
            System.out.println(i + ". " + HRS.getHotelNames().get(i));
        }
    }

    public void printSelectRoom(Hotel hotel){
        System.out.println("Select a Room");
        System.out.println(hotel.getRooms());
    }
    public void printSelectReservation(Hotel hotel){
        System.out.println("Select a Reservation");
        for(int i = 0; i < hotel.getNumberOfReservations(); i++){
            System.out.println(i + ". " + hotel.getReservations().get(i).getGuestName());
        }
    }

    public void printReservationInfo(Reservation reservation){
        System.out.println("Guest Name: " + reservation.getGuestName());
        System.out.println("Hotel: " + reservation.getRoom().getHotel().getName());
        System.out.println("Room Number: " + reservation.getRoom().getRoomNumber());
        System.out.println("Room Type: " + reservation.getRoom().getRoomType());
        System.out.println("Check-in Date: " + reservation.getCheckInDate());
        System.out.println("Check-out Date: " + reservation.getCheckOutDate());
        System.out.println("Total Price: " + reservation.getTotalPrice());
        System.out.println("Discount Code: " + reservation.getDiscountCode());
    }


    public void printInvalidDate(){
        System.out.println("Invalid Date");
    }

    public void printInvalidHotel(){
        System.out.println("Invalid Date");
    }

    public void printInvalidReservation(){
        System.out.println("Invalid Reservation");
    }

    public void printInvalidRoom(){
        System.out.println("Invalid Room");
    }

    public void printHRSMenu(){
        System.out.println("HOTEL RESERVATION SYSTEM");
        System.out.println("1. Create Hotel");
        System.out.println("2. View Hotel");
        System.out.println("3. Manage Hotel");
        System.out.println("4. Book Reservation");
        System.out.println("5. Exit");
    }

    public void printHotelManagementMenu(){
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

    public void printAvailabilityOnDate(ArrayList<Integer> availableDates, ArrayList<Integer> reservedDates){
        System.out.println("Available Dates: ");
        System.out.println(availableDates);
        System.out.println("Reserved Dates: ");
        System.out.println(reservedDates);
        System.out.println(" ");
    }

    public void printRoomInfo(Room room){
        System.out.println("Room Number: " + room.getRoomNumber());
        System.out.println("Room Type: " + room.getRoomType());
        System.out.println("Room Price: " + room.getPrice());
        System.out.println("Days room is available:");
        System.out.println(room.getAvailableDates());
        System.out.println("Days room is reserved:");
        System.out.println(room.getReservedDates());
        System.out.println(" ");
    }

    public void printEnterRoomType(){
        System.out.println("Enter Type of Room to Add:");
        System.out.println("1. Standard");
        System.out.println("2. Deluxe");
        System.out.println("3. Executive");
    }

    public void printEnterNumOfRooms(){
        System.out.println("Enter Number Of Rooms: ");
    }

    public void printInvalidRoomType() {
        System.out.println("Invalid Room Type");
    }

    public void printTooManyRooms(int roomType) {
        switch (roomType){
            case 1:
                System.out.println("The Number of Standard Rooms Cannot Exceed 30");
                break;
            case 2:
                System.out.println("The Number of Deluxe Rooms Cannot Exceed 10");
                break;
            case 3:
                System.out.println("The Number of Executive Rooms Cannot Exceed 10");
        }
    }

    public void printCannotRemoveRoomWithReservations(){
        System.out.println("[Cannot Remove Room With Active Reservations]");
    }

    public void printEnterNewPrice(){
        System.out.println("Enter New Price: ");
    }

    public void printMinimumHotelPrice(){
        System.out.println("[Price Must Be Greater Than Or Equal To 100.00]");
    }

    public void printSetPremium(){
        System.out.println("Set a Premium from 50% to 150% of the Base Price");
    }

    public void printInvalidPremium(){
        System.out.println("Invalid Premium");
    }
}
