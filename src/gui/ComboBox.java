package gui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class ComboBox extends JComboBox {

    public static final Object NONE = new Object() {
        @Override
        public String toString() {
            return "";
        }
    };

    public static Object[] addNone(Object[] items) {
        Object[] newArray = new Object[items.length + 1];
        newArray[0] = NONE;
        System.arraycopy(items, 0, newArray, 1, items.length);
        return newArray;
    }

    public ComboBox(Object[] items) {
        super(addNone(items));
    }

    public void setItems(Object[] items) {
        this.setModel(new DefaultComboBoxModel(addNone(items)));
    }

    @Override
    public void removeAllItems() {
        super.removeAllItems();
        addItem(NONE);
    }

}
