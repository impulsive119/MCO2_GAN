package gui;

import model.HotelReservationSystem;
import model.Hotel;
import model.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public abstract class ViewRoomForm extends InputForm{
    private ComboBox roomComboBox;
    private ComboBox hotelComboBox;

    public ViewRoomForm(HotelReservationSystem HRS) {
        super(HRS);
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
    protected void onEnter(){
        String hotelName = (String) hotelComboBox.getSelectedItem();
        Hotel hotel = HRS.getHotel(hotelName);
        int roomNumber= (int) roomComboBox.getSelectedItem();
        if(hotel == null){
            JOptionPane.showMessageDialog(this, "Please Select a Valid Hotel");
            return;
        }
        Room chosenRoom = null;
        for (Room room : hotel.getRooms()) {
            if (room.getRoomNumber() == roomNumber) chosenRoom = room;
        }
        if(chosenRoom == null){
            JOptionPane.showMessageDialog(this, "Please Select a Valid Hotel");
            return;
        }
        JOptionPane.showMessageDialog(
                this, "Room Number: " + roomNumber + "\n" +
                        "Room Type: " + chosenRoom.getRoomType() + "\n" +
                        "Room Price: " + chosenRoom.getPrice() + "\n" +
                        "Room Type: " + chosenRoom.getRoomType() + "\n" +
                        "Days Room is Available: " + "\n" +
                        chosenRoom.getAvailableDates() + "\n" +
                        "Days Room is Reserved: " + "\n" +
                        chosenRoom.getReservedDates());

    }
}
