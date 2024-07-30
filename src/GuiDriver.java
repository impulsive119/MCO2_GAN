import gui.Gui;
import model.HotelReservationSystem;

/**
 * This is the driver file for the graphical user interface.
 */

public class GuiDriver {

    public static void main(String[] args) {
        Gui gui = new Gui(new HotelReservationSystem());
        gui.start();
    }
}