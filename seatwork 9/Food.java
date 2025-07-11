public class Food extends Item {
    private int nutritionValue;

    public Food(String name, double weight, int nutritionValue) {
        super(name, weight);
        this.nutritionValue = nutritionValue;
    }

    public double computePrice() {
        double price = this.nutritionValue * 10;
        return price;
    }
}