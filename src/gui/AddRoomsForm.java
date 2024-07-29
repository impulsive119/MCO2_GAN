package gui;

import model.Hotel;
import model.HotelReservationSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AddRoomsForm extends InputForm{
    private ComboBox roomTypeComboBox;
    private JTextField numOfRoomsField;
    private ComboBox hotelComboBox;
    private JButton enterButton;

    public AddRoomsForm(HotelReservationSystem HRS){
        super(HRS);
    }

    @Override
    protected void addInputFields(){
        String[] roomTypes = new String[]{"Standard", "Deluxe", "Executive"};
        hotelComboBox = addComboBox("Select a Hotel:",  HRS.getHotelNames().toArray());
        roomTypeComboBox = addComboBox("Select a Room Type: ", roomTypes);
        numOfRoomsField = addTextField("Enter Number of Rooms: ");
        enterButton = addEnterButton();
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onEnter();
            }
        });
    }

    @Override
    protected void onEnter() {
        Hotel hotel = (Hotel) hotelComboBox.getSelectedItem();
        String roomType = (String) roomTypeComboBox.getSelectedItem();
        int numOfRooms = Integer.parseInt(numOfRoomsField.getText());
        int room = 0;
        if (roomType != null) {
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
                default:
                    break;
            }
        }
        if(hotel != null) {
            JOptionPane.showMessageDialog(this, hotel.addRooms(numOfRooms, room));
        }
    }
}
