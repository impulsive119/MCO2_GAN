package gui;

import model.Hotel;
import model.HotelReservationSystem;
import model.Reservation;
import model.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public abstract class ViewReservationForm extends InputForm{
    private ComboBox hotelComboBox;
    private ComboBox reservationComboBox;
    private JButton enterButton;

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
        hotelComboBox = addComboBox("Select A Hotel", HRS.getHotels().toArray());
        reservationComboBox = addComboBox();
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

    @Override
    protected void onEnter(){
        Hotel hotel = (Hotel) hotelComboBox.getSelectedItem();
        String guestName = (String) reservationComboBox.getSelectedItem();
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