package gui;

import model.Hotel;
import model.HotelReservationSystem;
import model.Reservation;
import model.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public abstract class ViewReservationForm extends InputForm{
    private ComboBox hotelComboBox;
    private ComboBox reservationComboBox;

    public ViewReservationForm(HotelReservationSystem HRS) {
        super(HRS);
    }

    private void hotelComboBoxClicked(ActionEvent e) {
        if (hotelComboBox.getSelectedItem() == ComboBox.NONE) {
            reservationComboBox.removeAllItems();
        } else if(hotelComboBox.getSelectedItem()  != null){
            Hotel  hotel = (Hotel) hotelComboBox.getSelectedItem();
            List<Room> rooms = hotel.getRooms();
            reservationComboBox.setItems(rooms.toArray());
        }
    }

    @Override
    protected void addInputFields(HotelReservationSystem HRS){
        hotelComboBox = addComboBox("Select a Hotel:", HRS.getHotelNames().toArray());
        reservationComboBox = addComboBox();
        reservationComboBox.addActionListener(this::hotelComboBoxClicked);
        JButton enterButton = addEnterButton();
        enterButton.addActionListener(_ -> onEnter());
    }

    @Override
    protected void onEnter(){
        String hotelName = (String) hotelComboBox.getSelectedItem();
        String guestName = (String) reservationComboBox.getSelectedItem();
        Hotel hotel = HRS.getHotel(hotelName);
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