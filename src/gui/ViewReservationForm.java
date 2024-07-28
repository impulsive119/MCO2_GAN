package gui;

import model.Hotel;
import model.Reservation;
import model.Room;

import javax.swing.*;

public abstract class ViewReservationForm extends InputForm{
    private JTextField reservationNameField;

    @Override
    protected void addInputFields(){
        reservationNameField = addTextField("Select Reservation: ");
    }

    @Override
    protected void onEnter(Hotel hotel){
        String guestName = reservationNameField.getText();
        Reservation reservation = hotel.getReservation(guestName);
        JOptionPane.showMessageDialog(
                this,"Guest Name: " + guestName + "\n" +
                        "Hotel: " + reservation.getRoom().getHotel()+"\n" +
                        "Room Number: " + reservation.getRoom().getRoomNumber() + "\n" +
                        "Room Type: " + reservation.getRoom().getRoomType() + "\n" +
                        "Check-In Date: " + reservation.getCheckInDate() + "\n" +
                        "Check-Out Date: " + reservation.getCheckOutDate() + "\n" +
                        "Total Price: " + reservation.getTotalPrice() + "\n" +
                        "Discount Code: " + reservation.getDiscountCode());
    }
}