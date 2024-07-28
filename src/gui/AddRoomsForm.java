package gui;

import model.Hotel;
import model.Room;

import javax.swing.*;

public abstract class AddRoomsForm extends InputForm{
    private JTextField roomTypeField;
    private JTextField numOfRoomsField;

    @Override
    protected void addInputFields(){
        roomTypeField = addTextField("Enter Room Type: ");
        numOfRoomsField = addTextField("Enter Number of Rooms: ");
    }

    @Override
    protected void onEnter(Hotel hotel) {
        String roomType = roomTypeField.getText();
        int numOfRooms = Integer.parseInt(numOfRoomsField.getText());
        int room = 0;
        switch(roomType){
            case "Standard":
                room = 1;
                break;
            case "Deluxe":
                room = 2;
                break;
            case "Executive":
                room = 3;
                break;
            default:
                break;
        }

        JOptionPane.showMessageDialog(this,hotel.addRooms(numOfRooms, room));
    }
}
