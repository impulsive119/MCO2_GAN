package gui;

import model.HotelReservationSystem;

import javax.swing.*;

/**
 * A form that allows users to create a hotel and add it to the Hotel Reservation System.
 * This form extends {@link InputForm} and provides validation for the inputs and updates the Hotel Reservation System with the new hotel.
 */

public class CreateHotelForm extends InputForm{
    private JTextField hotelNameField;

    /**
     * Constructs a new CreateHotelForm with the given hotel reservation system and root frame.
     *
     * @param HRS  The hotel reservation system that this form will interact with.
     * @param root The main frame of the application.
     */

    public CreateHotelForm(HotelReservationSystem HRS, JFrame root) {
        super(HRS, root);
    }

    /**
     * Returns the title for this form.
     *
     * @return The title of the form.
     */

    @Override
    protected String getTitle() {
        return "Hotel Reservation System - Create Hotel";
    }

    /**
     * Adds the input fields to the form.
     */

    @Override
    protected void addInputFields(){
        hotelNameField = addTextField("Enter Hotel Name: ");
        JButton enterButton = addEnterButton();
        enterButton.addActionListener(e -> onEnter());
    }

    /**
     * Handles the action performed when the "Enter" button is clicked.
     * This method validates the hotel name and creates a hotel using the
     * name and adds it to the Hotel Reservation System if it is valid.
     * Shows an appropriate message dialog for errors or successful updates.
     */

    @Override
    protected void onEnter(){
        String hotelName = hotelNameField.getText();
        if(hotelName.isEmpty()){
            JOptionPane.showMessageDialog(this,"Please Enter a Valid Name");
        }else if(HRS.createHotel(hotelName)){
            JOptionPane.showMessageDialog(this,"Hotel " + hotelName + " Added");
        }else{
            JOptionPane.showMessageDialog(this,"Hotel Name is Already Used");
        }
        hotelNameField.setText("");
    }
}
