package gui;

import model.Hotel;
import model.HotelReservationSystem;
import model.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public abstract class RemoveRoomForm extends InputForm{
    private ComboBox roomComboBox;
    private ComboBox hotelComboBox;

    public RemoveRoomForm(HotelReservationSystem HRS, JFrame root) {
        super(HRS, root);
    }

    protected String getTitle() {
        return "Hotel Reservation System - Remove Room";
    }

    @Override
    protected void addInputFields(){
        hotelComboBox = addComboBox("Select a Hotel:", HRS.getHotelNames().toArray());
        roomComboBox = addComboBox("Select a Room", null);
        hotelComboBox.addActionListener(this::hotelComboBoxClicked);
        JButton enterButton = addEnterButton();
        enterButton.addActionListener(_ -> onEnter());
    }

    private void hotelComboBoxClicked(ActionEvent e) {
        if (hotelComboBox.getSelectedItem() == null || hotelComboBox.getSelectedItem().equals("NONE")) {
            roomComboBox.removeAllItems();
        } else {
            Hotel hotel = HRS.getHotel((String) hotelComboBox.getSelectedItem());
            if (hotel != null) {
                ArrayList<Integer> rooms = new ArrayList<>();
                for (Room room : hotel.getRooms()) {
                    rooms.add(room.getRoomNumber());
                }
                roomComboBox.removeAllItems();
                for (Integer roomNumber : rooms) {
                    roomComboBox.addItem(roomNumber);
                }
            }
        }
    }

    @Override
    protected void onEnter() {
        Object selectedHotel = hotelComboBox.getSelectedItem();
        if (!(selectedHotel instanceof String hotelName)) {
            JOptionPane.showMessageDialog(this, "Please Select a Valid Hotel");
            return;
        }

        Hotel hotel = HRS.getHotel(hotelName);

        Object selectedRoom = roomComboBox.getSelectedItem();
        if (!(selectedRoom instanceof Integer roomNumber)) {
            JOptionPane.showMessageDialog(this, "Please Select a Valid Room");
            return;
        }

        Room chosenRoom = hotel.selectRoom(roomNumber);
        hotel.removeRoom(chosenRoom);
        JOptionPane.showMessageDialog(this, "Room Removed");

        ArrayList<Room> rooms = new ArrayList<>(hotel.getRooms());
        roomComboBox.removeAllItems();
        for (Room room : rooms) {
            roomComboBox.addItem(room.getRoomNumber());
        }
    }
}
