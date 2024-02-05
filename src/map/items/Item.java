package map.items;

public abstract class Item {
    protected String name;

    public abstract String getName();

    public abstract String effect();

    public abstract boolean usableAnytime();

    public abstract String itemToPickUp();

    @Override
    public String toString() {
        return name;
    }
}