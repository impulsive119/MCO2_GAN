package gui;

import javax.swing.*;
import model.Hotel;

import java.util.ArrayList;

public abstract class ViewAvailabilityOnDateForm extends InputForm{
    private JTextField dateField;

    @Override
    protected void addInputFields(){
        dateField = addTextField("Enter Date: ");
    }

    @Override
    protected void onEnter(Hotel hotel){
        int date = Integer.parseInt(dateField.getText());

        if(date < 1 || date > 31) {
            JOptionPane.showMessageDialog(this, "Invalid Hotel Name");
        }else {
            JOptionPane.showMessageDialog(this,
                    "Available Rooms:"+ "\n" +
                            hotel.getAvailableRoomsOnDate(date) +"\n" +
                            "Reserved Rooms: " + "\n" +
                            hotel.getReservedRoomsOnDate(date));
        }
    }
}
