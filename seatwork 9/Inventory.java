import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> itemList;

    public Inventory() {
        this.itemList = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        this.itemList.add(item);
    }

    public boolean sellItem(int index) {
        boolean success = false;
        Item get = this.itemList.get(index);
        if(!get.equals(Scrap)) {
            get.computePrice();
            success = true;
            this.itemList.remove(get);
        }
        return success;
    }

    public void displayItemInformation() {
        for (Item item : this.itemList) {
            if (!item.equals(Scrap)) {
                print(item.getName() + " (weight " + item.getWeight() + ") (sellable)");
            } else {
                print(item.getName() + " (weight " + item.getWeight() + ")");
            }
        }
    }
}