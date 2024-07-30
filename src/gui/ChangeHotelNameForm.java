package gui;

import model.Hotel;
import model.HotelReservationSystem;

import javax.swing.*;

/**
 * A form that allows users to change a selected hotel's name.
 * This form extends {@link InputForm} and provides validation for the inputs and updates the hotel with the hotel name.
 */

public class ChangeHotelNameForm extends InputForm{
    private JTextField hotelNameField;
    private ComboBox hotelComboBox;

    /**
     * Constructs a new ChangeHotelNameForm with the given hotel reservation system and root frame.
     *
     * @param HRS  The hotel reservation system that this form will interact with.
     * @param root The main frame of the application.
     */


    public ChangeHotelNameForm(HotelReservationSystem HRS, JFrame root) {
        super(HRS, root);
    }

    /**
     * Returns the title for this form.
     *
     * @return The title of the form.
     */

    protected String getTitle() {
        return "Hotel Reservation System - Change Hotel Name";
    }

    /**
     * Adds the input fields to the form.
     */

    @Override
    protected void addInputFields(){
        hotelComboBox = addComboBox("Select a Hotel:", HRS.getHotelNames().toArray());
        hotelNameField = addTextField("Enter Hotel Name: ");
        JButton enterButton = addEnterButton();
        enterButton.addActionListener(_ -> onEnter());
    }

    /**
     * Handles the action performed when the "Enter" button is clicked.
     * This method gets the selected hotel and new hotel name,
     * validates the inputs, and updates the room with the new hotel name.
     * Shows an appropriate message dialog for errors or successful updates.
     */

    @Override
    protected void onEnter(){
        Object selectedHotel = hotelComboBox.getSelectedItem();
        if (!(selectedHotel instanceof String hotelName)) {
            JOptionPane.showMessageDialog(this, "Please Select a Valid Hotel");
            return;
        }

        Hotel hotel = HRS.getHotel(hotelName);
        String newHotelName = hotelNameField.getText();

        if(hotel != null && hotel.setName(newHotelName)) {
            String message = "Hotel " + newHotelName + " Added";
            JOptionPane.showMessageDialog(this, message);
            hotelComboBox.setItems(HRS.getHotelNames().toArray());
        }else{
            JOptionPane.showMessageDialog(this, "Invalid Hotel Name");
        }
        hotelNameField.setText("");
    }
}
