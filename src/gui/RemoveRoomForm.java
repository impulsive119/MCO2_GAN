package gui;

import model.Hotel;
import model.HotelReservationSystem;
import model.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public abstract class RemoveRoomForm extends InputForm{
    private ComboBox roomComboBox;
    private ComboBox hotelComboBox;
    private JButton enterButton;

    public RemoveRoomForm(HotelReservationSystem HRS) {
        super(HRS);
    }

    @Override
    protected void addInputFields(){
        hotelComboBox = addComboBox("Select a Hotel:", HRS.getHotels().toArray());
        roomComboBox = addComboBox("Select a Room", null);
        roomComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hotelComboBoxClicked(e);
            }
        });
        enterButton = addEnterButton();
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onEnter();
            }
        });
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
        Hotel hotel = (Hotel) hotelComboBox.getSelectedItem();
        Room chosenRoom = null;
        if(hotel != null){
            chosenRoom = hotel.selectRoom((int)roomComboBox.getSelectedItem());
        }

        if(chosenRoom != null) {
            hotel.removeRoom(chosenRoom);
            JOptionPane.showMessageDialog(this, "Room Removed");
        }else{
            JOptionPane.showMessageDialog(this, "Please Select a Valid Room");
        }
    }
}
