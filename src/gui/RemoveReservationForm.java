package gui;

import model.Hotel;
import model.HotelReservationSystem;
import model.Reservation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * A form that allows users to remove a reservation from a selected hotel.
 * This form extends {@link InputForm} and provides validation for the inputs and updates the system accordingly.
 */

public class RemoveReservationForm extends InputForm{
    private ComboBox reservationComboBox;
    private ComboBox hotelComboBox;

    /**
     * Constructs a new RemoveReservationForm with the given hotel reservation system and root frame.
     *
     * @param HRS  The hotel reservation system that this form will interact with.
     * @param root The main frame of the application.
     */

    public RemoveReservationForm(HotelReservationSystem HRS, JFrame root) {
        super(HRS, root);
    }

    /**
     * Returns the title for this form.
     *
     * @return The title of the form.
     */

    protected String getTitle() {
        return "Hotel Reservation System - Remove Reservation";
    }

    /**
     * Adds the input fields to the form.
     */

    @Override
    protected void addInputFields(){
        hotelComboBox = addComboBox("Select a Hotel:", HRS.getHotelNames().toArray());
        reservationComboBox = addComboBox();
        reservationComboBox.addActionListener(this::hotelComboBoxClicked);
        JButton enterButton = addEnterButton();
        enterButton.addActionListener(_ -> onEnter());
    }

    /**
     * Updates reservationComboBox based on the selected hotel in hotelComboBox
     */

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

    /**
     * Handles the action performed when the "Enter" button is clicked.
     * This method gets the selected hotel and reservation info, validates it,
     * then removes the reservation from the hotel if it is valid.
     * Shows an appropriate message dialog for errors or successful updates.
     * Updates its own ComboBox accordingly.
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
        Reservation chosenReservation = hotel.getReservation(guestName);
        hotel.removeReservation(chosenReservation);
        JOptionPane.showMessageDialog(this, "Reservation Removed");
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
