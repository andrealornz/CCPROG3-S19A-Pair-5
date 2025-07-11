public class Jewelry extends Item {
    private double carats;

    public Jewelry(String name, double weight, double carats) {
        super(name, weight);
        this.carats = carats;
    }

    public double computePrice() {
        double price = this.carats * 100;
        return price;
    }
}