package gui;

import model.Hotel;
import model.HotelReservationSystem;

import javax.swing.*;
import java.util.ArrayList;

/**
 * A form that allows users to add rooms to a selected hotel.
 * This form extends {@link InputForm} and provides validation for the inputs and updates the hotel with the new room information.
 */

public class AddRoomsForm extends InputForm {
    private ComboBox roomTypeComboBox;
    private JTextField numOfRoomsField;
    private ComboBox hotelComboBox;

    /**
     * Constructs a new AddRoomsForm with the given hotel reservation system and root frame.
     *
     * @param HRS  The hotel reservation system that this form will interact with.
     * @param root The main frame of the application.
     */

    public AddRoomsForm(HotelReservationSystem HRS, JFrame root) {
        super(HRS, root);

    }

    /**
     * Returns the title for this form.
     *
     * @return The title of the form.
     */

    protected String getTitle() {
        return "Hotel Reservation System - Add Rooms";
    }

    /**
     * Adds the input fields to the form.
     */

    @Override
    protected void addInputFields() {
        hotelComboBox = addComboBox("Select a Hotel:", HRS.getHotelNames().toArray());
        ArrayList<String> roomTypes = new ArrayList<>();
        roomTypes.add("Standard");
        roomTypes.add("Deluxe");
        roomTypes.add("Executive");
        roomTypeComboBox = addComboBox("Select a Room Type: ", roomTypes.toArray());
        numOfRoomsField = addTextField("Enter Number of Rooms: ");
        JButton enterButton = addEnterButton();
        enterButton.addActionListener(e -> onEnter());
    }

    /**
     * Handles the action performed when the "Enter" button is clicked.
     * This method gets the selected hotel, room type, and number of rooms,
     * validates the inputs, and updates the selected hotel with the new room information.
     * Shows an appropriate message dialog for errors or successful updates.
     */

    @Override
    protected void onEnter() {
        Object selectedHotel = hotelComboBox.getSelectedItem();
        if (!(selectedHotel instanceof String hotelName)) {
            JOptionPane.showMessageDialog(this, "Please Select a Valid Hotel");
            return;
        }

        Object selectedReservation = roomTypeComboBox.getSelectedItem();
        if (!(selectedReservation instanceof String roomTypeString)) {
            JOptionPane.showMessageDialog(this, "Please Select a Valid Reservation");
            return;
        }

        Hotel hotel = HRS.getHotel(hotelName);

        int numOfRooms = Integer.parseInt(numOfRoomsField.getText());
        int roomType = 0;

        switch (roomTypeString) {
            case "Standard":
                roomType = 1;
                break;
            case "Deluxe":
                roomType = 2;
                break;
            case "Executive":
                roomType = 3;
                break;
            default:
                break;
        }
        if (hotel.isNumberOfRoomsValid(numOfRooms)) {
            JOptionPane.showMessageDialog(this, "Rooms " + hotel.addRooms(numOfRooms, roomType) + " Added");
        } else if(numOfRooms < 1){
            JOptionPane.showMessageDialog(this, "Invalid Input");
        }else{
            JOptionPane.showMessageDialog(this, "The Number of Rooms Cannot Exceed 50");
        }

        numOfRoomsField.setText("");
    }
}
