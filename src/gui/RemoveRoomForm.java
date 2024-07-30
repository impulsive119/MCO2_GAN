package gui;

import model.Hotel;
import model.HotelReservationSystem;
import model.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * A form that allows users to remove a room from a selected hotel.
 * This form extends {@link InputForm} and provides validation for the inputs and updates the system accordingly.
 */

public class RemoveRoomForm extends InputForm{
    private ComboBox roomComboBox;
    private ComboBox hotelComboBox;

    /**
     * Constructs a new RemoveReservationForm with the given hotel reservation system and root frame.
     *
     * @param HRS  The hotel reservation system that this form will interact with.
     * @param root The main frame of the application.
     */


    public RemoveRoomForm(HotelReservationSystem HRS, JFrame root) {
        super(HRS, root);
    }

    /**
     * Returns the title for this form.
     *
     * @return The title of the form.
     */

    protected String getTitle() {
        return "Hotel Reservation System - Remove Room";
    }

    /**
     * Adds the input fields to the form.
     */

    @Override
    protected void addInputFields(){
        hotelComboBox = addComboBox("Select a Hotel:", HRS.getHotelNames().toArray());
        roomComboBox = addComboBox("Select a Room", null);
        hotelComboBox.addActionListener(this::hotelComboBoxClicked);
        JButton enterButton = addEnterButton();
        enterButton.addActionListener(_ -> onEnter());
    }

    /**
     * Updates roomComboBox based on the selected hotel in hotelComboBox
     */

    private void hotelComboBoxClicked(ActionEvent e) {
        if (hotelComboBox.getSelectedItem() == null || hotelComboBox.getSelectedItem().equals("NONE")) {
            roomComboBox.removeAllItems();
        } else {
            Hotel hotel = HRS.getHotel((String) hotelComboBox.getSelectedItem());
            if (hotel != null) {
                ArrayList<Integer> rooms = new ArrayList<>();
                for (Room room : hotel.getRooms()) {
                    rooms.add(room.getRoomNumber());
                }
                roomComboBox.removeAllItems();
                for (Integer roomNumber : rooms) {
                    roomComboBox.addItem(roomNumber);
                }
            }
        }
    }

    /**
     * Handles the action performed when the "Enter" button is clicked.
     * This method gets the selected hotel and room info, validates it,
     * then removes the room from the hotel if it is valid.
     * Shows an appropriate message dialog for errors or successful updates.
     * Updates its own ComboBox accordingly.
     */

    @Override
    protected void onEnter() {
        Object selectedHotel = hotelComboBox.getSelectedItem();
        if (!(selectedHotel instanceof String hotelName)) {
            JOptionPane.showMessageDialog(this, "Please Select a Valid Hotel");
            return;
        }

        Hotel hotel = HRS.getHotel(hotelName);

        Object selectedRoom = roomComboBox.getSelectedItem();
        if (!(selectedRoom instanceof Integer roomNumber)) {
            JOptionPane.showMessageDialog(this, "Please Select a Valid Room");
            return;
        }

        Room chosenRoom = hotel.getRoom(roomNumber);
        hotel.removeRoom(chosenRoom);
        JOptionPane.showMessageDialog(this, "Room Removed");

        ArrayList<Room> rooms = new ArrayList<>(hotel.getRooms());
        roomComboBox.removeAllItems();
        for (Room room : rooms) {
            roomComboBox.addItem(room.getRoomNumber());
        }
    }
}
