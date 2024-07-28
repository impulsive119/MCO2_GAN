package gui;

import model.Reservation;
import model.Hotel;
import model.Room;

import javax.swing.*;

public abstract class ViewRoomForm extends InputForm{
    private JTextField roomNumberField;

    @Override
    protected void addInputFields(){
        roomNumberField = addTextField("Enter Room Number: ");
    }

    @Override
    protected void onEnter(Hotel hotel){
        int roomNumber = Integer.parseInt(roomNumberField.getText());

        Room chosenRoom = null;

        for(Room room: hotel.getRooms()){
            if(room.getRoomNumber() == roomNumber){
                chosenRoom = room;
            }
        }if(chosenRoom != null) {
            JOptionPane.showMessageDialog(
                    this,"Room Number: " + roomNumber + "\n" +
                            "Room Type: " + chosenRoom.getRoomType()+"\n" +
                            "Room Price: " + chosenRoom.getPrice() + "\n" +
                            "Room Type: " + chosenRoom.getRoomType() + "\n" +
                            "Days Room is Available: " + "\n" +
                            chosenRoom.getAvailableDates() + "\n" +
                            "Days Room is Reserved: " + "\n" +
                            chosenRoom.getReservedDates());
        }
    }
}
