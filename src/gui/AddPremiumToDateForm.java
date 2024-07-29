package gui;

import model.Hotel;
import model.HotelReservationSystem;

import javax.swing.*;

public abstract class AddPremiumToDateForm extends InputForm{
    private ComboBox hotelComboBox;
    private JTextField premiumField;
    private JTextField dateField;

    public AddPremiumToDateForm(HotelReservationSystem HRS){
        super(HRS);
    }

    @Override
    protected void addInputFields(){
        hotelComboBox = addComboBox("Select a Hotel:", HRS.getHotelNames().toArray());
        dateField = addTextField("Enter Date: ");
        premiumField = addTextField("Enter Premium: ");
        JButton enterButton = addEnterButton();
        enterButton.addActionListener(_ -> onEnter());
    }

    @Override
    protected void onEnter() {
        String hotelName = (String) hotelComboBox.getSelectedItem();
        Hotel hotel = HRS.getHotel(hotelName);
        if(hotel == null){
            JOptionPane.showMessageDialog(this, "Please Select a Valid Hotel");
            return;
        }
        int date = Integer.parseInt(dateField.getText());
        double premium = Double.parseDouble(premiumField.getText());
        if(date < 1 || date > 31){
            JOptionPane.showMessageDialog(this, "Invalid Date");
        }else if(premium < 0.5 || premium > 1.5 ){
            JOptionPane.showMessageDialog(this, "Value Must Be Between 0.5 and 1.5");
        }else{
            hotel.addPremiumToDate(date, premium);
            JOptionPane.showMessageDialog(this,"Premium Added");
        }
    }
}
