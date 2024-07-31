import cli.ControlHRS;
import cli.InputHelper;
import cli.ViewHRS;
import model.HotelReservationSystem;

/**
 * This is the driver for the command line interface
 */

public class CommandDriver {
    public static void main (String[] args){
        HotelReservationSystem HRS = new HotelReservationSystem();
        ViewHRS view = new ViewHRS();
        ControlHRS controller = new ControlHRS(HRS, view);
        boolean exit = false;
        while(!exit){
            controller.printMenu();
            int option = InputHelper.nextInt();
            switch (option){
                case 1:
                    controller.createHotel();
                    break;
                case 2:
                    controller.viewHotel();
                    break;
                case 3:
                    controller.manageHotel();
                    break;
                case 4:
                    controller.bookReservation();
                    break;
                case 5:
                    exit = true;
            }
        }

        InputHelper.close();
    }
}
