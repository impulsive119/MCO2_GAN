package gui;

import model.Hotel;
import model.HotelReservationSystem;
import model.Reservation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public abstract class RemoveReservationForm extends InputForm{
    private ComboBox reservationComboBox;
    private ComboBox hotelComboBox;

    public RemoveReservationForm(HotelReservationSystem HRS, JFrame root) {
        super(HRS, root);
    }

    protected String getTitle() {
        return "Hotel Reservation System - Remove Reservation";
    }

    @Override
    protected void addInputFields(){
        hotelComboBox = addComboBox("Select a Hotel:", HRS.getHotelNames().toArray());
        reservationComboBox = addComboBox();
        reservationComboBox.addActionListener(this::hotelComboBoxClicked);
        JButton enterButton = addEnterButton();
        enterButton.addActionListener(_ -> onEnter());
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
