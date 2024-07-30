package gui;

import model.Hotel;
import model.HotelReservationSystem;

import javax.swing.*;

/**
 * A form that allows users to view a hotel's high-level information.
 * This form extends {@link InputForm} and provides validation for the inputs and displays the overview of a selected hotel.
 */

public class HotelOverviewForm extends InputForm{
    private ComboBox hotelComboBox;

    /**
     * Constructs a new HotelOverviewForm with the given hotel reservation system and root frame.
     *
     * @param HRS  The hotel reservation system that this form will interact with.
     * @param root The main frame of the application.
     */

    public HotelOverviewForm(HotelReservationSystem HRS, JFrame root) {
        super(HRS, root);
    }

    /**
     * Returns the title for this form.
     *
     * @return The title of the form.
     */

    protected String getTitle() {
        return "Hotel Reservation System - Hotel Overview";
    }

    /**
     * Adds the input fields to the form.
     */

    @Override
    protected void addInputFields(){
        hotelComboBox = addComboBox("Select a Hotel",  HRS.getHotelNames().toArray());
        JButton enterButton = addEnterButton();
        enterButton.addActionListener(_ -> onEnter());
    }

    /**
     * Handles the action performed when the "Enter" button is clicked.
     * This method gets the selected hotel and displays its high-level information.
     * Shows an appropriate message dialog for errors or successful viewings.
     */

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
