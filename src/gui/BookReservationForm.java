package gui;

import model.Hotel;
import model.Room;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public abstract class BookReservationForm extends InputForm{
    private JTextField guestNameField;
    private JTextField roomNumberField;
    private JTextField checkInDateField;
    private JTextField checkOutDateField;
    private JTextField discountCodeField;

    @Override
    protected void addInputFields(){
        guestNameField = addTextField("Enter Guest Name: ");
        roomNumberField = addTextField("Enter Room Number: ");
        checkInDateField = addTextField("Enter Check-In Date: ");
        checkOutDateField = addTextField("Enter Check-Out Date: ");
        discountCodeField = addTextField("Enter Discount Code: ");
    }

    @Override
    protected void onEnter(Hotel hotel) {
        String guestName = guestNameField.getText();
        int roomNumber  = Integer.parseInt(roomNumberField.getText());
        int checkInDate  = Integer.parseInt(checkInDateField.getText());
        int checkOutDate  = Integer.parseInt(checkOutDateField.getText());
        String discountCode = discountCodeField.getText();

        Room room = hotel.selectRoom(roomNumber);

        if(room == null){
            JOptionPane.showMessageDialog(this, "Invalid Room");
        }else if(checkInDate < 1 || checkInDate > 30){
            JOptionPane.showMessageDialog(this, "Invalid Check-In Date");
        }else if(checkOutDate > 31 || checkOutDate < 2){
            JOptionPane.showMessageDialog(this, "Invalid Check-Out Date");
        } else if (checkInDate >= checkOutDate) {
            JOptionPane.showMessageDialog(this, "Check-In Date must be before Check-Out Date");
        } else if (room.isReserved(checkInDate, checkOutDate)) {
            JOptionPane.showMessageDialog(this, "Room is Reserved on these Dates");
        } else{
            room.addReservation( guestName, checkInDate, checkOutDate, discountCode);
        }
    }
}
