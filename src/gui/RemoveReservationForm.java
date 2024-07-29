package gui;

import model.Hotel;
import model.HotelReservationSystem;
import model.Reservation;
import model.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public abstract class RemoveReservationForm extends InputForm{
    private ComboBox reservationComboBox;
    private ComboBox hotelComboBox;
    private JButton enterButton;

    public RemoveReservationForm(HotelReservationSystem HRS) {
        super(HRS);
    }

    @Override
    protected void addInputFields(){
        hotelComboBox = addComboBox("Select a Hotel:", HRS.getHotels().toArray());
        reservationComboBox = addComboBox("Select a Reservation", null);
        reservationComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hotelComboBoxClicked(e);
            }
        });
        enterButton = addEnterButton();
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onEnter();
            }
        });
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
        Hotel hotel = (Hotel) hotelComboBox.getSelectedItem();
        Reservation chosenReservation = null;
        if(hotel != null){
            chosenReservation = hotel.selectReservation((int)reservationComboBox.getSelectedItem());
        }

        if(chosenReservation != null) {
            hotel.removeReservation(chosenReservation);
            JOptionPane.showMessageDialog(this, "Reservation Removed");
        }else{
            JOptionPane.showMessageDialog(this, "Please Select a Valid Reservation");
        }
    }
}
