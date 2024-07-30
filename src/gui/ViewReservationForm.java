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

    public ViewReservationForm(HotelReservationSystem HRS, JFrame root) {
        super(HRS, root);
    }

    protected String getTitle() {
        return "Hotel Reservation System - View Reservation";
    }

    private void hotelComboBoxClicked(ActionEvent e) {
        if (hotelComboBox.getSelectedItem() == null || hotelComboBox.getSelectedItem() == ComboBox.NONE) {
            reservationComboBox.removeAllItems();
        } else {
            Hotel hotel = HRS.getHotelUsingName((String) hotelComboBox.getSelectedItem());
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
    protected void addInputFields(){
        hotelComboBox = addComboBox("Select a Hotel:", HRS.getHotelNames().toArray());
        reservationComboBox = addComboBox("Select a Reservation", null);
        hotelComboBox.addActionListener(this::hotelComboBoxClicked);
        JButton enterButton = addEnterButton();
        enterButton.addActionListener(_ -> onEnter());
    }

    @Override
    protected void onEnter(){
        Object selectedHotel = hotelComboBox.getSelectedItem();
        if (!(selectedHotel instanceof String hotelName)) {
            JOptionPane.showMessageDialog(this, "Please Select a Valid Hotel");
            return;
        }
        Hotel hotel = HRS.getHotelUsingName(hotelName);
        Object selectedReservation = reservationComboBox.getSelectedItem();
        if (!(selectedReservation instanceof String guestName)) {
            JOptionPane.showMessageDialog(this, "Please Select a Valid Reservation");
            return;
        }
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