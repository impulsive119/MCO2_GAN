package gui;

import javax.swing.*;

import model.HotelReservationSystem;
import model.Hotel;

import java.util.ArrayList;

public abstract class ViewAvailabilityOnDateForm extends InputForm{
    private ComboBox hotelComboBox;
    private JTextField dateField;

    @Override
    protected void addInputFields(HotelReservationSystem HRS){
        hotelComboBox = addComboBox("Select a Hotel",  HRS.getHotelNames().toArray());
        dateField = addTextField("Enter Date: ");
    }

    @Override
    protected void onEnter(){
        Hotel hotel = (Hotel)hotelComboBox.getSelectedItem();
        int date = Integer.parseInt(dateField.getText());

        if(date < 1 || date > 31) {
            JOptionPane.showMessageDialog(this, "Invalid Hotel Name");
        }else if (hotel != null) {
                JOptionPane.showMessageDialog(this,
                        "Available Rooms:"+ "\n" +
                                hotel.getAvailableRoomsOnDate(date) +"\n" +
                                "Reserved Rooms: " + "\n" +
                                hotel.getReservedRoomsOnDate(date));

        }
    }
}
