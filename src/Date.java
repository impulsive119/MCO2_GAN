public class Date {
    private final int date;
    private Boolean isAvailable;
    private double price;
    private Boolean isCheckoutDate;

    public Date(int date, double price){
        this.date = date;
        this.isAvailable = true;
        this.price = price;
        this.isCheckoutDate = false;
    }

    public void setPrice(Room room){
        this.price = room.getPrice();
    }

    public void setAvailability(boolean availability){
        this.isAvailable = availability;
    }

    public void setIsCheckoutDate(boolean isCheckoutDate){
        this.isCheckoutDate = true;
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

    public Boolean getIsCheckoutDate(){
        return isCheckoutDate;
    }
}
