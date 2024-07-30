package gui;

import model.HotelReservationSystem;

import javax.swing.*;

public class Gui {
    private final HotelReservationSystem HRS;

    public Gui(HotelReservationSystem HRS){
        this.HRS = HRS;
    }
    private final JFrame root = new JFrame();

    public void start() {
        JMenu createHotel = new JMenu("Create Hotel");

        createHotel.add(new JMenuItem("Enter Hotel Name")).addActionListener(_ -> setForm(new AddHotelForm(HRS)));

        JMenu viewHotel = new JMenu("View Hotel");

        viewHotel.add(new JMenuItem("View Availability On Date")).addActionListener(_ -> setFormIfHotelExists(new ViewAvailabilityOnDateForm(HRS) {
        }));

        viewHotel.add(new JMenuItem("View Room Information")).addActionListener(_ -> setFormIfHotelExists(new ViewRoomForm(HRS) {
        }));

        viewHotel.add(new JMenuItem("View Reservation")).addActionListener(_ -> setFormIfHotelExists(new ViewReservationForm(HRS) {
        }));

        JMenu manageHotel = new JMenu("Manage Hotel");

        manageHotel.add(new JMenuItem("Change Name")).addActionListener(_ -> setFormIfHotelExists(new ChangeHotelNameForm(HRS) {
        }));

        manageHotel.add(new JMenuItem("Add Rooms")).addActionListener(_ -> setFormIfHotelExists(new AddRoomsForm(HRS) {
        }));

        manageHotel.add(new JMenuItem("Remove Rooms")).addActionListener(_ -> setFormIfHotelExists(new RemoveRoomForm(HRS) {
        }));

        manageHotel.add(new JMenuItem("Change Price")).addActionListener(_ -> setFormIfHotelExists(new ChangePriceForm(HRS) {
        }));

        manageHotel.add(new JMenuItem("Add Premium To Date")).addActionListener(_ -> setFormIfHotelExists(new AddPremiumToDateForm(HRS) {
        }));

        manageHotel.add(new JMenuItem("Remove Reservation")).addActionListener(_ -> setFormIfHotelExists(new RemoveReservationForm(HRS) {
        }));

        manageHotel.add(new JMenuItem("Remove Hotel")).addActionListener(_ -> setFormIfHotelExists(new RemoveHotelForm(HRS) {
        }));

        JMenu bookReservation = new JMenu("Book Reservation");
        bookReservation.add(new JMenuItem("Book Reservation")).addActionListener(_ -> setFormIfHotelExists(new BookReservationForm(HRS)));

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

    private void setFormIfHotelExists(InputForm form){
        if (HRS.getNumOfHotels() > 0){
            root.getContentPane().removeAll();
            root.getContentPane().add(form);
            root.setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(null, "There are no active hotels");
        }
    }

}
