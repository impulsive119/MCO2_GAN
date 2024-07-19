public class ExecutiveRoom extends Room {

    public ExecutiveRoom(int roomNumber, Hotel hotel) {
        super(roomNumber, hotel);
        this.roomType = "Deluxe";
    }

    @Override
    protected void initializeDates() {
        // Example: Custom price calculation based on the room type
        double deluxePrice = hotel.getPrice() * 1.35; // Adjust as per your business logic
        for (int i = 0; i < 31; i++) {
            dates.add(new Date(i + 1, deluxePrice));
        }
    }


    public void setPrice(double price){
        this.price = price * 1.35;
        for(Date date: dates){
            date.setPrice(price);
        }
    }
}
