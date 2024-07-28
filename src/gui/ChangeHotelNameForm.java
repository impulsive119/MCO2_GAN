package gui;

import model.Hotel;
import model.HotelReservationSystem;

import javax.swing.*;

public abstract class ChangeHotelNameForm extends InputForm{
    private JTextField hotelNameField;
    private ComboBox hotelComboBox;

    @Override
    protected void addInputFields(HotelReservationSystem HRS){
        hotelComboBox = addComboBox("Select a Hotel:", HRS.getHotels().toArray());
        hotelNameField = addTextField("Enter Hotel Name: ");
    }

    @Override
    protected void onEnter(){
        Hotel hotel = (Hotel) hotelComboBox.getSelectedItem();
        String hotelName = hotelNameField.getText();

        if(hotel != null && hotel.setName(hotelName)) {
            String message = "Hotel " + hotelName + " Added";
            JOptionPane.showMessageDialog(this, message);
        }else{
            JOptionPane.showMessageDialog(this, "Invalid Hotel Name");
        }
    }
}
