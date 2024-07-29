package gui;

import model.Hotel;
import model.HotelReservationSystem;
import model.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class BookReservationForm extends InputForm{
    private JTextField guestNameField;
    private ComboBox hotelComboBox;
    private ComboBox roomComboBox;
    private JTextField checkInDateField;
    private JTextField checkOutDateField;
    private JTextField discountCodeField;

    public BookReservationForm(HotelReservationSystem HRS){
        super(HRS);
    }

    @Override
    protected void addInputFields() {
        hotelComboBox = addComboBox("Select a Hotel:",  HRS.getHotelNames().toArray());
        roomComboBox = addComboBox("Select a Room", null);
        roomComboBox.addActionListener(this::hotelComboBoxClicked);
        guestNameField = addTextField("Enter Guest Name: ");
        checkInDateField = addTextField("Enter Check-In Date: ");
        checkOutDateField = addTextField("Enter Check-Out Date: ");
        discountCodeField = addTextField("Enter Discount Code: ");
        JButton enterButton = addEnterButton();
        enterButton.addActionListener(_ -> onEnter());

    }

    private void hotelComboBoxClicked(ActionEvent e) {
        if (hotelComboBox.getSelectedItem() == ComboBox.NONE) {
            roomComboBox.removeAllItems();
        } else if(hotelComboBox.getSelectedItem()  != null){
            Hotel hotel = HRS.getHotel((String)hotelComboBox.getSelectedItem());
            List<Room> rooms = hotel.getRooms();
            roomComboBox.setItems(rooms.toArray());
        }
    }

    @Override
    protected void onEnter() {
        Room room = (Room) roomComboBox.getSelectedItem();
        String guestName = guestNameField.getText();
        int checkInDate  = Integer.parseInt(checkInDateField.getText());
        int checkOutDate  = Integer.parseInt(checkOutDateField.getText());
        String discountCode = discountCodeField.getText();

        if(checkInDate < 1 || checkInDate > 30){
            JOptionPane.showMessageDialog(this, "Invalid Check-In Date");
        }else if(checkOutDate > 31 || checkOutDate < 2){
            JOptionPane.showMessageDialog(this, "Invalid Check-Out Date");
        } else if (checkInDate >= checkOutDate) {
            JOptionPane.showMessageDialog(this, "Check-In Date must be before Check-Out Date");
        } else if (room != null && room.isReserved(checkInDate, checkOutDate)) {
            JOptionPane.showMessageDialog(this, "Room is Reserved on these Dates");
        } else if(room != null){
            room.addReservation( guestName, checkInDate, checkOutDate, discountCode);
            JOptionPane.showMessageDialog(this, "Reservation Booked");

        }
    }
}
