package gui;

import model.Hotel;
import model.HotelReservationSystem;

import javax.swing.*;
import java.util.ArrayList;

public abstract class ChangePriceForm extends InputForm{
    private JTextField priceField;
    private ComboBox hotelComboBox;

    @Override
    protected void addInputFields(HotelReservationSystem HRS){
        hotelComboBox = addComboBox("Select a Hotel", HRS.getHotelNames().toArray());
        priceField = addTextField("Enter New Price: ");
    }

    @Override
    protected void onEnter() {
        Hotel hotel = (Hotel) hotelComboBox.getSelectedItem();
        double price = Double.parseDouble(priceField.getText());
        if(price < 100){
            JOptionPane.showMessageDialog(this,"Price Must Be Greater than 100.00");
        }else{
            hotel.changePrice(price);
            JOptionPane.showMessageDialog(this, "New Price: " + price);
        }
    }
}
