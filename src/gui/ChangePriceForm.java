package gui;

import model.Hotel;
import model.HotelReservationSystem;

import javax.swing.*;

public class ChangePriceForm extends InputForm{
    private JTextField priceField;
    private ComboBox hotelComboBox;

    public ChangePriceForm(HotelReservationSystem HRS, JFrame root) {
        super(HRS, root);
    }

    protected String getTitle() {
        return "Hotel Reservation System - Change Price";
    }

    @Override
    protected void addInputFields(){
        hotelComboBox = addComboBox("Select a Hotel", HRS.getHotelNames().toArray());
        priceField = addTextField("Enter New Price: ");
        JButton enterButton = addEnterButton();
        enterButton.addActionListener(_ -> onEnter());
    }

    @Override
    protected void onEnter() {
        Object selectedHotel = hotelComboBox.getSelectedItem();
        if (!(selectedHotel instanceof String hotelName)) {
            JOptionPane.showMessageDialog(this, "IPlease Select a Valid Hotel");
            return;
        }
        Hotel hotel = HRS.getHotelUsingName(hotelName);
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
