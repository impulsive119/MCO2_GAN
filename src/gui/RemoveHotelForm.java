package gui;

import model.Hotel;
import model.HotelReservationSystem;

import javax.swing.*;

public abstract class RemoveHotelForm extends InputForm{
    private ComboBox hotelComboBox;

    public RemoveHotelForm(HotelReservationSystem HRS) {
        super(HRS);
    }

    @Override
    protected void addInputFields(){
        hotelComboBox = addComboBox("Select a Hotel:", HRS.getHotelNames().toArray());
        JButton enterButton = addEnterButton();
        enterButton.addActionListener(_ -> onEnter());
    }

    @Override
    protected void onEnter(){
        String hotelName = (String) hotelComboBox.getSelectedItem();
        Hotel hotel = HRS.getHotel(hotelName);
        if(hotel != null){
            HRS.removeHotel(hotel);
            JOptionPane.showMessageDialog(this, "Hotel Removed");
        }else{
            JOptionPane.showMessageDialog(this, "Please Select a Valid Hotel");
        }
    }
}
