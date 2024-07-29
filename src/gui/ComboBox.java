package gui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.lang.reflect.Array;

public class ComboBox extends JComboBox<Object> {

    public static final Object NONE = new Object() {
        @Override
        public String toString() {
            return "";
        }
    };

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

    public ComboBox(Object[] items) {
        super(addNone(items));
    }

    public void setItems(Object[] items) {
        this.setModel(new DefaultComboBoxModel<>(addNone(items)));
    }

    @Override
    public void removeAllItems() {
        super.removeAllItems();
        addItem(NONE);
    }
}