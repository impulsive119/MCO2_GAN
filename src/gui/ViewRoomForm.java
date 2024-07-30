package gui;

import model.HotelReservationSystem;
import model.Hotel;
import model.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * A form that allows users to view a given room's information.
 * This form extends {@link InputForm} and provides validation for the inputs and views selected room.
 */

public class ViewRoomForm extends InputForm{
    private ComboBox roomComboBox;
    private ComboBox hotelComboBox;

    /**
     * Constructs a new ViewRoomForm with the given hotel reservation system and root frame.
     *
     * @param HRS  The hotel reservation system that this form will interact with.
     * @param root The main frame of the application.
     */

    public ViewRoomForm(HotelReservationSystem HRS, JFrame root) {
        super(HRS, root);
    }

    /**
     * Returns the title for this form.
     *
     * @return The title of the form.
     */

    protected String getTitle() {
        return "Hotel Reservation System - View Room";
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
     * Updates roomComboBox according to the selected hotel in HotelComboBox.
     */

    private void hotelComboBoxClicked(ActionEvent e) {
        if (hotelComboBox.getSelectedItem() == null || hotelComboBox.getSelectedItem() == ComboBox.NONE) {
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
     * This method gets the selected hotel and room, validates the information,
     * then displays the room information if the inputs are valid.
     * Shows an appropriate message dialog for errors or successful viewings.
     */

    @Override
    protected void onEnter(){
        Object selectedHotel = hotelComboBox.getSelectedItem();
        if (!(selectedHotel instanceof String hotelName)) {
            JOptionPane.showMessageDialog(this, "Please Select a Valid Hotel");
            return;
        }

        Hotel hotel = HRS.getHotel(hotelName);
        Object selectedRoom = roomComboBox.getSelectedItem();
        if (!(selectedRoom instanceof Integer roomNumber)) {
            JOptionPane.showMessageDialog(this, "Please Select a Valid Reservation");
            return;
        }
        Room chosenRoom = hotel.getRoom(roomNumber);
        JOptionPane.showMessageDialog(
                this, "Room Number: " + roomNumber + "\n" +
                        "Room Type: " + chosenRoom.getRoomType() + "\n" +
                        "Room Price: " + chosenRoom.getPrice() + "\n" +
                        "Room Type: " + chosenRoom.getRoomType() + "\n" +
                        "Days Room is Available: " + "\n" +
                        chosenRoom.getAvailableDates() + "\n" +
                        "Days Room is Reserved: " + "\n" +
                        chosenRoom.getReservedDates());

    }
}
