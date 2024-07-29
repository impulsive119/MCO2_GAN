package gui;

import model.Hotel;
import model.HotelReservationSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ChangeHotelNameForm extends InputForm{
    private JTextField hotelNameField;
    private ComboBox hotelComboBox;
    private JButton enterButton;

    public ChangeHotelNameForm(HotelReservationSystem HRS){
        super(HRS);
    }

    @Override
    protected void addInputFields(){
        hotelComboBox = addComboBox("Select a Hotel:", HRS.getHotels().toArray());
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
        Hotel hotel = (Hotel) hotelComboBox.getSelectedItem();
        String hotelName = hotelNameField.getText();

        if(hotel != null && hotel.setName(hotelName)) {
            String message = "Hotel " + hotelName + " Added";
            JOptionPane.showMessageDialog(this, message);
        }else{
            JOptionPane.showMessageDialog(this, "Invalid Hotel Name");
        }
    }
}
