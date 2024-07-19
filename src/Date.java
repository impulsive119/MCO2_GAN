public class Date {
    private final int date;
    private Boolean isAvailable;
    private double price;

    public Date(int date, double price){
        this.date = date;
        this.isAvailable = true;
        this.price = price;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    public void setAvailability(boolean availability){
        this.isAvailable = availability;
    }

    public double getPrice(){
        return price;
    }

    public int getDate(){
        return date;
    }

    public Boolean getAvailability() {
        return isAvailable;
    }
}
