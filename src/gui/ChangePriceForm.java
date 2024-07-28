package gui;

import model.Hotel;

import javax.swing.*;

public abstract class ChangePriceForm extends InputForm{
    private JTextField priceField;

    @Override
    protected void addInputFields(){
        priceField = addTextField("Enter New Price: ");
    }

    @Override
    protected void onEnter(Hotel hotel) {
        double price = Double.parseDouble(priceField.getText());
        if(price < 100){
            JOptionPane.showMessageDialog(this,"Price Must Be Greater than 100.00");
        }else{
            hotel.changePrice(price);
            JOptionPane.showMessageDialog(this, "New Price: " + price);
        }
    }
}
