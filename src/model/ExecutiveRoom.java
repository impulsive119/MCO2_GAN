package model;

public class ExecutiveRoom extends Room {

    public ExecutiveRoom(int roomNumber, Floor floor) {
        super(roomNumber, floor);
        this.roomType = "Executive";
    }

    @Override
    public double getPrice(){
        return floor.getHotel().getPrice() * 1.35;
    }
}
