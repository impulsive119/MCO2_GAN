package gui;

import model.Hotel;
import model.HotelReservationSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AddPremiumToDateForm extends InputForm{
    private ComboBox hotelComboBox;
    private JTextField premiumField;
    private JTextField dateField;
    private JButton enterButton;

    public AddPremiumToDateForm(HotelReservationSystem HRS){
        super(HRS);
    }

    @Override
    protected void addInputFields(){
        hotelComboBox = addComboBox("Select a Hotel:", HRS.getHotelNames().toArray());
        dateField = addTextField("Enter Date: ");
        premiumField = addTextField("Enter Premium: ");
        enterButton = addEnterButton();
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onEnter();
            }
        });
    }

    @Override
    protected void onEnter() {
        Hotel hotel = (Hotel) hotelComboBox.getSelectedItem();
        int date = Integer.parseInt(dateField.getText());
        double premium = Double.parseDouble(premiumField.getText());
        if(date < 1 || date > 31){
            JOptionPane.showMessageDialog(this, "Invalid Date");
        }else if(premium < 0.5 || premium > 1.5 ){
            JOptionPane.showMessageDialog(this, "Value Must Be Between 0.5 and 1.5");
        }else if (hotel != null) {
            hotel.addPremiumToDate(date, premium);
            JOptionPane.showMessageDialog(this,"Premium Added");
        }
    }
}
