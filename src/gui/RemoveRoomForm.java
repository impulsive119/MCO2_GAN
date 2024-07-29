package gui;

import model.Hotel;
import model.HotelReservationSystem;
import model.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public abstract class RemoveRoomForm extends InputForm{
    private ComboBox roomComboBox;
    private ComboBox hotelComboBox;

    public RemoveRoomForm(HotelReservationSystem HRS) {
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
        Room chosenRoom = hotel.selectRoom((int)roomComboBox.getSelectedItem());

        if(chosenRoom != null) {
            hotel.removeRoom(chosenRoom);
            JOptionPane.showMessageDialog(this, "Room Removed");
        }else{
            JOptionPane.showMessageDialog(this, "Please Select a Valid Room");
        }
    }
}
