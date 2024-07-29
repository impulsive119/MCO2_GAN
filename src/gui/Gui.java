package gui;

import model.HotelReservationSystem;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                InputForm addHotelForm = setForm(new AddHotelForm(HRS));
            }
        });

        JMenu viewHotel = new JMenu("View Hotel");

        viewHotel.add(new JMenu("View Availability On Date")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JMenu manageHotel = new JMenu("Manage Hotel");

        JMenu bookReservation = new JMenu("Book Reservation");
        bookReservation.add(new JMenuItem("Book Reservation")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

    private InputForm setForm(InputForm form) {
        root.getContentPane().removeAll();
        root.getContentPane().add(form);
        root.setVisible(true);
        return form;
    }

}
