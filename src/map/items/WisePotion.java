package map.items;

public class WisePotion extends Item{
    public WisePotion() {
        this.name = "Potion of wiseness";
    }

    @Override
    public String effect() {
        return "In your next battle you will need less words to defeat the enemy";
    }
}
