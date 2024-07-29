package gui;

import model.Hotel;
import model.HotelReservationSystem;

import javax.swing.*;

public class ChangeHotelNameForm extends InputForm{
    private JTextField hotelNameField;
    private ComboBox hotelComboBox;

    public ChangeHotelNameForm(HotelReservationSystem HRS){
        super(HRS);
    }

    @Override
    protected void addInputFields(){
        hotelComboBox = addComboBox("Select a Hotel:", HRS.getHotelNames().toArray());
        hotelNameField = addTextField("Enter Hotel Name: ");
        JButton enterButton = addEnterButton();
        enterButton.addActionListener(_ -> onEnter());
    }

    @Override
    protected void onEnter(){
        String hotelName = (String) hotelComboBox.getSelectedItem();
        String newHotelName = hotelNameField.getText();
        Hotel hotel = HRS.getHotel(hotelName);

        if(hotel != null && hotel.setName(newHotelName)) {
            String message = "Hotel " + newHotelName + " Added";
            JOptionPane.showMessageDialog(this, message);
        }else{
            JOptionPane.showMessageDialog(this, "Invalid Hotel Name");
        }
    }
}
