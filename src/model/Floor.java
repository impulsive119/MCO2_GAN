package model;

import java.util.ArrayList;

public class Floor {
    private final int floorNumber;
    private final Room[] rooms;
    private final Hotel hotel;

    public Floor(int floorNumber, Hotel hotel) {
        this.floorNumber = floorNumber;
        this.hotel = hotel;

        this.rooms = new Room[10];
    }

    public int getNumOfRooms(){
        return getRooms().size();
    }

    public ArrayList<Room> getRooms(){
        ArrayList<Room> returnedRooms = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            if(rooms[i] != null){
               returnedRooms.add(rooms[i]);
            }
        }
        return returnedRooms;
    }

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

    public void removeRoom(int roomIndex){
        rooms[roomIndex] = null;
    }

    public Hotel getHotel(){
        return hotel;
    }
}
