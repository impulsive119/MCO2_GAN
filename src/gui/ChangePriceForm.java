package gui;

import model.Hotel;
import model.HotelReservationSystem;

import javax.swing.*;

public class ChangePriceForm extends InputForm{
    private JTextField priceField;
    private ComboBox hotelComboBox;
    private JButton enterButton;

    public ChangePriceForm(HotelReservationSystem HRS) {
        super(HRS);
    }

    @Override
    protected void addInputFields(){
        hotelComboBox = addComboBox("Select a Hotel", HRS.getHotelNames().toArray());
        priceField = addTextField("Enter New Price: ");
        enterButton = addEnterButton();
    }

    @Override
    protected void onEnter() {
        Hotel hotel = (Hotel) hotelComboBox.getSelectedItem();
        double price = Double.parseDouble(priceField.getText());
        if(hotel != null) {
            if (price < 100) {
                JOptionPane.showMessageDialog(this, "Price Must Be Greater than 100.00");
            } else {
                hotel.changePrice(price);
                JOptionPane.showMessageDialog(this, "New Price: " + price);
            }
        }
    }
}
