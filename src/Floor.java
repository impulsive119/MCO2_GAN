public class Floor {
    private final int floorNumber;
    private final Room[] rooms;
    private int numOfRooms;
    private final Hotel hotel;

    public Floor(int floorNumber, Hotel hotel) {
        this.floorNumber = floorNumber;
        this.hotel = hotel;
        if(floorNumber == 1){
            addRoom();
        }
        this.rooms = new Room[10];
    }

    public int getNumOfRooms(){
        return numOfRooms;
    }

    public Room addRoom(){
        boolean isRoomAdded = false;
        int i = 0;
        Room room = null;
        int roomNumber;
        while (!isRoomAdded){
            assert rooms != null;
            if(rooms[i] == null){
                roomNumber = floorNumber * 100 + i + 1;
                room = switch (floorNumber) {
                    case 1, 2, 3 -> new Room(roomNumber, hotel);
                    case 4 -> new DeluxeRoom(roomNumber, hotel);
                    case 5 -> new ExecutiveRoom(roomNumber, hotel);
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
