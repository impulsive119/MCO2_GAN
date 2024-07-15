public class Stay4Get1Discount extends Discount {
    public Stay4Get1Discount(){
        super();
        this.discountCode = "STAY4_GET1";
        this.discountPercentage = 0;
    }

    public static double getDiscountAmount(double firstDayCost, double totalCost){
        return totalCost - firstDayCost;
    }
}
