package gui;

import model.Hotel;
import model.HotelReservationSystem;

import javax.swing.*;

public abstract class AddRoomsForm extends InputForm{
    private ComboBox roomTypeComboBox;
    private JTextField numOfRoomsField;
    private ComboBox hotelComboBox;

    public AddRoomsForm(HotelReservationSystem HRS){
        super(HRS);
    }

    @Override
    protected void addInputFields(){
        String[] roomTypes = new String[]{"Standard", "Deluxe", "Executive"};
        hotelComboBox = addComboBox("Select a Hotel:",  HRS.getHotelNames().toArray());
        roomTypeComboBox = addComboBox("Select a Room Type: ", roomTypes);
        numOfRoomsField = addTextField("Enter Number of Rooms: ");
        JButton enterButton = addEnterButton();
        enterButton.addActionListener(_ -> onEnter());
    }

    @Override
    protected void onEnter() {
        String hotelName = (String) hotelComboBox.getSelectedItem();
        Hotel hotel = HRS.getHotel(hotelName);
        String roomType = (String) roomTypeComboBox.getSelectedItem();
        int numOfRooms = Integer.parseInt(numOfRoomsField.getText());
        int room = 0;
        switch (roomType) {
            case "Standard":
                room = 1;
                break;
                case "Deluxe":
                    room = 2;
                    break;
                case "Executive":
                    room = 3;
                    break;
            case null, default:
                    break;
            }
            JOptionPane.showMessageDialog(this, hotel.addRooms(numOfRooms, room));

    }
}
