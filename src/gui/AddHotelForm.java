package gui;

import model.Hotel;
import model.HotelReservationSystem;

import javax.swing.*;

public abstract class AddHotelForm extends InputForm{
    private JTextField hotelNameField;

    @Override
    protected void addInputFields(){
        hotelNameField = addTextField("Enter Hotel Name: ");
    }

    @Override
    protected void onEnter(HotelReservationSystem HRS){
        String hotelName = hotelNameField.getText();
        if(HRS.createHotel(hotelName)){
            JOptionPane.showMessageDialog(this,"Hotel " + hotelName + " Added");
        }else{
            JOptionPane.showMessageDialog(this,"Hotel Name is Already Used");
        }
    }
}
