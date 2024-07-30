package model;

import java.util.ArrayList;
import java.util.NoSuchElementException;


/**
 * The model.HotelReservationSystem class manages operations involving hotels, rooms, and reservations, which includes
 * modification of data, input verification, and data output.
 */


public class HotelReservationSystem {
    private final ArrayList<Hotel> hotels = new ArrayList<>();

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
     * Checks if name has been used.
     *
     * @param name  The name to check.
     * @return true if the name has been used, false otherwise.
     */


    public boolean isHotelNameAvailable(String name) {
        boolean found = true;
        for (Hotel hotel : hotels) {
            if (name.equals(hotel.getName())) {
                found = false;
                break;
            }
        }
        return found;
    }

    /**
     * Allows the user to select a hotel from the current list of hotels.
     *
     * @return The selected model.Hotel object, which is null if the selected hotel is invalid.
     */


    public Hotel getHotelUsingIndex(int hotelNumber){
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





    public Hotel getHotelUsingName(String name){
        for(Hotel hotel: hotels){
            if(hotel.getName().equals(name)){
                return hotel;
            }
        }

        return null;
    }

    public int getNumOfHotels(){
        return hotels.size();
    }

    public ArrayList<String> getHotelNames(){
        ArrayList<String> hotelNames = new ArrayList<>();
        for(Hotel hotel: hotels){
            hotelNames.add(hotel.getName());
        }
        return hotelNames;
    }

    public void removeHotel(Hotel hotel){
        hotels.remove(hotel);
    }
}
