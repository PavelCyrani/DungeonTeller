package map.items;

public abstract class Item {
    protected String name;

    public abstract String getName();

    /**
     * Method for explaining what is item for
     *
     * @return String text
     */
    public abstract String effect();

    /**
     * If returns true, player can use item when he wants to, else
     * item is used automatically.
     *
     * @return boolean
     */
    public abstract boolean usableAnytime();

    /**
     * Method that returns text first, last index of text is ID of item needed to get this item
     *
     * @return String
     */
    public abstract String itemToPickUp();

    @Override
    public String toString() {
        return name;
    }
}