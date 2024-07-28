package gui;

import model.Hotel;

import javax.swing.*;

public abstract class AddPremiumToDateForm extends InputForm{
    private JTextField premiumField;
    private JTextField dateField;

    @Override
    protected void addInputFields(){
        dateField = addTextField("Enter Date: ");
        premiumField = addTextField("Enter Premium: ");
    }

    @Override
    protected void onEnter(Hotel hotel) {
        int date = Integer.parseInt(dateField.getText());
        double premium = Double.parseDouble(premiumField.getText());
        if(date < 1 || date > 31){
            JOptionPane.showMessageDialog(this, "Invalid Date");
        }else if(premium < 0.5 || premium > 1.5 ){
            JOptionPane.showMessageDialog(this, "Value Must Be Between 0.5 and 1.5");
        }else{
            hotel.addPremiumToDate(date, premium);
            JOptionPane.showMessageDialog(this,"Premium Added");
        }
    }
}
