package gui;

import model.Hotel;
import model.HotelReservationSystem;
import model.Room;

import javax.swing.*;
import java.util.ArrayList;

public abstract class AddRoomsForm extends InputForm{
    private JTextField roomTypeField;
    private JTextField numOfRoomsField;
    private ComboBox hotelComboBox;

    @Override
    protected void addInputFields(HotelReservationSystem HRS){
        hotelComboBox = addComboBox("Select a Hotel:",  HRS.getHotelNames().toArray());
        roomTypeField = addTextField("Enter Room Type: ");
        numOfRoomsField = addTextField("Enter Number of Rooms: ");
    }

    @Override
    protected void onEnter() {
        Hotel hotel = (Hotel) hotelComboBox.getSelectedItem();
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
