package gui;

import model.Hotel;
import model.HotelReservationSystem;
import model.Reservation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public abstract class ViewReservationForm extends InputForm{
    private ComboBox hotelComboBox;
    private ComboBox reservationComboBox;

    public ViewReservationForm(HotelReservationSystem HRS) {
        super(HRS);
    }

    private void hotelComboBoxClicked(ActionEvent e) {
        if (hotelComboBox.getSelectedItem() == null || hotelComboBox.getSelectedItem().equals("NONE")) {
            reservationComboBox.removeAllItems();
        } else {
            Hotel hotel = HRS.getHotel((String) hotelComboBox.getSelectedItem());
            if (hotel != null) {
                ArrayList<String> reservations = new ArrayList<>();
                for (Reservation reservation : hotel.getReservations()) {
                    reservations.add(reservation.getGuestName());
                }
                reservationComboBox.removeAllItems();
                for (String reservation : reservations) {
                    reservationComboBox.addItem(reservation);
                }
            }
        }
    }

    @Override
    protected void addInputFields(HotelReservationSystem HRS){
        hotelComboBox = addComboBox("Select a Hotel:", HRS.getHotelNames().toArray());
        reservationComboBox = addComboBox("Select a Reservation", null);
        hotelComboBox.addActionListener(this::hotelComboBoxClicked);
        JButton enterButton = addEnterButton();
        enterButton.addActionListener(_ -> onEnter());
    }

    @Override
    protected void onEnter(){
        String hotelName = (String) hotelComboBox.getSelectedItem();
        String guestName = (String) reservationComboBox.getSelectedItem();
        Hotel hotel = HRS.getHotel(hotelName);
        Reservation reservation = hotel.getReservation(guestName);
        if(reservation == null){
            JOptionPane.showMessageDialog(this, "Please Select a Valid Reservation");
            return;
        }
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