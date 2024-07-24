
import java.util.ArrayList;
import java.util.NoSuchElementException;


/**
 * The HotelReservationSystem class manages operations involving hotels, rooms, and reservations, which includes
 * modification of data, input verification, and data output.
 */


public class HotelReservationSystem {
    private final ArrayList<Hotel> hotels = new ArrayList<>();
    private final ArrayList<String> hotelNames = new ArrayList<>();

    public boolean createHotel(String hotelName) {
        if (isNameUnique(hotelNames, hotelName)) {
            Hotel newHotel = new Hotel(hotelName);
            hotels.add(newHotel);
            hotelNames.add(newHotel.getName());
            return true;
        } else {
            return false;
        }
    }
    public int bookReservation(Hotel hotel, Room room, String guestName, int checkInDate, int checkOutDate, String discountCode) {
        int successStatus;
        switch (discountCode){
            case "STAY4_GET1":
                    if(checkOutDate - checkInDate + 1 >= 5){
                        hotel.addReservation(room, guestName, checkInDate, checkOutDate, discountCode);
                        successStatus = 1;
                    }
                    else{
                        successStatus = 2;
                    }
                    break;
                    case "PAYDAY":
                    if((checkInDate <= 15 && checkOutDate > 15)||(checkInDate <= 28 && checkOutDate > 28)){
                        hotel.addReservation(room, guestName, checkInDate, checkOutDate, discountCode);
                        successStatus = 1;
                    }
                    else{
                        successStatus = 3;
                    }
                    break;
                    default:
                        hotel.addReservation(room, guestName, checkInDate, checkOutDate, discountCode);
                    successStatus = 1;
        }
        return successStatus;
    }


    /**
     * Checks if a name is unique given the list of existing names.
     *
     * @param names The list of existing names.
     * @param name  The name to check.
     * @return True if the name is unique (not found in the list), false otherwise.
     */


    public boolean isNameUnique(ArrayList<String> names, String name) {return !names.contains(name);}

    /**
     * Allows the user to select a hotel from the current list of hotels.
     *
     * @return The selected Hotel object, which is null if the selected hotel is invalid.
     */


    public Hotel selectHotel(int hotelNumber){
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
     * Asks for confirmation before modifying an object.
     *
     * @return True if the user confirms, false otherwise.
     */

    public boolean isNumberOfRoomsValid(Hotel hotel, int numOfRooms){
        return numOfRooms >= 1 && numOfRooms + hotel.getNumberOfRooms() <= 50;
    }

    public Hotel getHotel(String name){
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
        return hotelNames;
    }

    public void removeHotel(Hotel hotel){
        hotels.remove(hotel);
        hotelNames.remove(hotel.getName());
    }

    public void changeHotelName(Hotel hotel, String newName){
        for(int i = 0; i < hotelNames.size(); i++){
            if (hotelNames.get(i).equals(hotel.getName())){
                hotelNames.set(i, newName);
            }
        }
        hotel.setName(newName);
    }
}
