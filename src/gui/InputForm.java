package gui;

import model.HotelReservationSystem;

import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A base class for forms used in the hotel reservation system.
 * This class sets up the layout for input forms and provides common functionality for handling user input.
 */

public class InputForm extends JPanel implements ActionListener {

    private final JPanel buttonPanel = new JPanel();
    protected final HotelReservationSystem HRS;
    protected final JFrame root;

    /**
     * Constructs a new InputForm with the given hotel reservation system and root frame.
     *
     * @param HRS  The hotel reservation system that this form will interact with.
     * @param root The main frame of the application.
     */
    public InputForm(HotelReservationSystem HRS, JFrame root) {
        super(new GridBagLayout());
        this.HRS = HRS;
        this.root = root;

        addInputFields();

        // Add cancel button to button panel
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setSize(60, 20);
        cancelButton.addActionListener(_ -> {
            setVisible(false);
            root.setTitle("Hotel Reservation System");
        });
        buttonPanel.add(cancelButton);

        // Add button panel to the bottom of the input form
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);
    }

    /**
     * Returns the title for this form. This method can be overridden by subclasses to provide a custom title.
     *
     * @return The title of the form.
     */
    protected String getTitle() {
        return "Hotel Reservation System";
    }

    /**
     * Handles action events for components within this form.
     * This method should be overridden by subclasses to handle specific actions.
     *
     * @param e The action event to be handled.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Default implementation does nothing.
    }

    /**
     * Adds input fields to the form.
     * This method can be overridden by subclasses to add specific fields.
     */
    protected void addInputFields() {
        // Default implementation does nothing.
    }

    /**
     * Adds a combo box with a default label and no items to the form.
     *
     * @return The created ComboBox.
     */
    protected ComboBox addComboBox() {
        return addComboBox("Select a Room", new Object[] {});
    }

    /**
     * Adds a combo box with a specified label and items to the form.
     *
     * @param label The label for the combo box.
     * @param items The items to be added to the combo box.
     * @return The created ComboBox.
     */
    protected ComboBox addComboBox(String label, Object[] items) {
        ComboBox comboBox = new ComboBox(items);
        comboBox.addActionListener(this);
        addField(new JLabel(label), comboBox);
        return comboBox;
    }

    /**
     * Adds a text field with a default column size to the form.
     *
     * @param label The label for the text field.
     * @return The created JTextField.
     */
    protected JTextField addTextField(String label) {
        return addTextField(label, 12);
    }

    /**
     * Adds a text field with a specified column size to the form.
     *
     * @param label  The label for the text field.
     * @param columns The number of columns for the text field.
     * @return The created JTextField.
     */
    protected JTextField addTextField(String label, int columns) {
        JTextField textField = new JTextField(columns);
        addField(new JLabel(label), textField);
        return textField;
    }

    /**
     * Adds an "Enter" button to the button panel at the bottom of the form.
     *
     * @return The created JButton.
     */
    protected JButton addEnterButton() {
        JButton button = new JButton("Enter");
        button.setSize(60, 20);
        button.addActionListener(this);
        buttonPanel.add(button);
        return button;
    }

    /**
     * Adds a label and a component (e.g., text field or combo box) to the form with grid bag constraints.
     *
     * @param label     The label to be added.
     * @param component The component to be added.
     */
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

    /**
     * Adds additional input fields to the form based on the hotel reservation system.
     * This method can be overridden by subclasses to provide specific fields.
     *
     * @param HRS The hotel reservation system instance that this form will interact with.
     */
    protected void addInputFields(HotelReservationSystem HRS) {
        // Default implementation does nothing.
    }

    /**
     * Handles the action performed when the "Enter" button is clicked or other actions are triggered.
     * This method should be overridden by subclasses to define specific behavior on form submission.
     */
    protected void onEnter() {
        // Default implementation does nothing.
    }
}