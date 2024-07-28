package gui;

import model.Hotel;
import model.HotelReservationSystem;
import model.Reservation;
import model.Room;

import javax.swing.*;

public abstract class ViewReservationForm extends InputForm{
    private ComboBox hotelComboBox;
    private JTextField reservationNameField;

    @Override
    protected void addInputFields(HotelReservationSystem HRS){
        hotelComboBox = addComboBox("Select A Hotel", HRS.getHotels().toArray());
        reservationNameField = addTextField("Select Reservation: ");
    }

    @Override
    protected void onEnter(){
        Hotel hotel = (Hotel) hotelComboBox.getSelectedItem();
        String guestName = reservationNameField.getText();
        if(hotel != null) {
            Reservation reservation = hotel.getReservation(guestName);
            JOptionPane.showMessageDialog(
                    this, "Guest Name: " + guestName + "\n" +
                            "Hotel: " + reservation.getRoom().getHotel() + "\n" +
                            "Room Number: " + reservation.getRoom().getRoomNumber() + "\n" +
                            "Room Type: " + reservation.getRoom().getRoomType() + "\n" +
                            "Check-In Date: " + reservation.getCheckInDate() + "\n" +
                            "Check-Out Date: " + reservation.getCheckOutDate() + "\n" +
                            "Total Price: " + reservation.getTotalPrice() + "\n" +
                            "Discount Code: " + reservation.getDiscountCode());
        }
    }
}