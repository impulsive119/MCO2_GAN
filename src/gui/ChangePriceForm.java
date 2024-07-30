package gui;

import model.Hotel;
import model.HotelReservationSystem;

import javax.swing.*;

/**
 * A form that allows users to change a selected hotel's base price.
 * This form extends {@link InputForm} and provides validation for the inputs and updates the hotel with the base price.
 */

public class ChangePriceForm extends InputForm{
    private JTextField priceField;
    private ComboBox hotelComboBox;

    /**
     * Constructs a new ChangePriceForm with the given hotel reservation system and root frame.
     *
     * @param HRS  The hotel reservation system that this form will interact with.
     * @param root The main frame of the application.
     */

    public ChangePriceForm(HotelReservationSystem HRS, JFrame root) {
        super(HRS, root);
    }

    /**
     * Returns the title for this form.
     *
     * @return The title of the form.
     */

    protected String getTitle() {
        return "Hotel Reservation System - Change Price";
    }

    /**
     * Adds the input fields to the form.
     */

    @Override
    protected void addInputFields(){
        hotelComboBox = addComboBox("Select a Hotel", HRS.getHotelNames().toArray());
        priceField = addTextField("Enter New Price: ");
        JButton enterButton = addEnterButton();
        enterButton.addActionListener(_ -> onEnter());
    }

    /**
     * Handles the action performed when the "Enter" button is clicked.
     * This method gets the selected hotel and new price,
     * validates the inputs, and updates the room with the price information.
     * Shows an appropriate message dialog for errors or successful updates.
     */

    @Override
    protected void onEnter() {
        Object selectedHotel = hotelComboBox.getSelectedItem();
        if (!(selectedHotel instanceof String hotelName)) {
            JOptionPane.showMessageDialog(this, "IPlease Select a Valid Hotel");
            return;
        }
        Hotel hotel = HRS.getHotel(hotelName);
        double price = Double.parseDouble(priceField.getText());
        if (price < 100) {
            JOptionPane.showMessageDialog(this, "Price Must Be Greater than 100.00");
        } else if (!hotel.getReservations().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hotel Must Have No Active Reservations");
        } else {
            hotel.setPrice(price);
            JOptionPane.showMessageDialog(this, "New Price: " + price);
        }
        priceField.setText("");
    }
}
