package model;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * The HotelReservationSystem class holds a list of hotels, and manages operations involving these hotels, its rooms, and
 * reservations. It includes methods for modifying data, verifying inputs, and retrieving hotel data.
 */
public class HotelReservationSystem {
    private final ArrayList<Hotel> hotels = new ArrayList<>();

    /**
     * Creates a new hotel using the specified name if the name is not already used by an existing hotel.
     *
     * @param hotelName The name of the hotel.
     * @return True if the hotel was successfully created, false if the name is already used by an existing hotel.
     */
    public boolean createHotel(String hotelName) {
        if (isHotelNameAvailable(hotelName)) {
            Hotel newHotel = new Hotel(hotelName, this);
            hotels.add(newHotel);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if a hotel name is already used by another hotel or not.
     *
     * @param name The name to check.
     * @return True if the name is available, false otherwise.
     */
    public boolean isHotelNameAvailable(String name) {
        for (Hotel hotel : hotels) {
            if (name.equals(hotel.getName())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets a hotel using its number from the current list of hotels.
     *
     * @param hotelNumber The hotel number.
     * @return The Hotel corresponding to the hotel number, or null if the number is invalid.
     */
    public Hotel getHotel(int hotelNumber){
        try {
            if (hotelNumber > 0 && hotelNumber <= hotels.size()) {
                return hotels.get(hotelNumber - 1);
            } else {
                return null;
            }
        } catch (NumberFormatException | IndexOutOfBoundsException | NoSuchElementException e) {
            return null;
        }
    }

    /**
     * Gets a hotel using its name.
     *
     * @param name The name of the hotel.
     * @return The Hotel with the given name, or null if no hotel with that name is found.
     */
    public Hotel getHotel(String name){
        for (Hotel hotel : hotels){
            if (hotel.getName().equals(name)){
                return hotel;
            }
        }
        return null;
    }

    /**
     * Gets the number of hotels currently managed by the system.
     *
     * @return The number of hotels.
     */
    public int getNumOfHotels(){
        return hotels.size();
    }

    /**
     * Gets the list of names of all hotels currently managed by the system.
     *
     * @return The list of hotel names.
     */
    public ArrayList<String> getHotelNames(){
        ArrayList<String> hotelNames = new ArrayList<>();
        for (Hotel hotel : hotels){
            hotelNames.add(hotel.getName());
        }
        return hotelNames;
    }

    /**
     * Removes a specified hotel from the system.
     *
     * @param hotel The hotel to be removed.
     */
    public void removeHotel(Hotel hotel){
        hotels.remove(hotel);
    }
}