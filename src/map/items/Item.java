package map.items;

public abstract class Item {
    protected String name;

    public abstract String getName();

    public abstract String effect();

    @Override
    public String toString() {
        return name;
    }
}