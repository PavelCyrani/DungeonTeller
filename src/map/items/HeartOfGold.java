package map.items;

public class HeartOfGold extends Item {

    public HeartOfGold() {
        this.name = "Heart of gold";
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String effect() {
        return "You have a second chance, don't waste it";
    }

    @Override
    public boolean usableAnytime() {
        return false;
    }

    @Override
    public String itemToPickUp() {
        return "Medic offer's you " + name + " for Pouch of goldfish. 2";
    }
}