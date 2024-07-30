package gui;

import model.HotelReservationSystem;

import javax.swing.*;

/**
 * Represents the graphical user interface for the Hotel Reservation System.
 * This class sets up the main window, menu items, and handles navigation between different input forms to allow the user to view whatever information
 * they want to view.
 */
public class Gui {
    private final HotelReservationSystem HRS;

    /**
     * Constructs a new Gui instance.
     *
     * @param HRS The hotel reservation system that this GUI interacts with.
     */
    public Gui(HotelReservationSystem HRS) {
        this.HRS = HRS;
    }

    private final JFrame root = new JFrame();

    /**
     * Initializes and displays the main window of the hotel reservation system.
     * This method sets up the menu bar with the option to create hotels, view hotels, manage hotels, and book reservations.
     */
    public void start() {
        JMenu createHotel = new JMenu("Create Hotel");
        createHotel.add(new JMenuItem("Enter Hotel Name")).addActionListener(_ -> setForm(new CreateHotelForm(HRS, root)));

        JMenu viewHotel = new JMenu("View Hotel");
        viewHotel.add(new JMenuItem("Hotel Overview")).addActionListener(_ -> setFormIfHotelExists(new HotelOverviewForm(HRS, root)));
        viewHotel.add(new JMenuItem("View Availability On Date")).addActionListener(_ -> setFormIfHotelExists(new ViewAvailabilityOnDateForm(HRS, root)));
        viewHotel.add(new JMenuItem("View Room Information")).addActionListener(_ -> setFormIfHotelExists(new ViewRoomForm(HRS, root)));
        viewHotel.add(new JMenuItem("View Reservation")).addActionListener(_ -> setFormIfHotelExists(new ViewReservationForm(HRS, root)));

        JMenu manageHotel = new JMenu("Manage Hotel");
        manageHotel.add(new JMenuItem("Change Name")).addActionListener(_ -> setFormIfHotelExists(new ChangeHotelNameForm(HRS, root)));
        manageHotel.add(new JMenuItem("Add Rooms")).addActionListener(_ -> setFormIfHotelExists(new AddRoomsForm(HRS, root)));
        manageHotel.add(new JMenuItem("Remove Rooms")).addActionListener(_ -> setFormIfHotelExists(new RemoveRoomForm(HRS, root)));
        manageHotel.add(new JMenuItem("Change Price")).addActionListener(_ -> setFormIfHotelExists(new ChangePriceForm(HRS, root)));
        manageHotel.add(new JMenuItem("Add Premium To Date")).addActionListener(_ -> setFormIfHotelExists(new AddPremiumToDateForm(HRS, root)));
        manageHotel.add(new JMenuItem("Remove Reservation")).addActionListener(_ -> setFormIfHotelExists(new RemoveReservationForm(HRS, root)));
        manageHotel.add(new JMenuItem("Remove Hotel")).addActionListener(_ -> setFormIfHotelExists(new RemoveHotelForm(HRS, root)));

        JMenu bookReservation = new JMenu("Book Reservation");
        bookReservation.add(new JMenuItem("Book Reservation")).addActionListener(_ -> setFormIfHotelExists(new BookReservationForm(HRS, root)));

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createHotel);
        menuBar.add(viewHotel);
        menuBar.add(manageHotel);
        menuBar.add(bookReservation);

        root.setJMenuBar(menuBar);
        root.setTitle("Hotel Reservation System");
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root.setSize(500, 400);
        root.setLocation(200, 200);
        root.setVisible(true);
    }

    /**
     * Sets the given form as the main content of the root window.
     * This method also updates the title of the window to match the form's title.
     *
     * @param form The form to be set as the main content.
     */
    private void setForm(InputForm form) {
        root.setTitle(form.getTitle());
        root.getContentPane().removeAll();
        root.getContentPane().add(form);
        root.setVisible(true);
    }

    /**
     * Calls setForm if there is at least one hotel available.
     * If no hotels are available, an error message is displayed instead.
     *
     * @param form The form to be set as the main content.
     */
    private void setFormIfHotelExists(InputForm form) {
        if (HRS.getNumOfHotels() > 0) {
            setForm(form);
        } else {
            JOptionPane.showMessageDialog(null, "There are no active hotels");
        }
    }
}