public class Driver {
    public static void main(String[] args) {
    HotelReservationSystem HRS = new HotelReservationSystem();
    boolean exit = false;


    do {
        System.out.println("HOTEL RESERVATION SYSTEM");
        System.out.println("1. Create Hotel");
        System.out.println("2. View Hotel");
        System.out.println("3. Manage Hotel");
        System.out.println("4. Book Reservation");
        System.out.println("5. Exit");


        switch (InputHelper.nextInt()) {
            case 1:
                System.out.print("Enter Hotel Name: ");
                String hotelName = InputHelper.nextStr();
                HRS.createHotel(hotelName);
                break;


            case 2:
                Hotel viewedHotel = HRS.selectHotel();
                if (viewedHotel != null) {
                    HRS.viewHotel(viewedHotel);
                }
                break;
            case 3:
                Hotel managedHotel = HRS.selectHotel();
                if (managedHotel != null) {
                    HRS.manageHotel(managedHotel);
                }
                break;
            case 4:
                Hotel reservedHotel = HRS.selectHotel();
                if (reservedHotel != null) {
                    HRS.bookReservation(reservedHotel);
                }
                break;
            case 5:
                exit = true;
                break;
            default:
                System.out.println("Invalid Input");
                System.out.println(" ");
        }


    } while (!exit);


    InputHelper.close();
    }
}
