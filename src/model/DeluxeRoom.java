package model;

/**
 * The DeluxeRoom class is a type of Room that has a different roomType and a price 120% more expensive than that of a standard room.
 */

public class DeluxeRoom extends Room {

    /**
     * Constructs a DeluxeRoom using the given room number and floor number.
     *
     * @param roomNumber The room number.
     * @param floor The Floor this room is located on.
     */
    public DeluxeRoom(int roomNumber, Floor floor) {
        super(roomNumber, floor);
        this.roomType = "Deluxe";
    }


    /**
     * Gets the price of the room. The price is 120% of the base hotel price.
     *
     * @return The price of the room.
     */
    @Override
    public double getPrice(){
        return floor.getHotel().getPrice() * 1.2;
    }
}
