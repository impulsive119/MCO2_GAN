package gui;

import model.Hotel;
import model.HotelReservationSystem;
import model.Reservation;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public abstract class RemoveReservationForm extends InputForm{
    private ComboBox reservationComboBox;
    private ComboBox hotelComboBox;

    public RemoveReservationForm(HotelReservationSystem HRS) {
        super(HRS);
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
        if (hotelComboBox.getSelectedItem() == ComboBox.NONE) {
            reservationComboBox.removeAllItems();
        } else if(hotelComboBox.getSelectedItem()  != null){
            Hotel  hotel = (Hotel) hotelComboBox.getSelectedItem();
            List<Reservation> reservations = hotel.getReservations();
            reservationComboBox.setItems(reservations.toArray());
        }
    }

    @Override
    protected void onEnter(){
        String hotelName = (String) hotelComboBox.getSelectedItem();
        Hotel hotel = HRS.getHotel(hotelName);
        Reservation chosenReservation = hotel.selectReservation((int)reservationComboBox.getSelectedItem());

        if(chosenReservation != null) {
            hotel.removeReservation(chosenReservation);
            JOptionPane.showMessageDialog(this, "Reservation Removed");
        }else{
            JOptionPane.showMessageDialog(this, "Please Select a Valid Reservation");
        }
    }
}
