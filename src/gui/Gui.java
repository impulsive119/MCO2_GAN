package gui;

import model.Hotel;
import model.HotelReservationSystem;
import model.Reservation;
import model.Room;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Gui {
    private final HotelReservationSystem HRS;

    public Gui(HotelReservationSystem HRS){
        this.HRS = HRS;
    }
    private final JFrame root = new JFrame();

    public void start() {
        JMenu createHotel = new JMenu("Create Hotel");

        createHotel.add(new JMenuItem("Enter Hotel Name")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDropdown(new HotelDropdown() {
                    @Override
                    public Reservation reservationDropdown(Hotel hotel) {
                        return null;
                    }

                    @Override
                    public Room roomDropdown(Hotel hotel) {
                        return null;
                    }
                });
            }
        });


        createHotel.addSeparator();

        JMenu viewHotel = new JMenu("View Hotel");

        JMenu manageHotel = new JMenu("Manage Hotel");

        JMenu bookReservation = new JMenu("Book Reservation");
        bookReservation.add(new JMenuItem("Book Reservation")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDropdown(new HotelDropdown() {
                    @Override
                    public String hotelDropdown(HotelReservationSystem HRS) {
                        return super.hotelDropdown(HRS);
                    }

                    @Override
                    public Reservation reservationDropdown(Hotel hotel) {
                        return null;
                    }

                    @Override
                    public Room roomDropdown(Hotel hotel) {
                        return null;
                    }
                });
            }
        });

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createHotel);
        menuBar.add(viewHotel);
        menuBar.add(manageHotel);
        menuBar.add(bookReservation);

        root.setJMenuBar(menuBar);
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root.setSize(500,400);
        root.setLocation(200,200);
        root.setVisible(true);
    }

    private void setForm(InputForm form) {
        root.getContentPane().removeAll();
        root.getContentPane().add(form);
        root.setVisible(true);
    }

    private void setDropdown (Dropdown dropdown){
        root.getContentPane().removeAll();
        root.getContentPane().add(dropdown);
        root.setVisible(true);
    }


}
