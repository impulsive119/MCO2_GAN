import gui.GuiController;
import model.HotelReservationSystem;

/**
 * This is the driver file for the graphical user interface.
 */

public class GuiDriver {

    public static void main(String[] args) {
        GuiController gui = new GuiController(new HotelReservationSystem());
        gui.start();
    }
}