public class Stay4Get1Discount extends Discount {
    public Stay4Get1Discount(int stayDuration){
        super();
        this.discountCode = "STAY4_GET1";
        this.discountPercentage = ((double) 1 / stayDuration);
    }
}
