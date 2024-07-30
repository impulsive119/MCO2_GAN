package model;

import java.util.ArrayList;

/**
 * The Floor class represents a floor in a hotel. It manages the rooms on that floor and can add, remove, and retrieve information about rooms.
 */
public class Floor {
    private final int floorNumber;
    private final Room[] rooms;
    private final Hotel hotel;

    /**
     * Constructs a Floor object using the given floor number and the hotel it is in.
     *
     * @param floorNumber The floor number.
     * @param hotel The hotel that this floor is in.
     */
    public Floor(int floorNumber, Hotel hotel) {
        this.floorNumber = floorNumber;
        this.hotel = hotel;
        this.rooms = new Room[10];
    }

    /**
     * Gets the number of rooms in the floor.
     *
     * @return The number of rooms in the floor.
     */
    public int getNumOfRooms() {
        return getRooms().size();
    }

    /**
     * Gets the list of all non-null rooms on this floor.
     *
     * @return The list of Rooms on this floor.
     */
    public ArrayList<Room> getRooms() {
        ArrayList<Room> returnedRooms = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (rooms[i] != null) {
                returnedRooms.add(rooms[i]);
            }
        }
        return returnedRooms;
    }

    /**
     * Adds a new room to the floor using the given roomType.
     *
     * @param roomType The type of room to add (1 for Standard, 2 for Deluxe, 3 for Executive).
     * @return The newly created Room, or null if the room type is invalid.
     */
    public Room addRoom(int roomType) {
        int i = 0;
        Room room = null;
        int roomNumber;
        boolean isRoomAdded = false;
        while (!isRoomAdded) {
            if (rooms[i] == null) {
                roomNumber = floorNumber * 100 + i + 1;
                room = switch (roomType) {
                    case 1 -> new Room(roomNumber, this);
                    case 2 -> new DeluxeRoom(roomNumber, this);
                    case 3 -> new ExecutiveRoom(roomNumber, this);
                    default -> null;
                };
                rooms[i] = room;
                isRoomAdded = true;
            }
            i++;
        }
        return room;
    }

    /**
     * Removes a room from the floor by setting its array slot to null.
     *
     * @param roomIndex The index of the room to be removed.
     */
    public void removeRoom(int roomIndex) {
        if (roomIndex >= 0 && roomIndex < rooms.length) {
            rooms[roomIndex] = null;
        }
    }

    /**
     * Gets the hotel the floor is in.
     *
     * @return The Hotel the floor is in.
     */
    public Hotel getHotel() {
        return hotel;
    }
}