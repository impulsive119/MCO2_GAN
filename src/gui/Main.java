package gui;

import model.HotelReservationSystem;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        Gui gui = new Gui(new HotelReservationSystem());
        gui.start();
    }

}