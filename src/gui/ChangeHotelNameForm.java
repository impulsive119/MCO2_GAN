package gui;

import model.Hotel;
import model.HotelReservationSystem;

import javax.swing.*;

public class ChangeHotelNameForm extends InputForm{
    private JTextField hotelNameField;
    private ComboBox hotelComboBox;

    public ChangeHotelNameForm(HotelReservationSystem HRS, JFrame root) {
        super(HRS, root);
    }

    protected String getTitle() {
        return "Hotel Reservation System - Change Hotel Name";
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
        Object selectedHotel = hotelComboBox.getSelectedItem();
        if (!(selectedHotel instanceof String hotelName)) {
            JOptionPane.showMessageDialog(this, "Please Select a Valid Hotel");
            return;
        }

        Hotel hotel = HRS.getHotel(hotelName);
        String newHotelName = hotelNameField.getText();

        if(hotel != null && hotel.setName(newHotelName)) {
            String message = "Hotel " + newHotelName + " Added";
            JOptionPane.showMessageDialog(this, message);
            hotelComboBox.setItems(HRS.getHotelNames().toArray());
        }else{
            JOptionPane.showMessageDialog(this, "Invalid Hotel Name");
        }
        hotelNameField.setText("");
    }
}
