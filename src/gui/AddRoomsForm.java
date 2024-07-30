package gui;

import model.Hotel;
import model.HotelReservationSystem;

import javax.swing.*;
import java.util.ArrayList;

public abstract class AddRoomsForm extends InputForm{
    private ComboBox roomTypeComboBox;
    private JTextField numOfRoomsField;
    private ComboBox hotelComboBox;

    public AddRoomsForm(HotelReservationSystem HRS){
        super(HRS);
    }

    @Override
    protected void addInputFields(){
        hotelComboBox = addComboBox("Select a Hotel:",  HRS.getHotelNames().toArray());
        ArrayList<String> roomTypes = new ArrayList<>();
        roomTypes.add("Standard");
        roomTypes.add("Deluxe");
        roomTypes.add("Executive");
        roomTypeComboBox = addComboBox("Select a Room Type: ", roomTypes.toArray());
        numOfRoomsField = addTextField("Enter Number of Rooms: ");
        JButton enterButton = addEnterButton();
        enterButton.addActionListener(_ -> onEnter());
    }

    @Override
    protected void onEnter() {
        Object selectedHotel = hotelComboBox.getSelectedItem();
        if (!(selectedHotel instanceof String hotelName)) {
            JOptionPane.showMessageDialog(this, "Please Select a Valid Hotel");
            return;
        }

        Hotel hotel = HRS.getHotel(hotelName);

        String roomTypeString = (String) roomTypeComboBox.getSelectedItem();
        int numOfRooms = Integer.parseInt(numOfRoomsField.getText());
        int roomType = 0;

        switch (roomTypeString) {
            case "Standard":
                roomType = 1;
                break;
                case "Deluxe":
                    roomType = 2;
                    break;
                case "Executive":
                    roomType = 3;
                    break;
            case null, default:
                    break;
            }
            JOptionPane.showMessageDialog(this, "Rooms " + hotel.addRooms(numOfRooms, roomType) + " Added");

    }
}
