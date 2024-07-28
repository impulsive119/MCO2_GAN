package gui;
import model.HotelReservationSystem;
import model.Hotel;
import javax.swing.*;

public abstract class HotelDropdown extends Dropdown{
    public Hotel setDropdown(HotelReservationSystem HRS){
        String[] hotels = new String[HRS.getNumOfHotels()];
        for(int i = 0; i < HRS.getNumOfHotels(); i++){
            hotels[i] = HRS.getHotelNames().get(i);
        }

        String name = JOptionPane.showInputDialog(null, "Select Hotel", hotels);
        return HRS.getHotel(name);
    }
}
