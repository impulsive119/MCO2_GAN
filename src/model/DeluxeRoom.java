package model;

public class DeluxeRoom extends Room {

    public DeluxeRoom(int roomNumber, Floor floor) {
        super(roomNumber, floor);
        this.roomType = "Deluxe";
    }

    @Override
    public double getPrice(){
        return floor.getHotel().getPrice() * 1.2;
    }
}
