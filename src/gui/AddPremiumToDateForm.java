package gui;

import model.Hotel;
import model.HotelReservationSystem;

import javax.swing.*;

public abstract class AddPremiumToDateForm extends InputForm{
    private ComboBox hotelComboBox;
    private JTextField premiumField;
    private JTextField dateField;

    public AddPremiumToDateForm(HotelReservationSystem HRS, JFrame root) {
        super(HRS, root);
    }

    protected String getTitle() {
        return "Hotel Reservation System - Add Premium To Date";
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
        Object selectedHotel = hotelComboBox.getSelectedItem();
        if (!(selectedHotel instanceof String hotelName)) {
            JOptionPane.showMessageDialog(this, "Please Select a Valid Hotel");
            return;
        }

        Hotel hotel = HRS.getHotel(hotelName);
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
        dateField.setText("");
        premiumField.setText("");
    }
}
