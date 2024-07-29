package gui;

import model.HotelReservationSystem;

import javax.swing.*;

public class AddHotelForm extends InputForm{
    private JTextField hotelNameField;

    public AddHotelForm(HotelReservationSystem HRS){
        super(HRS);
    }

    @Override
    protected void addInputFields(){
        hotelNameField = addTextField("Enter Hotel Name: ");
        JButton enterButton = addEnterButton();
        enterButton.addActionListener(_ -> onEnter());
    }

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
    }
}
