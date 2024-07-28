package cli;

import model.Hotel;
import model.HotelReservationSystem;
import model.Reservation;
import model.Room;

public class ControlHRS {
    private final HotelReservationSystem HRS;
    private final ViewHRS View;

    public ControlHRS(HotelReservationSystem HRS, ViewHRS View){
        this.HRS = HRS;
        this.View = View;
    }

    public void printMenu(){
        View.printHRSMenu();
    }

    public void createHotel(){
        View.printEnterNewHotelName();
        String hotelName = InputHelper.nextStr();
        if(HRS.createHotel(hotelName)){
            View.printNewHotelName(HRS.getHotel(hotelName));
        } else{
            View.printHotelNameError();
        }
    }

    public void viewAvailabilityOnDate(Hotel hotel){
        View.printSelectDate();
        int date = InputHelper.nextInt();
        if(date < 1 || date > 31){
            View.printInvalidDate();
        }
        else {
            View.printAvailabilityOnDate(hotel.getAvailableRoomsOnDate(date), hotel.getReservedRoomsOnDate(date));
            toggleMenu();
        }
    }

    public void viewRoom(Hotel hotel){
        View.printSelectRoom(hotel);
        int roomNumber = InputHelper.nextInt();
        if(hotel.selectRoom(roomNumber) != null){
            View.printRoomInfo(hotel.selectRoom(roomNumber));
            toggleMenu();
        }else{
            View.printInvalidRoom();
        }

    }

    public void viewReservation(Hotel hotel){
        if(hotel.getNumberOfReservations() == 0){
            View.printNoReservations();
            return;
        }
        View.printSelectReservation(hotel);
        int reservationNumber = InputHelper.nextInt();
        Reservation reservation = hotel.selectReservation(reservationNumber);
        if( reservation != null) {
            View.printReservationInfo(reservation);
            toggleMenu();
        }
        else{
            View.printInvalidReservation();
        }
    }

    public void changeName(Hotel hotel){
        View.printEnterNewHotelName();
        String name = InputHelper.nextStr();
        if(hotel.setName(name)){
            View.printNewHotelName(hotel);
        }else{
            View.printHotelNameError();
        }
    }

    public void addRooms(Hotel hotel){
        if(hotel.getNumberOfRooms() == 50){
            View.printTooManyRooms();
            return;
        }
        View.printEnterRoomType();
        int roomType = InputHelper.nextInt();
        if(roomType < 1 || roomType > 3){
            View.printInvalidRoomType();
            return;
        }
        View.printEnterNumOfRooms();
        int numOfRooms = InputHelper.nextInt();
        if(!HRS.isNumberOfRoomsValid(hotel, numOfRooms)){
            View.printTooManyRooms();
            return;
        }
        if(confirmModification()){
            View.printAddedRooms(hotel.addRooms(numOfRooms, roomType));
        }
    }

    public void removeRoom(Hotel hotel){
        boolean exit = false;
        boolean error = hotel.getNumberOfRooms() == 1;
        while(hotel.getNumberOfRooms() > 1 && !exit) {
            View.printSelectRoom(hotel);
            int roomNumber = InputHelper.nextInt();
            Room room = hotel.selectRoom(roomNumber);
            if (room == null) {
                View.printInvalidRoom();
                return;
            }
            if (!room.getReservations().isEmpty()) {
                View.printCannotRemoveRoomWithReservations();
                return;
            }
            if (confirmModification()) {
                hotel.removeRoom(room);
            }
            if(hotel.getNumberOfRooms() == 1){
                error = true;
                break;
            }
            if (!continueRemovingRooms()){
                exit = true;
            }
        }
        if(error){
            View.printMinimumRooms();
        }
    }

    public boolean continueRemovingRooms(){
        View.printKeepRemovingRooms();
        return InputHelper.nextStr().equals("CONTINUE");
    }

    public void changePrice(Hotel hotel){
        if(hotel.getNumberOfReservations() > 0){
            View.printHasActiveReservations();
            return;
        }
        View.printEnterNewPrice();
        double price = InputHelper.nextDouble();
        if(price < 100){
            View.printMinimumHotelPrice();
            return;
        }
        if(confirmModification()){
            hotel.changePrice(price);
        }
    }

    public void setPremiumOnDate(Hotel hotel){
        if(hotel.getNumberOfReservations() > 0){
            View.printHasActiveReservations();
            return;
        }
        View.printSelectDate();
        int date = InputHelper.nextInt();
        if(date < 1 || date > 31){
            View.printInvalidDate();
            return;
        }
        View.printSetPremium();
        double premium = InputHelper.nextDouble();
        if(premium < 0.5 || premium > 1.5){
            View.printInvalidPremium();
            return;
        }
        if(confirmModification()){
            hotel.addPremiumToDate(date, premium);
        }
    }

    public void removeReservation(Hotel hotel){
        if(hotel.getReservations().isEmpty()){
            View.printNoReservations();
            return;
        }
        View.printSelectReservation(hotel);
        int reservation = InputHelper.nextInt();
        if(hotel.selectReservation(reservation) == null){
            View.printInvalidReservation();
            return;

        }
        if(confirmModification()){
            hotel.removeReservation(hotel.selectReservation(reservation));
        }
    }

    public void removeHotel(Hotel hotel){
        if(confirmModification()){
            HRS.removeHotel(hotel);
        }
    }

    public void bookReservation(){
        if(HRS.getNumOfHotels() == 0){
            View.printNoHotels();
            return;
        }
        View.printSelectHotel(HRS);
        int hotelNumber = InputHelper.nextInt();
        Hotel hotel = HRS.selectHotel(hotelNumber);
        if(hotel == null){
            View.printInvalidHotel();
            return;
        }
        View.printSelectRoom(hotel);
        int roomNumber = InputHelper.nextInt();
        Room room = hotel.selectRoom(roomNumber);
        if(room == null){
            View.printInvalidRoom();
            return;
        }
        View.printCheckIn();
        View.printSelectDate();
        int checkInDate = InputHelper.nextInt();
        if(checkInDate < 1 || checkInDate > 30){
            View.printInvalidDate();
            return;
        }
        View.printCheckOut();
        View.printSelectDate();
        int checkOutDate = InputHelper.nextInt();
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
        String guestName = InputHelper.nextStr();
        View.printEnterDiscountCode();
        String discountCode = InputHelper.nextStr();
        View.printCheckReservation(room.addReservation(guestName, checkInDate, checkOutDate, discountCode));
    }

    public boolean confirmModification(){
        View.printConfirmation();
        String confirmation = InputHelper.nextStr();
        if(confirmation.equals("CONFIRM")){
            View.printModificationConfirmed();
            return true;
        }else{
            View.printDiscardModification();
            return false;
        }
    }

    public void toggleMenu(){
        View.printToggle();
        String toggle = InputHelper.nextStr();
        if(toggle !=null){
            View.printSpace();
        }
    }

    public void viewHotel(){
        if(HRS.getNumOfHotels() == 0){
            View.printNoHotels();
            return;
        }
        boolean exit = false;
        View.printSelectHotel(HRS);
        int hotelNumber = InputHelper.nextInt();
        Hotel hotel = HRS.selectHotel(hotelNumber);
        if(hotel == null){
            View.printInvalidHotel();
            return;
        }
        while(!exit){
            View.printHotelInfo(hotel);
            int option = InputHelper.nextInt();
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

    public void manageHotel(){
        if(HRS.getNumOfHotels() == 0){
            View.printNoHotels();
            return;
        }
        boolean exit = false;
        View.printSelectHotel(HRS);
        int hotelNumber = InputHelper.nextInt();
        Hotel hotel = HRS.selectHotel(hotelNumber);
        if(hotel == null){
            View.printInvalidHotel();
            return;
        }
        while(!exit){
            View.printHotelManagementMenu();
            int option = InputHelper.nextInt();
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
