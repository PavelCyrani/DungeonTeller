package map.items;

public class Portal extends Item {
    public Portal() {
        this.name = "empty aquarium";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String effect() {
        return null;
    }

    @Override
    public boolean usableAnytime() {
        return false;
    }

    @Override
    public String itemToPickUp() {
        return null;
    }
}
