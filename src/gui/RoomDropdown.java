package gui;

import model.Hotel;
import model.Room;

import javax.swing.*;

public abstract class RoomDropdown extends Dropdown{
    public Room setDropdown(Hotel hotel){
        int[] rooms = new int[hotel.getNumberOfRooms()];
        for(int i = 0; i < hotel.getNumberOfRooms(); i++){
            rooms[i] = hotel.getRooms().get(i).getRoomNumber();
        }

        int roomNumber = Integer.parseInt(JOptionPane.showInputDialog(null, "Select Room", rooms));
        return hotel.selectRoom(roomNumber);
    }
}
