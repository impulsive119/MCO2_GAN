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
                setForm(new AddHotelForm(HRS));
            }
        });

        JMenu viewHotel = new JMenu("View Hotel");

        viewHotel.add(new JMenuItem("View Availability On Date")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setForm(new ViewAvailabilityOnDateForm(HRS) {
                });
            }
        });

        viewHotel.add(new JMenuItem("View Room Information")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setForm(new ViewRoomForm(HRS) {
                });
            }
        });

        viewHotel.add(new JMenuItem("View Reservation")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setForm(new ViewReservationForm(HRS) {
                });
            }
        });

        JMenu manageHotel = new JMenu("Manage Hotel");

        manageHotel.add(new JMenuItem("Change Name")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setForm(new ChangeHotelNameForm(HRS) {
                });
            }
        });

        manageHotel.add(new JMenuItem("Change Name")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setForm(new ChangeHotelNameForm(HRS) {
                });
            }
        });

        manageHotel.add(new JMenuItem("Add Rooms")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setForm(new AddRoomsForm(HRS) {
                });
            }
        });

        manageHotel.add(new JMenuItem("Remove Rooms")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setForm(new RemoveRoomForm(HRS) {
                });
            }
        });

        manageHotel.add(new JMenuItem("Change Price")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setForm(new ChangePriceForm(HRS) {
                });
            }
        });

        manageHotel.add(new JMenuItem("Add Premium To Date")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setForm(new AddPremiumToDateForm(HRS) {
                });
            }
        });

        manageHotel.add(new JMenuItem("Remove Reservation")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setForm(new RemoveReservationForm(HRS) {
                });
            }
        });

        manageHotel.add(new JMenuItem("Remove Hotel")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setForm(new RemoveHotelForm(HRS) {
                });
            }
        });

        JMenu bookReservation = new JMenu("Book Reservation");
        bookReservation.add(new JMenuItem("Book Reservation")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setForm(new BookReservationForm(HRS));
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

}
