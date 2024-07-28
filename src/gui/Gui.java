package gui;

import model.Room;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Gui {

    private final JFrame root = new JFrame();

    public void start() {
        JMenu createHotel = new JMenu("Create Hotel");

        createHotel.add(new JMenuItem("Enter Hotel Name")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setForm(new HotelCreationForm() {
                    @Override
                    protected void onEnter(Room hotel) {

                    }
                });
            }
        });


        createHotel.addSeparator();

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createHotel);

        root.setJMenuBar(menuBar);
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root.setSize(500,400);
        root.setLocation(200,200);
        root.setVisible(true);
    }

    private void setForm(InputForm form) {
        root.getContentPane().removeAll();
        root.getContentPane().add(form);
        root.setVisible(true);
    }

    private void setDropdown (Dropdown dropdown){
        root.getContentPane().removeAll();
        root.getContentPane().add(dropdown);
        root.setVisible(true);
    }


}
