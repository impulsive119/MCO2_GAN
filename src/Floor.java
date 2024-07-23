
public class Floor {
    private final int floorNumber;
    private final Room[] rooms;
    private int numOfRooms;
    private final Hotel hotel;

    public Floor(int floorNumber, Hotel hotel) {
        this.floorNumber = floorNumber;
        this.hotel = hotel;

        this.rooms = new Room[10];
    }

    public int getNumOfRooms(){
        return numOfRooms;
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
                    case 1 -> new Room(roomNumber, hotel);
                    case 2 -> new DeluxeRoom(roomNumber, hotel);
                    case 3 -> new ExecutiveRoom(roomNumber, hotel);
                    default -> null;
                };
                rooms[i] = room;
                numOfRooms++;
                isRoomAdded = true;
            }
            i++;
        }
        return room;
    }

    public void removeRoom(int roomIndex){
        rooms[roomIndex] = null;
        numOfRooms--;
    }
}
