package gui;

import model.Hotel;
import model.HotelReservationSystem;

import javax.swing.*;

public class ChangePriceForm extends InputForm{
    private JTextField priceField;
    private ComboBox hotelComboBox;

    public ChangePriceForm(HotelReservationSystem HRS) {
        super(HRS);
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
        String hotelName = (String) hotelComboBox.getSelectedItem();
        Hotel hotel = HRS.getHotel(hotelName);
        double price = Double.parseDouble(priceField.getText());
        if (price < 100) {
            JOptionPane.showMessageDialog(this, "Price Must Be Greater than 100.00");
        } else {
            hotel.changePrice(price);
            JOptionPane.showMessageDialog(this, "New Price: " + price);
        }
    }
}
