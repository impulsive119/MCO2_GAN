public class ExecutiveRoom extends Room {

    public ExecutiveRoom(int roomNumber, Hotel hotel) {
        super(roomNumber, hotel);
        this.price = hotel.getPrice() * 1.35;
        this.roomType = "Executive";
    }

    @Override
    protected void initializeDates() {
        // Example: Custom price calculation based on the room type
        double deluxePrice = hotel.getPrice() * 1.35; // Adjust as per your business logic
        for (int i = 0; i < 31; i++) {
            dates.add(new Date(i + 1, deluxePrice));
        }
    }

    @Override
    public void setPrice(double price){
        this.price = price * 1.35;
        for(int i = 0; i < 31; i++){
            dates.get(i).setPrice(this.price * hotel.getPremiums()[i]);
        }
    }
}
