package model;

/**
 * The Date class represents a date with an associated price used to calculate the total price of a reservation.
 */
public class Date {
    private final int date;
    private final double price;

    /**
     * Constructs a Date object using the given date and price.
     *
     * @param date The date represented by this Date object.
     * @param price The price associated with this date.
     */
    public Date(int date, double price) {
        this.date = date;
        this.price = price;
    }

    /**
     * Gets the price associated with this date.
     *
     * @return The price for this date.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the date represented by this Date object.
     *
     * @return The date.
     */
    public int getDate() {
        return date;
    }
}