package gui;

import model.Hotel;

import javax.swing.*;

public abstract class HotelNameForm extends InputForm{
    private JTextField hotelNameField;

    @Override
    protected void addInputFields(){
        hotelNameField = addTextField("Enter Hotel Name: ");
    }

    @Override
    protected void onEnter(Hotel hotel){
        String hotelName = hotelNameField.getText();

        if(hotel.setName(hotelName)) {
            String message = "Hotel " + hotelName + " Added";
            JOptionPane.showMessageDialog(this, message);
        }else{
            JOptionPane.showMessageDialog(this, "Invalid Hotel Name");
        }
    }
}
