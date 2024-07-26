package model;

public class Date {
    private final int date;
    private final double price;

    public Date(int date, double price){
        this.date = date;
        this.price = price;
    }

    public double getPrice(){
        return price;
    }

    public int getDate(){
        return date;
    }
}
