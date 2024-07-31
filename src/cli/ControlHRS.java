package cli;

import model.Hotel;
import model.HotelReservationSystem;
import model.Reservation;
import model.Room;

/**
 * The ControlHRS class handles user interactions for managing hotels, rooms, and reservations within the hotel reservation system  such as creating hotels,
 * viewing availability, managing rooms and reservations, and modifying hotel details.
 */
public class ControlHRS {
    private final HotelReservationSystem HRS;
    private final ViewHRS View;

    /**
     * Constructs a ControlHRS object using the given HotelReservationSystem and ViewHRS objects.
     *
     * @param HRS The HotelReservationSystem used to manage hotel operations.
     * @param View The ViewHRS instance used to interact with the user.
     */
    public ControlHRS(HotelReservationSystem HRS, ViewHRS View){
        this.HRS = HRS;
        this.View = View;
    }

    /**
     * Displays the main menu of the Hotel Reservation System.
     */
    public void printMenu(){
        View.printHRSMenu();
    }

    /**
     * Creates a new hotel using a name provided by the user.
     */
    public void createHotel(){
        View.printEnterNewHotelName();
        String hotelName = View.inputString();
        if(hotelName == null){
            return;
        }
        if(HRS.createHotel(hotelName)){
            View.printNewHotel(HRS.getHotel(hotelName));
        } else {
            View.printHotelNameError();
        }
    }

    /**
     * Displays the availability of rooms in a given hotel on a given date.
     *
     * @param hotel The Hotel object to check for room availability.
     */
    public void viewAvailabilityOnDate(Hotel hotel){
        View.printSelectDate();
        int date = View.inputDate();
        if(date != -1){
            View.printAvailabilityOnDate(hotel.getAvailableRoomsOnDate(date), hotel.getReservedRoomsOnDate(date));
            toggleMenu();
        }
    }

    /**
     * Displays information about a given room in the specified hotel.
     *
     * @param hotel The Hotel containing the room.
     */
    public void viewRoom(Hotel hotel){
        View.printSelectRoom(hotel);
        int roomNumber = View.inputInteger();
        if(hotel.getRoom(roomNumber) != null){
            View.printRoomInfo(hotel.getRoom(roomNumber));
            toggleMenu();
        } else {
            View.printInvalidRoom();
        }
    }

    /**
     * Displays details about a given reservation in the given hotel.
     *
     * @param hotel The Hotel containing the reservation.
     */
    public void viewReservation(Hotel hotel){
        if(hotel.getNumberOfReservations() == 0){
            View.printNoReservations();
            return;
        }
        View.printSelectReservation(hotel);
        int reservationNumber = View.inputInteger();
        Reservation reservation = hotel.getReservation(reservationNumber);
        if(reservation != null) {
            View.printReservationInfo(reservation);
            toggleMenu();
        } else {
            View.printInvalidReservation();
        }
    }

    /**
     * Changes the name of the given hotel to a name provided by the user.
     *
     * @param hotel The Hotel whose name is to be changed.
     */
    public void changeName(Hotel hotel){
        View.printEnterNewHotelName();
        String name = View.inputString();
        if(name == null){
            return;
        }
        if(confirmModification()) {
            if (hotel.setName(name)) {
                View.printNewHotelName(hotel);
            } else {
                View.printHotelNameError();
            }
        }
    }

    /**
     * Adds a given number of rooms of a given type to the given hotel.
     *
     * @param hotel The Hotel to which rooms will be added.
     */
    public void addRooms(Hotel hotel){
        if(hotel.getNumberOfRooms() == 50){
            View.printTooManyRooms();
            return;
        }
        View.printEnterRoomType();
        int roomType = View.inputRoomType();
        if(roomType < 1 || roomType > 3){
            View.printInvalidRoomType();
            return;
        }
        View.printEnterNumOfRooms();
        int numOfRooms = View.inputInteger();
        if(!hotel.isNumberOfRoomsValid(numOfRooms)){
            View.printTooManyRooms();
            return;
        }
        if(confirmModification()){
            View.printAddedRooms(hotel.addRooms(numOfRooms, roomType));
        }
    }

    /**
     * Removes rooms from the given hotel as long as the hotel always has at least 1 room.
     *
     * @param hotel The Hotel object from which rooms are to be removed.
     */
    public void removeRoom(Hotel hotel){
        boolean exit = false;
        boolean error = hotel.getNumberOfRooms() == 1;
        while(hotel.getNumberOfRooms() > 1 && !exit) {
            View.printSelectRoom(hotel);
            int roomNumber = View.inputInteger();
            Room room = hotel.getRoom(roomNumber);
            if(room == null){
                View.printInvalidRoom();
                return;
            }
            if(!room.getReservations().isEmpty()){
                View.printCannotRemoveRoomWithReservations();
                return;
            }
            if(confirmModification()){
                hotel.removeRoom(room);
            }
            if(hotel.getNumberOfRooms() == 1){
                error = true;
                break;
            }
            if(!continueRemovingRooms()){
                exit = true;
            }
        }
        if(error){
            View.printMinimumRooms();
        }
    }

    /**
     * Prompts the user to continue removing rooms or stop the operation.
     *
     * @return true if the user chooses to continue removing rooms, false otherwise.
     */
    public boolean continueRemovingRooms(){
        View.printKeepRemovingRooms();
        return View.inputContinue();
    }

    /**
     * Changes the base price of the given hotel.
     *
     * @param hotel The Hotel whose price is to be changed.
     */
    public void changePrice(Hotel hotel){
        if(hotel.getNumberOfReservations() > 0){
            View.printHasActiveReservations();
            return;
        }
        View.printEnterNewPrice();
        double price = View.inputDouble();
        if(price < 100){
            View.printMinimumHotelPrice();
            return;
        }
        if(confirmModification()){
            hotel.setPrice(price);
        }
    }

    /**
     * Sets a premium rate for a given date in the given hotel.
     *
     * @param hotel The Hotel where the premium rate is to be set.
     */
    public void setPremiumOnDate(Hotel hotel){
        if(hotel.getNumberOfReservations() > 0){
            View.printHasActiveReservations();
            return;
        }
        View.printSelectDate();
        int date = View.inputDate();
        if(date == -1){
            return;
        }
        View.printSetPremium();
        double premium = View.inputDouble();
        if(premium < 0.5 || premium > 1.5){
            View.printInvalidPremium();
            return;
        }
        if(confirmModification()){
            hotel.addPremiumToDate(date, premium);
        }
    }

    /**
     * Removes a reservation from the given hotel.
     *
     * @param hotel The Hotel from which the reservation is to be removed.
     */
    public void removeReservation(Hotel hotel){
        if(hotel.getReservations().isEmpty()){
            View.printNoReservations();
            return;
        }
        View.printSelectReservation(hotel);
        int reservation = View.inputInteger();
        if(hotel.getReservation(reservation) == null){
            View.printInvalidReservation();
            return;
        }
        if(confirmModification()){
            hotel.removeReservation(hotel.getReservation(reservation));
        }
    }

    /**
     * Removes a given hotel from the HotelReservationSystem.
     *
     * @param hotel The Hotel to be removed.
     */
    public void removeHotel(Hotel hotel){
        if(confirmModification()){
            HRS.removeHotel(hotel);
        }
    }

    /**
     * Books a reservation in a given hotel for a selected room and date range.
     */
    public void bookReservation(){
        if(HRS.getNumOfHotels() == 0){
            View.printNoHotels();
            return;
        }
        View.printSelectHotel(HRS);
        int hotelNumber = View.inputInteger();
        Hotel hotel = HRS.getHotel(hotelNumber);
        if(hotel == null){
            View.printInvalidHotel();
            return;
        }
        View.printSelectRoom(hotel);
        int roomNumber = View.inputInteger();
        Room room = hotel.getRoom(roomNumber);
        if(room == null){
            View.printInvalidRoom();
            return;
        }
        View.printCheckIn();
        View.printSelectDate();
        int checkInDate = View.inputDate();
        if(checkInDate == -1){
            return;
        }
        if(checkInDate == 31){
            View.printInvalidDate();
            return;
        }
        View.printCheckOut();
        View.printSelectDate();
        int checkOutDate = View.inputDate();
        if(checkOutDate == -1){
            return;
        }
        if(checkOutDate > 31 || checkOutDate < 2){
            View.printInvalidDate();
            return;
        }
        if(checkInDate >= checkOutDate){
            View.printCheckInError();
            return;
        }
        boolean isReservedOnDate = false;
        for(int i = checkInDate; i < checkOutDate; i++){
            if(room.getReservedDates().contains(i)){
                isReservedOnDate = true;
            }
        }
        if(isReservedOnDate){
            View.printRoomIsReserved();
            return;
        }
        View.printEnterGuestName();
        String guestName = View.inputString();
        if(guestName == null){
            return;
        }
        View.printEnterDiscountCode();
        String discountCode = View.inputPossiblyBlankString();
        View.printCheckReservation(room.addReservation(guestName, checkInDate, checkOutDate, discountCode));
    }

    /**
     * Prompts the user to confirm or discard a modification.
     *
     * @return true if the user confirms the modification, false otherwise.
     */
    public boolean confirmModification(){
        View.printConfirmation();
        boolean confirmation = View.inputConfirmModification();
        if(confirmation){
            View.printModificationConfirmed();
            return true;
        } else {
            View.printDiscardModification();
            return false;
        }
    }

    /**
     * Prompts the user to toggle the menu and returns to the previous menu after any key is clicked.
     */
    public void toggleMenu(){
        View.printToggle();
        String toggle = View.inputPossiblyBlankString();
        if(toggle != null){
            View.printSpace();
        }
    }

    /**
     * Allows the user to view and manage details of a given hotel.
     */
    public void viewHotel(){
        if(HRS.getNumOfHotels() == 0){
            View.printNoHotels();
            return;
        }
        boolean exit = false;
        View.printSelectHotel(HRS);
        int hotelNumber = View.inputInteger();
        Hotel hotel = HRS.getHotel(hotelNumber);
        if(hotel == null){
            View.printInvalidHotel();
            return;
        }
        while(!exit){
            View.printHotelInfo(hotel);
            int option = View.inputInteger();
            switch (option){
                case 1:
                    viewAvailabilityOnDate(hotel);
                    break;
                case 2:
                    viewRoom(hotel);
                    break;
                case 3:
                    viewReservation(hotel);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    View.printInvalidOption();
            }
        }
    }

    /**
     * Provides options to manage a given hotel, including renaming, adding/removing rooms, changing prices, and more.
     */
    public void manageHotel(){
        if(HRS.getNumOfHotels() == 0){
            View.printNoHotels();
            return;
        }
        boolean exit = false;
        View.printSelectHotel(HRS);
        int hotelNumber = View.inputInteger();
        Hotel hotel = HRS.getHotel(hotelNumber);
        if(hotel == null){
            View.printInvalidHotel();
            return;
        }
        while(!exit){
            View.printHotelManagementMenu();
            int option = View.inputInteger();
            switch (option){
                case 1:
                    changeName(hotel);
                    break;
                case 2:
                    addRooms(hotel);
                    break;
                case 3:
                    removeRoom(hotel);
                    break;
                case 4:
                    changePrice(hotel);
                    break;
                case 5:
                    setPremiumOnDate(hotel);
                    break;
                case 6:
                    removeReservation(hotel);
                    break;
                case 7:
                    removeHotel(hotel);
                    exit = true;
                    break;
                case 8:
                    exit = true;
                    break;
                default:
                    View.printInvalidOption();
            }
        }
    }
}