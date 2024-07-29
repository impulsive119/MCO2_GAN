package gui;

import javax.swing.*;

import model.HotelReservationSystem;
import model.Hotel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ViewAvailabilityOnDateForm extends InputForm{
    private ComboBox hotelComboBox;
    private JTextField dateField;
    private JButton enterButton;

    public ViewAvailabilityOnDateForm(HotelReservationSystem HRS) {
        super(HRS);
    }

    @Override
    protected void addInputFields(){
        hotelComboBox = addComboBox("Select a Hotel",  HRS.getHotelNames().toArray());
        dateField = addTextField("Enter Date: ");
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
