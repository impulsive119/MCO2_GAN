package gui;

import model.Hotel;
import model.HotelReservationSystem;

import javax.swing.*;

public abstract class HotelOverviewForm extends InputForm{
    private ComboBox hotelComboBox;

    public HotelOverviewForm(HotelReservationSystem HRS, JFrame root) {
        super(HRS, root);
    }

    protected String getTitle() {
        return "Hotel Reservation System - Hotel Overview";
    }

    @Override
    protected void addInputFields(){
        hotelComboBox = addComboBox("Select a Hotel",  HRS.getHotelNames().toArray());
        JButton enterButton = addEnterButton();
        enterButton.addActionListener(_ -> onEnter());
    }

    @Override
    protected void onEnter(){
        Object selectedHotel = hotelComboBox.getSelectedItem();
        if (!(selectedHotel instanceof String hotelName)) {
            JOptionPane.showMessageDialog(this, "Please Select a Valid Hotel");
            return;
        }
        Hotel hotel = HRS.getHotel(hotelName);

        JOptionPane.showMessageDialog(this,
                "Hotel Name: "+ hotel.getName() + "\n" +
                        "Total Number Of Rooms: " + hotel.getNumberOfRooms() +"\n" +
                        "Number of Standard Rooms: " + hotel.getNumberOfStandardRooms()+ "\n" +
                        "Number of Deluxe Rooms: " + hotel.getNumberOfDeluxeRooms()+ "\n" +
                        "Number of Executive Rooms: " + hotel.getNumberOfExecutiveRooms()+ "\n" +
                        "Monthly Earnings: " + hotel.getMonthlyEarnings()+ "\n");

    }
}
