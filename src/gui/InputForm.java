package gui;

import model.Hotel;
import model.HotelReservationSystem;
import model.Room;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class InputForm extends JPanel {

    public InputForm() {
        super(new GridBagLayout());
        addInputFields();
        addButtons();
    }

    protected void addInputFields() {

    }

    protected void onEnter() {

    }

    protected JTextField addTextField(String label) {
        return addTextField(label, 12);
    }

    protected JTextField addTextField(String label, int columns) {
        JTextField textField = new JTextField(columns);
        addComponents(new JLabel(label), textField);
        return textField;
    }

    private void addComponents(JLabel label, JTextField tf) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        add(label, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(tf, gbc);
    }

    private void addButtons() {
        JPanel buttonPanel = new JPanel();

        JButton saveButton = new JButton("Save");
        saveButton.setSize(60, 20);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onEnter();
            }
        });
        buttonPanel.add(saveButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setSize(60, 20);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        buttonPanel.add(cancelButton);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);
    }

    protected abstract void onEnter(Hotel hotel);

    protected abstract void onEnter(Room hotel);
}
