package gui;

import model.Hotel;
import model.HotelReservationSystem;
import model.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class BookReservationForm extends InputForm{
    private JTextField guestNameField;
    private ComboBox hotelComboBox;
    private ComboBox roomComboBox;
    private JTextField checkInDateField;
    private JTextField checkOutDateField;
    private JTextField discountCodeField;

    public BookReservationForm(HotelReservationSystem HRS, JFrame root) {
        super(HRS, root);
    }

    @Override
    protected String getTitle() {
        return "Hotel Reservation System - Book Reservation";
    }

    @Override
    protected void addInputFields() {
        hotelComboBox = addComboBox("Select a Hotel:",  HRS.getHotelNames().toArray());
        roomComboBox = addComboBox("Select a Room", null);
        hotelComboBox.addActionListener(this::hotelComboBoxClicked);
        guestNameField = addTextField("Enter Guest Name: ");
        checkInDateField = addTextField("Enter Check-In Date: ");
        checkOutDateField = addTextField("Enter Check-Out Date: ");
        discountCodeField = addTextField("Enter Discount Code: ");
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
        Room room = hotel.getRoom(roomNumber);
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
        } else if (room.isReserved(checkInDate, checkOutDate)) {
            JOptionPane.showMessageDialog(this, "Room is Reserved on these Dates");
        } else{
            room.addReservation(guestName, checkInDate, checkOutDate, discountCode);
            JOptionPane.showMessageDialog(this, "Reservation Booked");
        }
        guestNameField.setText("");
        checkInDateField.setText("");
        checkOutDateField.setText("");
        discountCodeField.setText("");
    }
}
