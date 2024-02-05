package map.items;

public class WisePotion extends Item {
    public WisePotion() {
        this.name = "Potion of wiseness";
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String effect() {
        return "In your next battle you will need less words to defeat the enemy";
    }

    @Override
    public boolean usableAnytime() {
        return true;
    }

    @Override
    public String itemToPickUp() {
        return null;
    }
}