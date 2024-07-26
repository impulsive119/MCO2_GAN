public class ControlHRS {
    private final HotelReservationSystem HRS;
    private final ViewHRS GUI;

    public ControlHRS(HotelReservationSystem HRS, ViewHRS GUI){
        this.HRS = HRS;
        this.GUI = GUI;
    }

    public void printMenu(){
        GUI.printHRSMenu();
    }

    public void createHotel(){
        GUI.printEnterNewHotelName();
        String hotelName = InputHelper.nextStr();
        if(HRS.createHotel(hotelName)){
            GUI.printNewHotelName(HRS.getHotel(hotelName));
        } else{
            GUI.printHotelNameError();
        }
    }

    public void viewAvailabilityOnDate(Hotel hotel){
        GUI.printSelectDate();
        int date = InputHelper.nextInt();
        if(date < 1 || date > 31){
            GUI.printInvalidDate();
        }
        else {
            GUI.printAvailabilityOnDate(hotel.getAvailableRoomsOnDate(date), hotel.getReservedRoomsOnDate(date));
            toggleMenu();
        }
    }

    public void viewRoom(Hotel hotel){
        GUI.printSelectRoom(hotel);
        int roomNumber = InputHelper.nextInt();
        if(hotel.selectRoom(roomNumber) != null){
            GUI.printRoomInfo(hotel.selectRoom(roomNumber));
            toggleMenu();
        }else{
            GUI.printInvalidRoom();
        }

    }

    public void viewReservation(Hotel hotel){
        if(hotel.getNumberOfReservations() == 0){
            GUI.printNoReservations();
            return;
        }
        GUI.printSelectReservation(hotel);
        int reservationNumber = InputHelper.nextInt();
        Reservation reservation = hotel.selectReservation(reservationNumber);
        if( reservation != null) {
            GUI.printReservationInfo(reservation);
            toggleMenu();
        }
        else{
            GUI.printInvalidReservation();
        }
    }

    public void changeName(Hotel hotel){
        GUI.printEnterNewHotelName();
        String name = InputHelper.nextStr();
        if(!HRS.isNameUnique(HRS.getHotelNames(), name)){
            GUI.printHotelNameError();
            return;
        }
        if(confirmModification()){
            HRS.changeHotelName(hotel, name);
        }
    }

    public void addRooms(Hotel hotel){
        if(hotel.getNumberOfRooms() == 50){
            GUI.printTooManyRooms();
            return;
        }
        GUI.printEnterRoomType();
        int roomType = InputHelper.nextInt();
        if(roomType < 0 || roomType > 3){
            GUI.printInvalidRoomType();
            return;
        }
        GUI.printEnterNumOfRooms();
        int numOfRooms = InputHelper.nextInt();
        if(!HRS.isNumberOfRoomsValid(hotel, numOfRooms)){
            GUI.printTooManyRooms();
            return;
        }
        if(confirmModification()){
            GUI.printAddedRooms(hotel.addRooms(numOfRooms, roomType));
        }
    }

    public void removeRoom(Hotel hotel){
        boolean exit = false;
        boolean error = hotel.getNumberOfRooms() == 1;
        while(hotel.getNumberOfRooms() > 1 && !exit) {
            GUI.printSelectRoom(hotel);
            int roomNumber = InputHelper.nextInt();
            Room room = hotel.selectRoom(roomNumber);
            if (room == null) {
                GUI.printInvalidRoom();
                return;
            }
            if (room.getNumOfReservations() != 0) {
                GUI.printCannotRemoveRoomWithReservations();
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
            GUI.printMinimumRooms();
        }
    }

    public boolean continueRemovingRooms(){
        GUI.printKeepRemovingRooms();
        return InputHelper.nextStr().equals("CONTINUE");
    }

    public void changePrice(Hotel hotel){
        if(hotel.getNumberOfReservations() > 0){
            GUI.printHasActiveReservations();
            return;
        }
        GUI.printEnterNewPrice();
        double price = InputHelper.nextDouble();
        if(price < 100){
            GUI.printMinimumHotelPrice();
            return;
        }
        if(confirmModification()){
            hotel.changePrice(price);
        }
    }

    public void setPremiumOnDate(Hotel hotel){
        if(hotel.getNumberOfReservations() > 0){
            GUI.printHasActiveReservations();
            return;
        }
        GUI.printSelectDate();
        int date = InputHelper.nextInt();
        if(date < 1 || date > 31){
            GUI.printInvalidDate();
            return;
        }
        GUI.printSetPremium();
        double premium = InputHelper.nextDouble();
        if(premium < 0.5 || premium > 1.5){
            GUI.printInvalidPremium();
            return;
        }
        if(confirmModification()){
            hotel.addPremiumToDate(date, premium);
        }
    }

    public void removeReservation(Hotel hotel){
        GUI.printSelectReservation(hotel);
        int reservation = InputHelper.nextInt();
        if(hotel.selectReservation(reservation) == null){
            GUI.printInvalidReservation();
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
            GUI.printNoHotels();
            return;
        }
        GUI.printSelectHotel(HRS);
        int hotelNumber = InputHelper.nextInt();
        Hotel hotel = HRS.selectHotel(hotelNumber);
        if(hotel == null){
            GUI.printInvalidHotel();
            return;
        }
        GUI.printSelectRoom(hotel);
        int roomNumber = InputHelper.nextInt();
        Room room = hotel.selectRoom(roomNumber);
        if(room == null){
            GUI.printInvalidRoom();
            return;
        }
        GUI.printCheckIn();
        GUI.printSelectDate();
        int checkInDate = InputHelper.nextInt();
        if(checkInDate < 1 || checkInDate > 30){
            GUI.printInvalidDate();
            return;
        }
        GUI.printCheckOut();
        GUI.printSelectDate();
        int checkOutDate = InputHelper.nextInt();
        if(checkOutDate > 31 || checkOutDate < 2){
            GUI.printInvalidDate();
            return;
        }
        if(checkInDate >= checkOutDate){
            GUI.printCheckInError();
            return;
        }
        boolean isReservedOnDate = false;
        for(int i = checkInDate; i < checkOutDate; i++){
            if(room.getReservedDates().contains(i)){
                isReservedOnDate = true;
            }
        }
        if(isReservedOnDate){
            GUI.printRoomIsReserved();
            return;
        }
        if (room.isReserved(checkInDate, checkOutDate)){
            GUI.printRoomIsAlreadyReserved();
            return;
        }
        GUI.printEnterGuestName();
        String guestName = InputHelper.nextStr();
        GUI.printEnterDiscountCode();
        String discountCode = InputHelper.nextStr();
        GUI.printCheckReservation(HRS.bookReservation(hotel, room, guestName, checkInDate, checkOutDate, discountCode));
    }

    public boolean confirmModification(){
        GUI.printConfirmation();
        String confirmation = InputHelper.nextStr();
        if(confirmation.equals("CONFIRM")){
            GUI.printModificationConfirmed();
            return true;
        }else{
            GUI.printDiscardModification();
            return false;
        }
    }

    public void toggleMenu(){
        GUI.printToggle();
        String toggle = InputHelper.nextStr();
        if(toggle !=null){
            GUI.printSpace();
        }
    }

    public void viewHotel(){
        if(HRS.getNumOfHotels() == 0){
            GUI.printNoHotels();
            return;
        }
        boolean exit = false;
        GUI.printSelectHotel(HRS);
        int hotelNumber = InputHelper.nextInt();
        Hotel hotel = HRS.selectHotel(hotelNumber);
        if(hotel == null){
            GUI.printInvalidHotel();
            return;
        }
        while(!exit){
            GUI.printHotelInfo(hotel);
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
                    GUI.printInvalidOption();
            }
        }
    }

    public void manageHotel(){
        if(HRS.getNumOfHotels() == 0){
            GUI.printNoHotels();
            return;
        }
        boolean exit = false;
        GUI.printSelectHotel(HRS);
        int hotelNumber = InputHelper.nextInt();
        Hotel hotel = HRS.selectHotel(hotelNumber);
        if(hotel == null){
            GUI.printInvalidHotel();
            return;
        }
        while(!exit){
            GUI.printHotelManagementMenu();
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
                    GUI.printInvalidOption();
            }
        }
    }
}
