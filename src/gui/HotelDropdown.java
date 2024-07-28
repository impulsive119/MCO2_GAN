package gui;
import model.HotelReservationSystem;
import javax.swing.*;

public abstract class HotelDropdown extends Dropdown{
    public String hotelDropdown(HotelReservationSystem HRS) {
        String[] hotels = new String[HRS.getNumOfHotels()];
        for(int i = 0; i < HRS.getNumOfHotels(); i++){
            hotels[i] = HRS.getHotelNames().get(i);
        }

        return JOptionPane.showInputDialog(null, "Select Hotel", hotels);
    }
}
