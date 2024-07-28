package gui;
import model.Hotel;
import model.HotelReservationSystem;
import model.Reservation;
import model.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Dropdown extends JOptionPane{
    public abstract String hotelDropdown(HotelReservationSystem HRS);

    public abstract Reservation reservationDropdown(Hotel hotel);

    public abstract Room roomDropdown(Hotel hotel);

    private void addComponents(JLabel label, JTextField tf) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        add(label, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(tf, gbc);
    }
}
