package gui;

import model.Hotel;
import model.Reservation;

import javax.swing.*;

public abstract class ReservationDropdown extends Dropdown{
    public Reservation setDropdown(Hotel hotel){
        String[] reservations = new String[hotel.getNumberOfReservations()];
        for(int i = 0; i < hotel.getNumberOfReservations(); i++){
            reservations[i] = hotel.getReservations().get(i).getGuestName();
        }

        String name = JOptionPane.showInputDialog(null, "Select Reservation", reservations);
        return hotel.getReservation(name);
    }
}
