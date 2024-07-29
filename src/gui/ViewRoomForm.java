package gui;

import model.HotelReservationSystem;
import model.Hotel;
import model.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public abstract class ViewRoomForm extends InputForm{
    private ComboBox roomComboBox;
    private ComboBox hotelComboBox;

    public ViewRoomForm(HotelReservationSystem HRS) {
        super(HRS);
    }

    @Override
    protected void addInputFields(){
        hotelComboBox = addComboBox("Select a Hotel:", HRS.getHotelNames().toArray());
        roomComboBox = addComboBox();
        roomComboBox.addActionListener(this::hotelComboBoxClicked);
        JButton enterButton = addEnterButton();
        enterButton.addActionListener(_ -> onEnter());
    }

    private void hotelComboBoxClicked(ActionEvent e) {
        if (hotelComboBox.getSelectedItem() == ComboBox.NONE) {
            roomComboBox.removeAllItems();
        } else if(hotelComboBox.getSelectedItem()  != null){
            Hotel  hotel = (Hotel) hotelComboBox.getSelectedItem();
            List<Room> rooms = hotel.getRooms();
            roomComboBox.setItems(rooms.toArray());
        }
    }

    @Override
    protected void onEnter(){
        String hotelName = (String) hotelComboBox.getSelectedItem();
        Hotel hotel = HRS.getHotel(hotelName);
        int roomNumber= (int) roomComboBox.getSelectedItem();

        Room chosenRoom = null;
        for (Room room : hotel.getRooms()) {
            if (room.getRoomNumber() == roomNumber) chosenRoom = room;
        }
        if (chosenRoom != null) {
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
}
