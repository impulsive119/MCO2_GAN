public class ControlHRS {
    private final HotelReservationSystem HRS;
    private final ViewHRS GUI;

    public ControlHRS(HotelReservationSystem HRS, ViewHRS GUI){
        this.HRS = HRS;
        this.GUI = GUI;
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

    public void viewHotelInfo(){
        GUI.printSelectHotel(HRS);
        int hotel = InputHelper.nextInt();
        if(HRS.selectHotel(hotel) !=null){
            GUI.printHotelInfo(HRS.selectHotel(hotel));
        }else{
            GUI.printInvalidHotel();
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
        }
    }

    public void viewRoom(Hotel hotel){
        GUI.printSelectRoom(hotel);
        int roomNumber = InputHelper.nextInt();
        if(hotel.selectRoom(roomNumber) != null){
            GUI.printRoomInfo(hotel.selectRoom(roomNumber));
        }else{
            GUI.printInvalidRoom();
        }

    }

    public void viewReservation(Hotel hotel){
        GUI.printSelectReservation(hotel);
        int reservationNumber = InputHelper.nextInt();
        Reservation reservation = hotel.selectReservation(reservationNumber);
        if( reservation != null)
            GUI.printReservationInfo(reservation);
        else{
            GUI.printInvalidReservation();
        }
    }

    public void changeName(Hotel hotel){
        GUI.printEnterNewHotelName();
        String name = InputHelper.nextStr();
        if(HRS.isNameUnique(HRS.getHotelNames(), name)){
            hotel.setName(name);
        }
        else{
            GUI.printHotelNameError();
        }
    }

    public void addRooms(Hotel hotel){
        GUI.printEnterRoomType();
        int roomType = InputHelper.nextInt();
        if(roomType < 0 || roomType > 4){
            GUI.printInvalidRoomType();
            return;
        }

        GUI.printEnterNumOfRooms();
        int numOfRooms = InputHelper.nextInt();
        if(HRS.isNumberOfRoomsInvalid(hotel, roomType, numOfRooms)){
            hotel.addRooms(numOfRooms, roomType);
        }else{
            GUI.printTooManyRooms(roomType);
        }
    }

    public void removeRoom(Hotel hotel){
        GUI.printSelectRoom(hotel);
        int roomNumber = InputHelper.nextInt();
        Room room = hotel.selectRoom(roomNumber);
        if (room == null) {
            GUI.printInvalidRoom();
        } else if (room.getNumOfReservations() != 0) {
            GUI.printCannotRemoveRoomWithReservations();
        } else {
            hotel.removeRoom(room);
        }
    }

    public void changePrice(Hotel hotel){
        if(hotel.getNumberOfReservations() > 0){
            System.out.println("Cannot Change Price if Hotel has Active Reservations");
            return;
        }
        GUI.printEnterNewPrice();
        double price = InputHelper.nextDouble();
        if(price < 100){
            GUI.printMinimumHotelPrice();
        }else{
            hotel.changePrice(price);
        }
    }

    public void setPremiumOnDate(Hotel hotel){
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
        }else{
            hotel.addPremiumToDate(date, premium);
        }
    }

    public void removeReservation(Hotel hotel){
        GUI.printSelectReservation(hotel);
        int reservation = InputHelper.nextInt();
        if(hotel.selectReservation(reservation) != null){
            hotel.removeReservation(hotel.selectReservation(reservation));
        }else{
            GUI.printInvalidReservation();
        }
    }

    public void removeHotel(){
        GUI.printSelectHotel(HRS);
        int hotel = InputHelper.nextInt();
        if(HRS.selectHotel(hotel) != null){
            HRS.removeHotel(HRS.selectHotel(hotel));
        }
    }
}
