package gui;

import model.Hotel;
import model.HotelReservationSystem;

import javax.swing.*;

/**
 * A form that allows users to add a premium rate to a given date for a selected hotel.
 * This form extends {@link InputForm} and provides validation for the inputs and updates the hotel with the new premium rate information.
 */

public class AddPremiumToDateForm extends InputForm{
    private ComboBox hotelComboBox;
    private JTextField premiumField;
    private JTextField dateField;

    /**
     * Constructs a new AddPremiumToDate with the given hotel reservation system and root frame.
     *
     * @param HRS  The hotel reservation system that this form will interact with.
     * @param root The main frame of the application.
     */

    public AddPremiumToDateForm(HotelReservationSystem HRS, JFrame root) {
        super(HRS, root);
    }

    /**
     * Returns the title for this form.
     *
     * @return The title of the form.
     */

    protected String getTitle() {
        return "Hotel Reservation System - Add Premium To Date";
    }

    /**
     * Adds the input fields to the form.
     */

    @Override
    protected void addInputFields(){
        hotelComboBox = addComboBox("Select a Hotel:", HRS.getHotelNames().toArray());
        dateField = addTextField("Enter Date: ");
        premiumField = addTextField("Enter Premium: ");
        JButton enterButton = addEnterButton();
        enterButton.addActionListener(_ -> onEnter());
    }

    /**
     * Handles the action performed when the "Enter" button is clicked.
     * This method gets the selected hotel, date, and premium rate,
     * validates the inputs, and updates the selected hotel with the new premium rate information.
     * Shows an appropriate message dialog for errors or successful updates.
     */


    @Override
    protected void onEnter() {
        Object selectedHotel = hotelComboBox.getSelectedItem();
        if (!(selectedHotel instanceof String hotelName)) {
            JOptionPane.showMessageDialog(this, "Please Select a Valid Hotel");
            return;
        }

        Hotel hotel = HRS.getHotel(hotelName);
        int date = Integer.parseInt(dateField.getText());
        double premium = Double.parseDouble(premiumField.getText());
        if(date < 1 || date > 31){
            JOptionPane.showMessageDialog(this, "Invalid Date");
        }else if(premium < 0.5 || premium > 1.5 ){
            JOptionPane.showMessageDialog(this, "Value Must Be Between 0.5 and 1.5");
        }else{
            hotel.addPremiumToDate(date, premium);
            JOptionPane.showMessageDialog(this,"Premium Added");

        }
        dateField.setText("");
        premiumField.setText("");
    }
}
