package gui;

import javax.swing.*;

import model.HotelReservationSystem;
import model.Hotel;

public abstract class ViewAvailabilityOnDateForm extends InputForm{
    private ComboBox hotelComboBox;
    private JTextField dateField;

    public ViewAvailabilityOnDateForm(HotelReservationSystem HRS, JFrame root) {
        super(HRS, root);
    }

    protected String getTitle() {
        return "Hotel Reservation System - View Availability On Date";
    }

    @Override
    protected void addInputFields(){
        hotelComboBox = addComboBox("Select a Hotel",  HRS.getHotelNames().toArray());
        dateField = addTextField("Enter Date: ");
        JButton enterButton = addEnterButton();
        enterButton.addActionListener(_ -> onEnter());
    }

    @Override
    protected void onEnter(){
        Object selectedHotel = hotelComboBox.getSelectedItem();
        if (!(selectedHotel instanceof String hotelName)) {
            JOptionPane.showMessageDialog(this, "Invalid hotel selection");
            return;
        }
        Hotel hotel = HRS.getHotel(hotelName);
        int date = Integer.parseInt(dateField.getText());

        if(hotel == null){
            JOptionPane.showMessageDialog(this, "Please Select a Valid Hotel");
            return;
        }

        if(date < 1 || date > 31) {
            JOptionPane.showMessageDialog(this, "Invalid Date");
        }else {
            JOptionPane.showMessageDialog(this,
                    "Available Rooms:"+ "\n" +
                            hotel.getAvailableRoomsOnDate(date) +"\n" +
                            "Reserved Rooms: " + "\n" +
                            hotel.getReservedRoomsOnDate(date));

        }
        dateField.setText("");
    }
}
