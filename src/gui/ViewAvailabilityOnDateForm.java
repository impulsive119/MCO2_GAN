package gui;

import javax.swing.*;

import model.HotelReservationSystem;
import model.Hotel;

/**
 * A form that allows users to view the available and reserved rooms on a given date.
 * This form extends {@link InputForm} and provides validation for the inputs and views room availability on selected dates.
 */

public class ViewAvailabilityOnDateForm extends InputForm{
    private ComboBox hotelComboBox;
    private JTextField dateField;

    /**
     * Constructs a new ViewAvailabilityForm with the given hotel reservation system and root frame.
     *
     * @param HRS  The hotel reservation system that this form will interact with.
     * @param root The main frame of the application.
     */

    public ViewAvailabilityOnDateForm(HotelReservationSystem HRS, JFrame root) {
        super(HRS, root);
    }

    /**
     * Returns the title for this form.
     *
     * @return The title of the form.
     */

    protected String getTitle() {
        return "Hotel Reservation System - View Availability On Date";
    }

    /**
     * Adds the input fields to the form.
     */

    @Override
    protected void addInputFields(){
        hotelComboBox = addComboBox("Select a Hotel",  HRS.getHotelNames().toArray());
        dateField = addTextField("Enter Date: ");
        JButton enterButton = addEnterButton();
        enterButton.addActionListener(e -> onEnter());
    }

    /**
     * Handles the action performed when the "Enter" button is clicked.
     * This method gets the selected hotel and date, validates the information,
     * then displays the available and reserved rooms on the date if the inputs are valid.
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
        int date = Integer.parseInt(dateField.getText());

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
