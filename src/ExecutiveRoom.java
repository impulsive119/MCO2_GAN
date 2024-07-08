public class ExecutiveRoom extends Room{

    /**
     * Creates a new Room given a specified room number and hotel to be added to.
     *
     * @param roomNumber The room number.
     * @param hotel      The hotel it will be added to.
     */
    public ExecutiveRoom(int roomNumber, Hotel hotel) {
        super(roomNumber, hotel);
        this.roomNumber = roomNumber;

        for (int i = 0; i < 31; i++){
            availableDates.add(i + 1);
        }

        this.price = hotel.getPrice() * 1.35;
        this.hotel = hotel;
    }
}
