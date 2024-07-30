package gui;

import model.Hotel;
import model.HotelReservationSystem;

import javax.swing.*;

/**
 * A form that allows users to remove a hotel from the Hotel Reservation System.
 * This form extends {@link InputForm} and provides validation for the inputs and updates the system accordingly.
 */


public class RemoveHotelForm extends InputForm{
    private ComboBox hotelComboBox;

    /**
     * Constructs a new RemoveHotelForm with the given hotel reservation system and root frame.
     *
     * @param HRS  The hotel reservation system that this form will interact with.
     * @param root The main frame of the application.
     */

    public RemoveHotelForm(HotelReservationSystem HRS, JFrame root) {
        super(HRS, root);
    }

    /**
     * Returns the title for this form.
     *
     * @return The title of the form.
     */

    protected String getTitle() {
        return "Hotel Reservation System - Remove Hotel";
    }

    /**
     * Adds the input fields to the form.
     */

    @Override
    protected void addInputFields(){
        hotelComboBox = addComboBox("Select a Hotel:", HRS.getHotelNames().toArray());
        JButton enterButton = addEnterButton();
        enterButton.addActionListener(_ -> onEnter());
    }

    /**
     * Handles the action performed when the "Enter" button is clicked.
     * This method gets the selected hotel and removes it from the system.
     * Shows an appropriate message dialog for errors or successful updates.
     * Updates its own ComboBox accordingly.
     */

    @Override
    protected void onEnter(){
        Object selectedHotel = hotelComboBox.getSelectedItem();
        if (!(selectedHotel instanceof String hotelName)) {
            JOptionPane.showMessageDialog(this, "Please Select a Valid Hotel");
            return;
        }
        Hotel hotel = HRS.getHotel(hotelName);
        if(hotel != null){
            HRS.removeHotel(hotel);
            JOptionPane.showMessageDialog(this, "Hotel Removed");
            hotelComboBox.setItems(HRS.getHotelNames().toArray());
        }else{
            JOptionPane.showMessageDialog(this, "Please Select a Valid Hotel");
        }
    }
}
