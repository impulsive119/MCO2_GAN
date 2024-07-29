package gui;

import model.HotelReservationSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddHotelForm extends InputForm{
    private JTextField hotelNameField;
    private JButton enterButton;

    public AddHotelForm(HotelReservationSystem HRS){
        super(HRS);
    }

    @Override
    protected void addInputFields(){
        hotelNameField = addTextField("Enter Hotel Name: ");
        enterButton = addEnterButton();
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onEnter();
            }
        });
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
