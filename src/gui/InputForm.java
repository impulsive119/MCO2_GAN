package gui;

import model.HotelReservationSystem;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class InputForm extends JPanel implements ActionListener {

    private final JPanel buttonPanel = new JPanel();
    protected final HotelReservationSystem HRS;

    public InputForm(HotelReservationSystem HRS) {
        super(new GridBagLayout());
        this.HRS = HRS;
        addInputFields();

        // Add cancel button to button panel
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setSize(60, 20);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        buttonPanel.add(cancelButton);

        // Add button panel to the bottom of the input form
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


    protected void addInputFields() {

    }

    protected ComboBox addComboBox() {
        return addComboBox("Select a Room", new Object[] {});
    }

    protected ComboBox addComboBox(String label, Object[] items) {
        ComboBox comboBox = new ComboBox(items);
        comboBox.addActionListener(this);
        addField(new JLabel(label), comboBox);
        return comboBox;
    }

    protected JTextField addTextField(String label) {
        return addTextField(label, 12);
    }

    protected JTextField addTextField(String label, int columns) {
        JTextField textField = new JTextField(columns);
        addField(new JLabel(label), textField);
        return textField;
    }

    protected JButton addEnterButton() {
        JButton button = new JButton("Enter");
        button.setSize(60, 20);
        button.addActionListener(this);
        buttonPanel.add(button);
        return button;
    }

    private void addField(JLabel label, JComponent component) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        add(label, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(component, gbc);
    }

    protected void addInputFields(HotelReservationSystem HRS){};

    protected void onEnter(){};
}
