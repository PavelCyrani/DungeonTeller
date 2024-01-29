package map.items;

public class GoldfishPouch extends Item{
    public GoldfishPouch() {
        this.name = "Pouch of goldfish";
    }

    @Override
    public String effect() {
        return "You can use them to buy something nice for you";
    }
}
