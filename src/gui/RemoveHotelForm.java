package gui;

import model.Hotel;
import model.HotelReservationSystem;
import model.Reservation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public abstract class RemoveHotelForm extends InputForm{
    private ComboBox hotelComboBox;
    private JButton enterButton;

    public RemoveHotelForm(HotelReservationSystem HRS) {
        super(HRS);
    }

    @Override
    protected void addInputFields(){
        hotelComboBox = addComboBox("Select a Hotel:", HRS.getHotels().toArray());
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
        Reservation chosenReservation = null;
        if(hotel != null){
            HRS.removeHotel(hotel);
            JOptionPane.showMessageDialog(this, "Hotel Removed");
        }else{
            JOptionPane.showMessageDialog(this, "Please Select a Valid Hotel");
        }
    }
}
