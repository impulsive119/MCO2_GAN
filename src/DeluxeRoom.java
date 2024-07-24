public class DeluxeRoom extends Room {

    public DeluxeRoom(int roomNumber, Hotel hotel) {
        super(roomNumber, hotel);
        this.price = hotel.getPrice() * 1.2;
        this.roomType = "Deluxe";
    }

    @Override
    protected void initializeDates() {
        // Example: Custom price calculation based on the room type
        double deluxePrice = hotel.getPrice() * 1.2; // Adjust as per your business logic
        for (int i = 0; i < 31; i++) {
            dates.add(new Date(i + 1, deluxePrice));
        }
    }

    // Additional methods specific to DeluxeRoom

    @Override
    public void setPrice(double price){
        this.price = price * 1.2;
        for(Date date: dates){
            date.setPrice(this.price);
        }
    }
}
