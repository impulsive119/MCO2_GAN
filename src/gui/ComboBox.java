package gui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.lang.reflect.Array;

/**
 * A custom JComboBox that includes a "none" item at the top of the list and additional functionalities to help manage the Hotel Reservation System's
 * interaction with the GUI
 */
public class ComboBox extends JComboBox<Object> {

    /**
     * An item used to represent "none" or "no selection".
     * This item is displayed as an empty string in the combo box.
     */
    public static final Object NONE = new Object() {
        @Override
        public String toString() {
            return "";
        }
    };

    /**
     * Adds a "none" item to the beginning of the provided array of items.
     *
     * @param items The array of items to which the "none" item will be added.
     * @param <T>   The type of the items in the array.
     * @return A new array with the "none" item added at the beginning.
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] addNone(T[] items) {
        if (items == null) {
            // Handle null items array case
            return (T[]) Array.newInstance(Object.class, 1); // Array of size 1 for the NONE item
        }
        T[] newArray = (T[]) Array.newInstance(items.getClass().getComponentType(), items.length + 1);
        newArray[0] = (T) NONE;
        System.arraycopy(items, 0, newArray, 1, items.length);
        return newArray;
    }

    /**
     * Constructs a new ComboBox using the given array of items.
     * The array will be modified to include the "none" item at the beginning.
     *
     * @param items The array of items to be added to the combo box.
     */
    public ComboBox(Object[] items) {
        super(addNone(items));
    }

    /**
     * Sets the items of the combo box, including the "none" item at the beginning.
     *
     * @param items The new array of items to be displayed in the combo box.
     */
    public void setItems(Object[] items) {
        this.setModel(new DefaultComboBoxModel<>(addNone(items)));
    }

    /**
     * Removes all items from the combo box and adds the "none" item back.
     * This ensures that the "none" item remains as the only item in the combo box.
     */
    @Override
    public void removeAllItems() {
        super.removeAllItems();
        addItem(NONE);
    }
}