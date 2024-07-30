package gui;

import model.Hotel;
import model.HotelReservationSystem;
import model.Reservation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * A form that allows users to view a given reservation's information.
 * This form extends {@link InputForm} and provides validation for the inputs and views selected reservations.
 */

public class ViewReservationForm extends InputForm{
    private ComboBox hotelComboBox;
    private ComboBox reservationComboBox;

    /**
     * Constructs a new ViewReservationForm with the given hotel reservation system and root frame.
     *
     * @param HRS  The hotel reservation system that this form will interact with.
     * @param root The main frame of the application.
     */

    public ViewReservationForm(HotelReservationSystem HRS, JFrame root) {
        super(HRS, root);
    }

    /**
     * Returns the title for this form.
     *
     * @return The title of the form.
     */

    protected String getTitle() {
        return "Hotel Reservation System - View Reservation";
    }

    /**
     * Updates reservationComboBox according to the selected hotel in HotelComboBox.
     */

    private void hotelComboBoxClicked(ActionEvent e) {
        if (hotelComboBox.getSelectedItem() == null || hotelComboBox.getSelectedItem() == ComboBox.NONE) {
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

    /**
     * Adds the input fields to the form.
     */

    @Override
    protected void addInputFields(){
        hotelComboBox = addComboBox("Select a Hotel:", HRS.getHotelNames().toArray());
        reservationComboBox = addComboBox("Select a Reservation", null);
        hotelComboBox.addActionListener(this::hotelComboBoxClicked);
        JButton enterButton = addEnterButton();
        enterButton.addActionListener(_ -> onEnter());
    }

    /**
     * Handles the action performed when the "Enter" button is clicked.
     * This method gets the selected hotel and reservation, validates the information,
     * then displays the reservation information if the inputs are valid.
     * Shows an appropriate message dialog for errors or successful viewings.
     */

    @Override
    protected void onEnter(){
        Object selectedHotel = hotelComboBox.getSelectedItem();
        if (!(selectedHotel instanceof String hotelName)) {
            JOptionPane.showMessageDialog(this, "Please Select a Valid Hotel");
            return;
        }
        Hotel hotel = HRS.getHotel(hotelName);
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