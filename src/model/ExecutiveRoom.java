package model;

/**
 * The ExecutiveRoom class is a type of Room that has a different roomType and a price 135% more expensive than that of a standard room.
 */
public class ExecutiveRoom extends Room {

    /**
     * Constructs an ExecutiveRoom using the given room number and floor number.
     *
     * @param roomNumber The room number.
     * @param floor The Floor this room is located on.
     */
    public ExecutiveRoom(int roomNumber, Floor floor) {
        super(roomNumber, floor);
        this.roomType = "Executive";
    }

    /**
     * Gets the price of the room. The price is 135% of the base hotel price.
     *
     * @return The price of the room.
     */
    @Override
    public double getPrice() {
        return floor.getHotel().getPrice() * 1.35;
    }
}